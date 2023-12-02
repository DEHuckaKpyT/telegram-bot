package io.github.dehuckakpyt.telegrambot.plugin.config.manager

import io.ktor.server.config.*


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBotConfigManager {

    fun createConfiguration(config: ApplicationConfig)

    fun preLoadModules() {}
}