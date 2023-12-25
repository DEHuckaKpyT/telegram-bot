package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambot.config.inDatabase
import io.github.dehuckakpyt.telegrambot.config.template.templating
import io.github.dehuckakpyt.telegrambot.ext.databaseSource
import io.github.dehuckakpyt.telegrambot.plugin.TelegramBot
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
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
        token = "5238202878:AAHGHJgor3PRhlp8-ZqJKcYfqQzekxwae8Q"
        username = "DEHuckaTestBot"

        messageSource = MessageSource.inDatabase

        receiving { telegramBot ->
            longPolling {
                limit = 10
                timeout = 25
            }

            databaseSource {
                maxCallbackContentsPerUser = 2
                callbackContentSource = CallbackContentSource.inDatabase
                chainSource = ChainSource.inDatabase
            }

            // для обработки своих исключений
//            exceptionHandler = CustomExceptionHandler(telegramBot)

            templating {
                freemarker {
                    defaultEncoding = "UTF-8"
                }
            }

            handling {
                startCommand()
                chainCommand()
                buttonCommand()
//                templateCommand()
                exceptionCommand()
                withArgsCommand()
            }
        }
    }
}