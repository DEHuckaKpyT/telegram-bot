package io.github.dehuckakpyt.telegrambot.source.message

import com.dehucka.microservice.logger.Logging


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface MessageSource : Logging {

    suspend fun save(chatId: Long, fromId: Long, messageId: Long, text: String? = null)

    companion object
}