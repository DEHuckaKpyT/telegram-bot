package io.github.dehuckakpyt.telegrambot.converter

import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.core.json.JsonWriteFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import java.text.SimpleDateFormat
import kotlin.reflect.KClass

/**
 * Created on 04.10.2023.
 *
 * Simple implementation for serialization and deserialization for objects.
 *
 * It used in methods transfer() and transferred(). Also used in serializing in callbacks.
 *
 * @see io.github.dehuckakpyt.telegrambot.handling.BotHandling.transfer
 * @see io.github.dehuckakpyt.telegrambot.handling.BotHandling.transferredOrNull
 * @see io.github.dehuckakpyt.telegrambot.handling.BotHandling.transferred
 * @see io.github.dehuckakpyt.telegrambot.handling.BotHandling.transferredOrNull
 *
 * @author Denis Matytsin
 */
class JsonContentConverter : ContentConverter {

    private val shortMapper = jacksonMapperBuilder().run {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        configure(JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature(), false)
        configure(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature(), true)
    }.build().apply {
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        registerModule(JavaTimeModule())  // support java.time.* types

        registerModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
    }

    /**
     * Serialize object to string for store in source.
     *
     * @param instance object to store
     *
     * @return json of object
     */
    override fun toContent(instance: Any): String = shortMapper.writeValueAsString(instance)

    /**
     * Deserialize object from string.
     *
     * @param content json of object
     * @param clazz class of object
     *
     * @return deserialized object
     */
    override fun <T : Any> fromContent(content: String, clazz: KClass<T>): T =
        shortMapper.readValue(content, clazz.java)
}