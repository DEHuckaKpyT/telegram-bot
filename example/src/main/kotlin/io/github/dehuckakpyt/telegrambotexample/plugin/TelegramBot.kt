package io.github.dehuckakpyt.telegrambotexample.plugin

import io.github.dehuckakpyt.telegrambot.plugin.TelegramBot
import io.github.dehuckakpyt.telegrambotexample.handler.chainCommand
import io.github.dehuckakpyt.telegrambotexample.handler.startCommand
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

        handling {
            startCommand()
            chainCommand()
        }
    }
}