package io.github.dehuckakpyt.telegrambot.plugin

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.binder.config.ApplicationConfigBinder
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.constants.properties.PropertiesConstants.PROPERTIES_ROOT
import io.github.dehuckakpyt.telegrambot.config.properties.TelegramBotProperties
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode.WEBHOOK
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.ext.config.merge
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.WebhookUpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.KtorMessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import io.ktor.server.config.*
import org.koin.core.qualifier.named
import org.koin.ktor.plugin.koinModule
import org.koin.mp.KoinPlatform.getKoin


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val TelegramBot = createApplicationPlugin(name = "telegram-bot", "telegram-bot", { TelegramBotConfig() }) {
    val applicationConfig = application.environment.config
    InternalKoinContext.koin.declare<Application>(application, named("application"))

    // Priority: code > application config > telegram-bot.yaml > defaults.
    ApplicationConfigBinder.bind(applicationConfig, PROPERTIES_ROOT, TelegramBotProperties::class.java)
        ?.let(pluginConfig::merge)

    if (pluginConfig.receiving.messageTemplate == null) pluginConfig.receiving.messageTemplate = { KtorMessageTemplate() }
    if (pluginConfig.receiving.updateReceiver == null && pluginConfig.receiving.mode == WEBHOOK) {
        pluginConfig.receiving.updateReceiver = { WebhookUpdateReceiver(telegramBot, receiving.updateResolver, receiving.webhook) }
    }

    if (applicationConfig.propertyOrNull("telegram-bot.template") != null) {
        InternalKoinContext.koin.declare<ApplicationConfig>(applicationConfig.config("telegram-bot.template"), named("telegramBotTemplate"))
    }

    val context = TelegramBotFactory.createTelegramBotContext(pluginConfig)
    val telegramBot = context.telegramBot
    val updateReceiver = context.updateReceiver

    application.koinModule {
        single<TelegramBot> { telegramBot }
        single<UpdateReceiver> { updateReceiver }
        single<BotHandling> { context.botHandling }
        single<BotUpdateHandling> { context.botUpdateHandling }
        single<Templater> { context.templater }
        single<ButtonFactory> { context.buttonFactory }
        single<InputFactory> { context.inputFactory }
        single { context.telegramMessageSource }
        single { context.telegramUserSource }
        single<ChainSource> { context.chainSource }
        single<CallbackContentSource> { context.callbackContentSource }
        single { context.chainManager }
    }

    val koin = getKoin()
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

