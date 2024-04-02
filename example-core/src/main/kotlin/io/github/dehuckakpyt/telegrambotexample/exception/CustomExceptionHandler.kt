package io.github.dehuckakpyt.telegrambotexample.exception

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.model.type.Chat
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CustomExceptionHandler(bot: TelegramBot, template: MessageTemplate, templater: Templater) :
    ExceptionHandlerImpl(bot, template, templater) {

    override suspend fun caught(chat: Chat, ex: Throwable) {
        when (ex) {
            is CustomException -> bot.sendMessage(chat.id, ex.localizedMessage)
            else -> super.caught(chat, ex)
        }
    }
}