package io.github.dehuckakpyt.telegrambot.exception.handler

import com.dehucka.microservice.logger.Logging
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.whenKnownErrorTemplate
import io.github.dehuckakpyt.telegrambot.template.whenUnknownErrorTemplate


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class ExceptionHandlerImpl(
    protected val bot: TelegramBot,
) : ExceptionHandler(), Templating, Logging {

    override suspend fun execute(chatId: Long, block: suspend () -> Unit): Unit {
        try {
            block()
        } catch (throwable: Throwable) {
            caught(chatId, throwable)
        }
    }

    override suspend fun caught(chatId: Long, ex: Throwable) {
        when (ex) {
            is PrivateChatException -> {
                if (chatId < 0) return // у личных чатов положительный id
                bot.sendMessage(chatId, whenKnownErrorTemplate with ("message" to ex.localizedMessage))
            }

            is ChatException -> bot.sendMessage(chatId, whenKnownErrorTemplate with ("message" to ex.localizedMessage))

            else -> {
                logger.error("Unexpected error while handling message in chat $chatId", ex)
                bot.sendMessage(chatId, whenUnknownErrorTemplate)
            }
        }
    }
}