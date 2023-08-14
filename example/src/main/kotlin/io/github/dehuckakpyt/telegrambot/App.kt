package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.plugin.configureDatabase
import io.github.dehuckakpyt.telegrambot.plugin.configureDependencyInjection
import io.github.dehuckakpyt.telegrambot.plugin.configureTelegramBot
import io.ktor.server.application.*

/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureDependencyInjection()
    configureDatabase()
    configureTelegramBot()
}
