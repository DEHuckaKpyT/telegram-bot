package io.github.dehuckakpyt.telegrambotexample

import io.github.dehuckakpyt.telegrambotexample.plugin.configureDatabase
import io.github.dehuckakpyt.telegrambotexample.plugin.configureDependencyInjection
import io.github.dehuckakpyt.telegrambotexample.plugin.configureTelegramBot
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

//    startRunners()
}
