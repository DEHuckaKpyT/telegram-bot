package com.dehucka.telegrambot.plugin

import com.dehucka.telegrambot.BotHandling
import com.dehucka.telegrambot.plugin.config.TelegramBotConfig
import com.elbekd.bot.Bot
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig(it) }) {
    if (!pluginConfig.enabled) return@createApplicationPlugin

    val token = pluginConfig.token ?: throw RuntimeException("Telegram-bot TOKEN must not be empty!")
    val username = pluginConfig.username ?: throw RuntimeException("Telegram-bot USERNAME must not be empty!")
    val bot = Bot.createPolling(token, username)
    val botHandling = BotHandling(application, bot, username)

    pluginConfig.configureBot(bot)
    pluginConfig.handling(botHandling)

    loadKoinModules(module { single { bot } })

    fun startTelegramBot() {
        application.log.info("Starting telegram-bot '$username'..")
        bot.start()
        application.log.info("Telegram-bot '$username' started.")
    }

    fun stopTelegramBot() {
        application.log.info("Stopping telegram-bot '$username'..")
        bot.stop()
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

