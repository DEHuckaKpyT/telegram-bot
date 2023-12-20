package io.github.dehuckakpyt.telegrambotexample.exception

import com.dehucka.microservice.exception.CustomException
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CustomExceptionHandler(bot: TelegramBot) : ExceptionHandlerImpl(bot) {
    override suspend fun caught(chatId: Long, ex: Throwable) {
        when (ex) {
            is CustomException -> bot.sendMessage(chatId, ex.localizedMessage)
            else -> super.caught(chatId, ex)
        }
    }
}