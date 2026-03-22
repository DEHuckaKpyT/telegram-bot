package io.github.dehuckakpyt.telegrambot.binder.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode
import io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer.KotlinDurationDeserializer
import io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer.TelegramBotReceivingModeDeserializer
import io.ktor.server.config.*
import kotlin.time.Duration

/**
 * Binds [io.ktor.server.config.ApplicationConfig] subtree to target class.
 */
internal object ApplicationConfigBinder {
    private val objectMapper = ObjectMapper()
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
     * Returns parsed [targetClass] from config [rootPath] subtree.
     * If subtree is absent, returns `null`.
     */
    internal fun <T : Any> bind(
        applicationConfig: ApplicationConfig,
        rootPath: String,
        targetClass: Class<T>,
    ): T? {
        val section = runCatching { applicationConfig.config(rootPath) }.getOrNull() ?: return null
        val rawMap = section.toMap()
        val normalizedMap = normalizeBlankStrings(rawMap)
        return objectMapper.convertValue(normalizedMap, targetClass)
    }

    private fun normalizeBlankStrings(value: Any?): Any? = when (value) {
        is String    -> value.ifBlank { null }
        is Map<*, *> -> value.entries.associate { (key, nestedValue) ->
            key.toString() to normalizeBlankStrings(nestedValue)
        }

        is List<*>   -> value.map { normalizeBlankStrings(it) }
        else         -> value
    }
}