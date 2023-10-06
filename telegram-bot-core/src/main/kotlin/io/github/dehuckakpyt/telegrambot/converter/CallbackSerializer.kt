package io.github.dehuckakpyt.telegrambot.converter


/**
 * Created on 04.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface CallbackSerializer {
    suspend fun toCallback(next: String, instance: Any?): String
    suspend fun getNext(callbackData: String): String
    suspend fun getContent(callbackData: String): String?
    fun validateCallbackName(name: String)
}