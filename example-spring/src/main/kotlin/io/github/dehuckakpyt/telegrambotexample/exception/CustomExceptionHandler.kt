package io.github.dehuckakpyt.telegrambotexample.exception

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.model.telegram.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import org.slf4j.LoggerFactory


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CustomExceptionHandler(bot: TelegramBot, template: MessageTemplate, templater: Templater) :
    ExceptionHandlerImpl(bot, template, templater) {

    private val logger = LoggerFactory.getLogger(ExceptionHandlerImpl::class.java)

    override suspend fun caught(chat: Chat, ex: Throwable) {
        when (ex) {
            is CustomException -> bot.sendMessage(chat.id, ex.localizedMessage)
            else -> super.caught(chat, ex)
        }
    }

    override suspend fun executeCallback(callback: CallbackQuery, block: suspend () -> Unit) {
        try {
            block()
        } catch (ce: CustomException) {
            bot.sendMessage(callback.message!!.chat.id, template.whenKnownException with ("message" to ce.localizedMessage))
        } catch (throwable: Throwable) {
            val chatId = callback.message!!.chat.id

            logger.error("Unexpected error while handling message in chat $chatId", throwable)
            bot.sendMessage(chatId, template.whenUnknownException)
        }
    }
}

class CustomException(message: String) : RuntimeException(message)
