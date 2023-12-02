package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.config.DatabaseSourceConfig
import io.github.dehuckakpyt.telegrambot.config.manager.DatabaseSourceConfigManager
import io.github.dehuckakpyt.telegrambot.loader.DatabaseSourceLoader
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import org.koin.mp.KoinPlatform


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun TelegramBotConfig.databaseSource(block: DatabaseSourceConfig.() -> Unit) {
    DatabaseSourceLoader.load()

    val configManager = KoinPlatform.getKoin().get<DatabaseSourceConfigManager>()
    configManager.createConfiguration(config)

    DatabaseSourceConfigManager.databaseSourceConfig!!.block()
}