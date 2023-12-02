package io.github.dehuckakpyt.telegrambot.config.manager

import io.github.dehuckakpyt.telegrambot.config.DatabaseSourceConfig
import io.github.dehuckakpyt.telegrambot.plugin.config.manager.TelegramBotConfigManager
import io.ktor.server.config.*
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DatabaseSourceConfigManager : TelegramBotConfigManager {
    override fun createConfiguration(config: ApplicationConfig) {
        if (databaseSourceConfig != null) throw RuntimeException("DatabaseSourceConfig may be initialized only once.")

        databaseSourceConfig = DatabaseSourceConfig(config)
    }

    override fun preLoadModules() {
        loadKoinModules(module {
            single(named("maxCallbackContentsPerUser")) { databaseSourceConfig!!.maxCallbackContentsPerUser }
        })
    }

    companion object {
        var databaseSourceConfig: DatabaseSourceConfig? = null
    }
}