package io.github.dehuckakpyt.telegrambot.plugin

import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.server.config.*
import org.koin.core.qualifier.named


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig() }) {
    InternalKoinContext.koin.declare<ApplicationConfig>(application.environment.config.config("telegram-bot.template"), named("telegramBotTemplate"))
    
    val telegramBot = TelegramBotFactory.createTelegramBot(pluginConfig)
    val updateReceiver = TelegramBotFactory.createUpdateReceiver(telegramBot, pluginConfig)

    fun startTelegramBot() {
        application.log.info("Starting telegram-bot '${telegramBot.username}'..")
        updateReceiver.start()
        application.log.info("Telegram-bot '${telegramBot.username}' started.")
    }

    fun stopTelegramBot() {
        application.log.info("Stopping telegram-bot '${telegramBot.username}'..")
        updateReceiver.stop()
        application.log.info("Telegram-bot '${telegramBot.username}' stopped.")
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

