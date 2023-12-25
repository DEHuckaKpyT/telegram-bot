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

    override fun toContent(instance: Any): String = shortMapper.writeValueAsString(instance)

    override fun <T : Any> fromContent(content: String, clazz: KClass<T>): T =
        shortMapper.readValue(content, clazz.java)

    override fun toContentOrNull(instance: Any?): String? {
        instance ?: return null

        return shortMapper.writeValueAsString(instance)
    }

    override fun <T : Any> fromContentOrNull(content: String?, clazz: KClass<T>): T? {
        content ?: return null

        return shortMapper.readValue(content, clazz.java)
    }
}