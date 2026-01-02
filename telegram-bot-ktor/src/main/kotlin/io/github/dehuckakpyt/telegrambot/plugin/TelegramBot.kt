package io.github.dehuckakpyt.telegrambot.plugin

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.KtorMessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.server.config.*
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig() }) {
    val telegramBotConfig = application.environment.config.config("telegram-bot")
    if (pluginConfig.token == null) pluginConfig.token = telegramBotConfig.tryGetString("token")
    if (pluginConfig.username == null) pluginConfig.username = telegramBotConfig.tryGetString("username")
    if (pluginConfig.receiving.messageTemplate == null) pluginConfig.receiving.messageTemplate = { KtorMessageTemplate() }

    InternalKoinContext.koin.declare<Application>(application, named("application"))

    if (telegramBotConfig.propertyOrNull("template") != null) {
        InternalKoinContext.koin.declare<ApplicationConfig>(telegramBotConfig.config("template"), named("telegramBotTemplate"))
    }

    val context = TelegramBotFactory.createTelegramBotContext(pluginConfig)
    val telegramBot = context.telegramBot
    val updateReceiver = context.updateReceiver

    val koin = getKoin()
    koin.declare<TelegramBot>(telegramBot)
    koin.declare<UpdateReceiver>(updateReceiver)
    koin.declare<BotHandling>(context.botHandling)
    koin.declare<BotUpdateHandling>(context.botUpdateHandling)
    koin.declare<Templater>(context.templater)
    koin.declare<ButtonFactory>(context.buttonFactory)
    koin.declare<InputFactory>(context.inputFactory)
    koin.declare(context.telegramMessageSource)
    koin.declare(context.telegramUserSource)
    koin.declare<ChainSource>(context.chainSource)
    koin.declare<CallbackContentSource>(context.callbackContentSource)

    koin.getAll<BotHandler>()
    koin.getAll<BotUpdateHandler>()

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

    on(MonitoringEvent(ApplicationStopping)) { application ->
        stopTelegramBot()

        // Release resources and unsubscribe from events
        application.environment.monitor.unsubscribe(ApplicationStarted) {}
        application.environment.monitor.unsubscribe(ApplicationStopping) {}
    }
}

