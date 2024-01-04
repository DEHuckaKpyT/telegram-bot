package io.github.dehuckakpyt.telegrambotexample.exception

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CustomExceptionHandler(bot: TelegramBot, template: MessageTemplate, templater: Templater) : ExceptionHandlerImpl(bot, template, templater) {
    override suspend fun caught(chatId: Long, ex: Throwable) {
        when (ex) {
            is CustomException -> bot.sendMessage(chatId, ex.localizedMessage)
            else -> super.caught(chatId, ex)
        }
    }
}

class CustomException(message: String) : RuntimeException(message)
