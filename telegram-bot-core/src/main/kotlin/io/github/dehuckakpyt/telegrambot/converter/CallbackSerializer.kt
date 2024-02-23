package io.github.dehuckakpyt.telegrambot.converter


/**
 * Created on 04.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface CallbackSerializer {
    suspend fun toCallback(chatId: Long, fromId: Long, next: String, instance: Any?): String
    suspend fun fromCallback(callbackData: String): CallbackDataInfo
    fun validateCallbackName(name: String)
}