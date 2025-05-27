package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambot.ext.config.receiver.handling
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.longPolling
import io.github.dehuckakpyt.telegrambot.ext.source.inDatabase
import io.github.dehuckakpyt.telegrambot.plugin.TelegramBot
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.*
import io.ktor.server.application.*


/**
 * Created on 16.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun Application.configureTelegramBot() {
    install(TelegramBot) {
        messageSource = { MessageSource.inDatabase }

        receiving {
            longPolling {
                limit = 10
                timeout = 25
            }

            callbackContentSource = { CallbackContentSource.inDatabase(maxCallbackContentsPerUser = 2) }
            chainSource = { ChainSource.inDatabase }
            telegramUserSource = { TelegramUserSource.inDatabase }
            telegramChatSource = { TelegramChatSource.inDatabase }
            telegramChatStatusEventSource = { TelegramChatStatusEventSource.inDatabase }

            // Handling custom exceptions
            exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templater) }

            handling {
                startCommand()
                chainCommand()
                buttonCommand()
                templateCommand()
                exceptionCommand()
                withArgsCommand()
            }
        }
    }
}
