package io.github.dehuckakpyt.telegrambot.converter

import kotlin.reflect.KClass


/**
 * Created on 04.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ContentConverter {

    fun toContent(instance: Any): String
    fun <T : Any> fromContent(content: String, clazz: KClass<T>): T
    fun toContentOrNull(instance: Any?): String?
    fun <T : Any> fromContentOrNull(content: String?, clazz: KClass<T>): T?
}