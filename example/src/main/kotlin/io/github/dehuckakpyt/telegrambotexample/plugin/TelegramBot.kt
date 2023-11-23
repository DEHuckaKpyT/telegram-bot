package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambot.ext.databaseSources
import io.github.dehuckakpyt.telegrambot.plugin.TelegramBot
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
        configureTemplating {
            defaultEncoding = "UTF-8"
        }

        // для обработки своих исключений
        exceptionHandler = CustomExceptionHandler()

        databaseSources()

        handling {
            startCommand()
            chainCommand()
            registerCommand()
            buttonCommand()
            templateCommand()
            exceptionCommand()
        }
    }
}