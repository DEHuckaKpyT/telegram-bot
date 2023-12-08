package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambot.ext.databaseSource
import io.github.dehuckakpyt.telegrambot.plugin.TelegramBot
import io.github.dehuckakpyt.telegrambotexample.exception.CustomExceptionHandler
import io.github.dehuckakpyt.telegrambotexample.handler.*
import io.ktor.server.application.*
import org.koin.mp.KoinPlatform


/**
 * Created on 16.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun Application.configureTelegramBot() {
    install(TelegramBot) {
        longPolling {
            limit = 10
            timeout = 3
        }

        configureTemplating {
            defaultEncoding = "UTF-8"
        }

        // для обработки своих исключений
        exceptionHandler = { CustomExceptionHandler(KoinPlatform.getKoin().get()) }

        databaseSource {
            allInDatabase()
            maxCallbackContentsPerUser = 2
        }

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