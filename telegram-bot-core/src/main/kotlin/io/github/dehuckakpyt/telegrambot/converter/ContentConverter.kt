package io.github.dehuckakpyt.telegrambot.converter

import kotlin.reflect.KClass


/**
 * Created on 04.10.2023.
 *
 * Interface for serialization and deserialization for objects.
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
interface ContentConverter {

    /**
     * Serialize object to string for store in source.
     *
     * @param instance object to store
     *
     * @return stringified object
     */
    fun toContent(instance: Any): String

    /**
     * Deserialize object from string.
     *
     * @param content stringified object
     * @param clazz class of object
     *
     * @return deserialized object
     */
    fun <T : Any> fromContent(content: String, clazz: KClass<T>): T
}

fun ContentConverter.toContentOrNull(instance: Any?): String? {
    instance ?: return null

    return toContent(instance)
}

fun <T : Any> ContentConverter.fromContentOrNull(content: String?, clazz: KClass<T>): T? {
    content ?: return null

    return fromContent(content, clazz)
}

inline fun <reified T : Any> ContentConverter.fromContent(content: String): T = fromContent(content, T::class)
inline fun <reified T : Any> ContentConverter.fromContentOrNull(content: String?): T? = fromContentOrNull(content, T::class)