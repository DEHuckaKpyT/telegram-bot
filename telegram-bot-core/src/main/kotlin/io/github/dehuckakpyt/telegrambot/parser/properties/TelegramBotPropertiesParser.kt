package io.github.dehuckakpyt.telegrambot.parser.properties

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.*
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.dehuckakpyt.telegrambot.config.constants.properties.PropertiesConstants.PROPERTIES_ROOT
import io.github.dehuckakpyt.telegrambot.config.properties.TelegramBotProperties
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode
import io.github.dehuckakpyt.telegrambot.exception.parser.properties.TelegramBotPropertiesParseException
import io.github.dehuckakpyt.telegrambot.exception.parser.properties.TelegramBotPropertiesParseException.Reason
import io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer.KotlinDurationDeserializer
import io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer.TelegramBotReceivingModeDeserializer
import kotlin.time.Duration

/**
 * Parser for [TelegramBotProperties] from yaml.
 *
 * Supports:
 * - root key `telegram-bot`
 * - kebab-case keys
 * - dotted keys (`receiving.long-polling.timeout`)
 * - placeholders from yaml properties, env and defaults (`${username}`, `${telegram-bot.username}`, `${ENV}`, `${ENV:default}`)
 * - placeholder yaml-path style depends on yaml root style:
 *   with `telegram-bot:` root -> use `${telegram-bot...}`,
 *   without `telegram-bot:` root -> use `${...}`.
 */
internal object TelegramBotPropertiesParser {

    private val objectMapper = ObjectMapper(YAMLFactory())
        .registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(
            SimpleModule().apply {
                addDeserializer(Duration::class.java, KotlinDurationDeserializer())
                addDeserializer(ReceivingMode::class.java, TelegramBotReceivingModeDeserializer())
            }
        )

    /**
     * Parses [TelegramBotProperties] from yaml content.
     */
    internal fun parseYamlContent(yaml: String, env: Map<String, String> = System.getenv()): TelegramBotProperties {
        if (yaml.isBlank()) return TelegramBotProperties()

        val yamlRootNode = objectMapper.readTree(yaml) ?: return TelegramBotProperties()
        val yamlRootObject = (yamlRootNode as? ObjectNode) ?: return TelegramBotProperties()
        val normalizedRoot = YamlNodeNormalizer.normalizeObjectNode(yamlRootObject)
        val hasTelegramBotRoot = normalizedRoot.has(PROPERTIES_ROOT) && normalizedRoot[PROPERTIES_ROOT] is ObjectNode
        val normalizedTree = extractPropertiesRoot(normalizedRoot, hasTelegramBotRoot)
        val resolvedTree = PlaceholderResolver(
            rootNode = normalizedTree,
            env = env,
            yamlUsesTelegramBotRoot = hasTelegramBotRoot,
        ).resolveTree(normalizedTree)

        return objectMapper.treeToValue(resolvedTree, TelegramBotProperties::class.java)
            ?: TelegramBotProperties()
    }

    /**
     * Parses [TelegramBotProperties] from classpath resource (for example, `telegram-bot.yaml`).
     *
     * If the resource does not exist, returns empty [TelegramBotProperties].
     */
    internal fun parse(resourceName: String, env: Map<String, String> = System.getenv()): TelegramBotProperties {
        val classLoader = Thread.currentThread().contextClassLoader ?: TelegramBotPropertiesParser::class.java.classLoader
        val yaml = classLoader.getResourceAsStream(resourceName)
            ?.bufferedReader(Charsets.UTF_8)
            ?.use { it.readText() }
            ?: return TelegramBotProperties()
        return parseYamlContent(yaml, env)
    }

    /**
     * Extracts `telegram-bot` root if present, otherwise returns the input object.
     */
    private fun extractPropertiesRoot(root: ObjectNode, hasTelegramBotRoot: Boolean): ObjectNode {
        if (hasTelegramBotRoot) {
            return root[PROPERTIES_ROOT] as ObjectNode
        }
        return root
    }

    private object YamlNodeNormalizer {
        /**
         * Normalizes yaml object keys:
         * - regular nested object keys are kept as-is
         * - dotted keys like "receiving.long-polling.timeout" are expanded into nested objects.
         */
        internal fun normalizeObjectNode(source: ObjectNode): ObjectNode {
            val normalizedObject = JsonNodeFactory.instance.objectNode()
            for ((key, value) in source.properties()) {
                val keyParts = key.split('.')
                val normalizedChild = normalizeNode(value)
                putByPath(normalizedObject, keyParts, normalizedChild)
            }
            return normalizedObject
        }

        private fun normalizeNode(node: JsonNode): JsonNode = when (node) {
            is ObjectNode -> normalizeObjectNode(node)
            is ArrayNode  -> JsonNodeFactory.instance.arrayNode().also { array ->
                node.forEach { array.add(normalizeNode(it)) }
            }

            else          -> node
        }

        /**
         * Writes a value into object by path parts.
         *
         * Example:
         * path = ["receiving", "webhook", "url-host"]
         * creates intermediate object nodes and writes value into `url-host`.
         */
        private fun putByPath(root: ObjectNode, path: List<String>, value: JsonNode) {
            var currentNode = root
            for (index in 0 until path.size - 1) {
                val pathSegment = path[index]
                val existingChild = currentNode[pathSegment]
                val nextNode = if (existingChild is ObjectNode) existingChild else JsonNodeFactory.instance.objectNode().also {
                    currentNode.set<ObjectNode>(pathSegment, it)
                }
                currentNode = nextNode
            }
            currentNode.set<JsonNode>(path.last(), value)
        }
    }

    private class PlaceholderResolver(
        private val rootNode: ObjectNode,
        private val env: Map<String, String>,
        private val yamlUsesTelegramBotRoot: Boolean,
    ) {
        // Cache of already resolved property references to avoid repeated evaluation.
        private val resolvedReferenceCache = hashMapOf<String, String?>()

        // Active resolution stack for cycle detection (e.g. a -> b -> a).
        private val resolvingReferencePaths = hashSetOf<String>()

        /**
         * Resolves placeholders in all text nodes of the tree.
         * Placeholder lookup order: yaml properties -> env -> default (if provided).
         */
        internal fun resolveTree(node: JsonNode, currentPath: String? = null): JsonNode = when (node) {
            is ObjectNode -> JsonNodeFactory.instance.objectNode().also { targetObject ->
                for ((fieldName, fieldValue) in node.properties()) {
                    val fieldPath = listOfNotNull(currentPath, fieldName).joinToString(".")
                    val resolvedValue = resolveTree(fieldValue, fieldPath)
                    targetObject.set<JsonNode>(fieldName, resolvedValue)
                }
            }

            is ArrayNode  -> JsonNodeFactory.instance.arrayNode().also { targetArray ->
                node.forEachIndexed { index, item ->
                    val itemPath = listOfNotNull(currentPath, index.toString()).joinToString(".")
                    targetArray.add(resolveTree(item, itemPath))
                }
            }

            is TextNode   -> resolveTextNode(node, currentPath)
            else          -> node
        }

        /**
         * Resolves all placeholders in a single scalar text value.
         *
         * Also applies "blank -> null" rule after substitutions.
         */
        private fun resolveTextNode(node: TextNode, currentPath: String?): JsonNode {
            val rawText = node.asText()
            var resolvedText = rawText
            while (true) {
                val placeholderMatches = findPlaceholders(resolvedText)
                if (placeholderMatches.isEmpty()) break

                val rebuilt = StringBuilder()
                var cursor = 0
                for (placeholderMatch in placeholderMatches) {
                    rebuilt.append(resolvedText, cursor, placeholderMatch.startIndex)
                    val (placeholderKey, defaultValue) = splitPlaceholderToken(placeholderMatch.token, currentPath)
                    val replacement = resolvePlaceholderValue(placeholderKey, defaultValue, currentPath)
                    rebuilt.append(replacement.orEmpty())
                    cursor = placeholderMatch.endIndexExclusive
                }
                rebuilt.append(resolvedText, cursor, resolvedText.length)
                val rebuiltText = rebuilt.toString()
                if (rebuiltText == resolvedText) {
                    throw TelegramBotPropertiesParseException(
                        reason = Reason.CYCLIC_REFERENCE,
                        path = currentPath,
                        placeholder = placeholderMatches.firstOrNull()?.token,
                        details = "Placeholder resolution made no progress.",
                    )
                }
                resolvedText = rebuiltText
            }

            if (rawText == resolvedText && findPlaceholders(rawText).isEmpty()) {
                return if (rawText.isBlank()) NullNode.instance else node
            }

            return if (resolvedText.isBlank()) NullNode.instance else TextNode.valueOf(resolvedText)
        }

        /**
         * Resolves one placeholder token value.
         *
         * Order:
         * 1. yaml property reference (if placeholder style matches current root mode)
         * 2. env variable
         * 3. inline default from `${key:default}`
         */
        private fun resolvePlaceholderValue(
            placeholderKey: String,
            defaultValue: String?,
            currentPath: String?,
        ): String? {
            val propertyReference = when (val lookup = lookupPropertyReference(placeholderKey)) {
                is PropertyLookupResult.Found -> lookup.path to lookup.node
                PropertyLookupResult.NotFound -> null
            }
            if (propertyReference != null) {
                val (propertyPath, propertyNode) = propertyReference
                return resolvePropertyNodeToString(propertyPath, propertyNode, currentPath)
            }

            env[placeholderKey]?.let { return it }

            if (defaultValue != null) return defaultValue

            throw TelegramBotPropertiesParseException(
                reason = Reason.MISSING_PLACEHOLDER_VALUE,
                path = currentPath,
                placeholder = placeholderKey,
            )
        }

        /**
         * Resolves a referenced property node to a scalar string.
         *
         * Nested placeholder chains are supported.
         * Complex referenced values (objects/arrays) are rejected.
         */
        private fun resolvePropertyNodeToString(
            propertyPath: String,
            propertyNode: JsonNode?,
            currentPath: String?,
        ): String? {
            resolvedReferenceCache[propertyPath]?.let { return it }
            if (!resolvingReferencePaths.add(propertyPath)) {
                throw TelegramBotPropertiesParseException(
                    reason = Reason.CYCLIC_REFERENCE,
                    path = currentPath,
                    placeholder = propertyPath,
                )
            }
            try {
                val resolved = when (propertyNode) {
                    null, is NullNode           -> null
                    is TextNode                 -> when (val resolvedNode = resolveTextNode(propertyNode, propertyPath)) {
                        is NullNode -> null
                        else        -> resolvedNode.asText()
                    }

                    is ObjectNode, is ArrayNode -> throw TelegramBotPropertiesParseException(
                        reason = Reason.COMPLEX_REFERENCE_NOT_SUPPORTED,
                        path = currentPath,
                        placeholder = propertyPath,
                    )

                    else                        -> propertyNode.asText()
                }
                resolvedReferenceCache[propertyPath] = resolved
                return resolved
            } finally {
                resolvingReferencePaths.remove(propertyPath)
            }
        }

        /**
         * Finds property node by placeholder key.
         * Placeholder style depends on root style:
         * - when yaml has `telegram-bot:` root: only `telegram-bot.*` placeholders map to yaml properties.
         * - when yaml has no `telegram-bot:` root: only non-prefixed placeholders map to yaml properties.
         */
        private fun lookupPropertyReference(placeholderKey: String): PropertyLookupResult {
            return if (yamlUsesTelegramBotRoot) {
                if (!placeholderKey.startsWith("$PROPERTIES_ROOT.")) return PropertyLookupResult.NotFound
                val pathWithoutRoot = placeholderKey.removePrefix("$PROPERTIES_ROOT.")
                findNodeByPath(pathWithoutRoot)
                    ?.let { PropertyLookupResult.Found(pathWithoutRoot, it) }
                    ?: PropertyLookupResult.NotFound
            } else {
                if (placeholderKey.startsWith("$PROPERTIES_ROOT.")) return PropertyLookupResult.NotFound
                findNodeByPath(placeholderKey)
                    ?.let { PropertyLookupResult.Found(placeholderKey, it) }
                    ?: PropertyLookupResult.NotFound
            }
        }

        /**
         * Finds node in normalized yaml tree by dotted path.
         *
         * Supports object fields and numeric array segments.
         */
        private fun findNodeByPath(dottedPath: String): JsonNode? {
            var current: JsonNode = rootNode
            for (segment in dottedPath.split('.')) {
                current = when (current) {
                    is ObjectNode -> current[segment] ?: return null
                    is ArrayNode  -> segment.toIntOrNull()?.let { idx -> current.get(idx) } ?: return null
                    else          -> return null
                }
            }
            return current
        }

        private sealed interface PropertyLookupResult {
            data class Found(val path: String, val node: JsonNode?) : PropertyLookupResult
            data object NotFound : PropertyLookupResult
        }

        /**
         * Finds top-level placeholders with balanced braces.
         * Supports nested placeholders in default values.
         */
        private fun findPlaceholders(text: String): List<PlaceholderMatch> {
            val matches = mutableListOf<PlaceholderMatch>()
            var index = 0

            while (index < text.length - 1) {
                if (text[index] != '$' || text[index + 1] != '{') {
                    index++
                    continue
                }

                val startIndex = index
                index += 2
                var nestedDepth = 0

                while (index < text.length) {
                    val c = text[index]
                    if (c == '$' && index + 1 < text.length && text[index + 1] == '{') {
                        nestedDepth++
                        index += 2
                        continue
                    }
                    if (c == '}') {
                        if (nestedDepth == 0) {
                            val endExclusive = index + 1
                            val token = text.substring(startIndex + 2, index)
                            matches.add(
                                PlaceholderMatch(
                                    startIndex = startIndex,
                                    endIndexExclusive = endExclusive,
                                    token = token,
                                )
                            )
                            index = endExclusive
                            break
                        }
                        nestedDepth--
                    }
                    index++
                }
            }

            return matches
        }

        /**
         * Splits placeholder token into key/default.
         * Uses first `:` as separator; default may contain nested placeholders.
         */
        private fun splitPlaceholderToken(token: String, path: String?): Pair<String, String?> {
            val separatorIndex = token.indexOf(':')
            if (separatorIndex < 0) {
                return token to null
            }

            val key = token.substring(0, separatorIndex)
            if (key.isBlank()) {
                throw TelegramBotPropertiesParseException(
                    reason = Reason.MISSING_PLACEHOLDER_VALUE,
                    path = path,
                    placeholder = token,
                    details = "Placeholder key is blank.",
                )
            }
            val defaultValue = token.substring(separatorIndex + 1)
            return key to defaultValue
        }

        private data class PlaceholderMatch(
            val startIndex: Int,
            val endIndexExclusive: Int,
            val token: String,
        )
    }
}
