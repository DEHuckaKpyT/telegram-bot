package io.github.dehuckakpyt.telegrambot.plugin

import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig(it) }) {
    if (!pluginConfig.enabled) return@createApplicationPlugin

    TelegramBotFactory.load(application, pluginConfig)
    val username = KoinPlatform.getKoin().get<String>(named("username"))
    val updateReceiver = InternalKoinContext.koin.get<UpdateReceiver>()

    fun startTelegramBot() {
        application.log.info("Starting telegram-bot '$username'..")
        updateReceiver.start()
        application.log.info("Telegram-bot '$username' started.")
    }

    fun stopTelegramBot() {
        application.log.info("Stopping telegram-bot '$username'..")
        updateReceiver.stop()
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

