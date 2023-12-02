package io.github.dehuckakpyt.telegrambot.loader

import io.github.dehuckakpyt.telegrambot.config.manager.DatabaseSourceConfigManager
import io.github.dehuckakpyt.telegrambot.plugin.config.manager.TelegramBotConfigManager
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object DatabaseSourceLoader {
    fun load() {
        loadKoinModules(module {
            singleOf(::DatabaseSourceConfigManager) bind TelegramBotConfigManager::class
        })
    }
}