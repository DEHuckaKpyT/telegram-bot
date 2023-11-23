package io.github.dehuckakpyt.telegrambot.plugin

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import org.koin.core.context.loadKoinModules
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig(it) }) {
    if (!pluginConfig.enabled) return@createApplicationPlugin

    val telegramBot = TelegramBotFactory.load(application, pluginConfig)
    val username = telegramBot.username

    loadKoinModules(module {
        single<TelegramBot> { telegramBot } bind io.github.dehuckakpyt.telegrambot.TelegramBot::class
    })

    fun startTelegramBot() {
        application.log.info("Starting telegram-bot '$username'..")
        telegramBot.start()
        application.log.info("Telegram-bot '$username' started.")
    }

    fun stopTelegramBot() {
        application.log.info("Stopping telegram-bot '$username'..")
        telegramBot.stop()
        application.log.info("Telegram-bot '$username' stopped.")
    }

    on(MonitoringEvent(ApplicationStarted)) {
        startTelegramBot()
    }

    on(MonitoringEvent(ApplicationStopped)) { application ->
        stopTelegramBot()

        // Release resources and unsubscribe from events
        application.environment.monitor.unsubscribe(ApplicationStarted) {}
        application.environment.monitor.unsubscribe(ApplicationStopped) {}
    }
}

