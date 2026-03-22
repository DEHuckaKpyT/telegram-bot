package io.github.dehuckakpyt.telegrambot.parser.properties

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.NullNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.dehuckakpyt.telegrambot.config.TelegramBotProperties
import io.github.dehuckakpyt.telegrambot.exception.parser.properties.TelegramBotPropertiesParseException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.time.Duration
import io.github.dehuckakpyt.telegrambot.exception.parser.properties.TelegramBotPropertiesParseException.Reason

/**
 * Parser for [io.github.dehuckakpyt.telegrambot.config.TelegramBotProperties] from yaml.
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
object TelegramBotPropertiesParser {
    private const val ROOT_KEY = "telegram-bot"

    private val objectMapper = ObjectMapper(YAMLFactory())
        .registerKotlinModule()
        .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(
            SimpleModule().apply {
                addDeserializer(Duration::class.java, KotlinDurationDeserializer())
            }
        )

    /**
     * Parses [TelegramBotProperties] from yaml content.
     */
    fun parse(yaml: String, env: Map<String, String> = System.getenv()): TelegramBotProperties {
        if (yaml.isBlank()) return TelegramBotProperties()

        val yamlRootNode = objectMapper.readTree(yaml) ?: return TelegramBotProperties()
        val yamlRootObject = (yamlRootNode as? ObjectNode) ?: return TelegramBotProperties()
        val hasTelegramBotRoot = yamlRootObject.has(ROOT_KEY) && yamlRootObject[ROOT_KEY] is ObjectNode
        val propertiesRoot = extractPropertiesRoot(yamlRootObject, hasTelegramBotRoot)
        val normalizedTree = YamlNodeNormalizer.normalizeObjectNode(propertiesRoot)
        val resolvedTree = PlaceholderResolver(
            rootNode = normalizedTree,
            env = env,
            yamlUsesTelegramBotRoot = hasTelegramBotRoot,
        ).resolveTree(normalizedTree)

        return objectMapper.treeToValue(resolvedTree, TelegramBotProperties::class.java)
            ?: TelegramBotProperties()
    }

    /**
     * Parses [TelegramBotProperties] from yaml file.
     */
    fun parse(path: Path, env: Map<String, String> = System.getenv()): TelegramBotProperties {
        if (!Files.exists(path) || !Files.isRegularFile(path)) return TelegramBotProperties()
        val yaml = Files.readString(path)
        return parse(yaml, env)
    }

    /**
     * Extracts `telegram-bot` root if present, otherwise returns the input object.
     */
    private fun extractPropertiesRoot(root: ObjectNode, hasTelegramBotRoot: Boolean): ObjectNode {
        if (hasTelegramBotRoot) {
            return root[ROOT_KEY] as ObjectNode
        }
        return root
    }

    private object YamlNodeNormalizer {
        /**
         * Normalizes yaml object keys:
         * - regular nested object keys are kept as-is
         * - dotted keys like "receiving.long-polling.timeout" are expanded into nested objects.
         */
        fun normalizeObjectNode(source: ObjectNode): ObjectNode {
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
            is ArrayNode -> JsonNodeFactory.instance.arrayNode().also { array ->
                node.forEach { array.add(normalizeNode(it)) }
            }
            else -> node
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
        fun resolveTree(node: JsonNode, currentPath: String? = null): JsonNode = when (node) {
            is ObjectNode -> JsonNodeFactory.instance.objectNode().also { targetObject ->
                for ((fieldName, fieldValue) in node.properties()) {
                    val fieldPath = listOfNotNull(currentPath, fieldName).joinToString(".")
                    val resolvedValue = resolveTree(fieldValue, fieldPath)
                    targetObject.set<JsonNode>(fieldName, resolvedValue)
                }
            }
            is ArrayNode -> JsonNodeFactory.instance.arrayNode().also { targetArray ->
                node.forEachIndexed { index, item ->
                    val itemPath = listOfNotNull(currentPath, index.toString()).joinToString(".")
                    targetArray.add(resolveTree(item, itemPath))
                }
            }
            is TextNode -> resolveTextNode(node, currentPath)
            else -> node
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
                resolvedText = rebuilt.toString()
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
                    null, is NullNode -> null
                    is TextNode -> when (val resolvedNode = resolveTextNode(propertyNode, propertyPath)) {
                        is NullNode -> null
                        else -> resolvedNode.asText()
                    }
                    is ObjectNode, is ArrayNode -> throw TelegramBotPropertiesParseException(
                        reason = Reason.COMPLEX_REFERENCE_NOT_SUPPORTED,
                        path = currentPath,
                        placeholder = propertyPath,
                    )
                    else -> propertyNode.asText()
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
                if (!placeholderKey.startsWith("$ROOT_KEY.")) return PropertyLookupResult.NotFound
                val pathWithoutRoot = placeholderKey.removePrefix("$ROOT_KEY.")
                findNodeByPath(pathWithoutRoot)
                    ?.let { PropertyLookupResult.Found(pathWithoutRoot, it) }
                    ?: PropertyLookupResult.NotFound
            } else {
                if (placeholderKey.startsWith("$ROOT_KEY.")) return PropertyLookupResult.NotFound
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
                    is ArrayNode -> segment.toIntOrNull()?.let { idx -> current.get(idx) } ?: return null
                    else -> return null
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

    /**
     * Converts ISO-8601 duration string (`PT10S`, `PT5M`, etc.) to Kotlin [Duration].
     */
    private class KotlinDurationDeserializer : StdScalarDeserializer<Duration>(Duration::class.java) {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): Duration {
            val value = parser.valueAsString
                ?: throw TelegramBotPropertiesParseException(
                    reason = Reason.INVALID_DURATION,
                    details = "Duration value must not be null.",
                )
            return runCatching { Duration.Companion.parse(value) }
                .getOrElse {
                    throw TelegramBotPropertiesParseException(
                        reason = Reason.INVALID_DURATION,
                        details = "Invalid kotlin.time.Duration value '$value'.",
                        cause = it,
                    )
                }
        }
    }
}
