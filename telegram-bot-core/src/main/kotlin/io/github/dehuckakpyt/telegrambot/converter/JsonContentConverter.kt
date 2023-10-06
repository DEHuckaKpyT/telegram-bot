package io.github.dehuckakpyt.telegrambot.converter

import com.dehucka.microservice.ext.shortMapper
import kotlin.reflect.KClass

class JsonContentConverter : ContentConverter {
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