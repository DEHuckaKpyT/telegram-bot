package io.github.dehuckakpyt.telegrambot.source.message


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface MessageSource {

    suspend fun save(chatId: Long, fromId: Long, messageId: Long, text: String? = null)

    companion object
}