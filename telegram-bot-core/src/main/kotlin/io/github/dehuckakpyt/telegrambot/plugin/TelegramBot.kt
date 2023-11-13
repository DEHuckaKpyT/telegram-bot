package io.github.dehuckakpyt.telegrambot.plugin

import com.elbekd.bot.Bot
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.advise.ChainExceptionAdvice
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.BotTemplate
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
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

    val token = pluginConfig.token ?: throw RuntimeException("Telegram-bot TOKEN must not be empty!")
    val username = pluginConfig.username ?: throw RuntimeException("Telegram-bot USERNAME must not be empty!")

    loadKoinModules(module {
        single<CallbackContentSource> { pluginConfig.callbackContentSource }
        single<ChainSource> { pluginConfig.chainSource }
        single<MessageSource> { pluginConfig.messageSource }
        single<CallbackSerializer> { pluginConfig.callbackSerializer }
        single<HtmlFormatter> { pluginConfig.htmlFormatter }
        single(named("telegramBotTemplate")) { application.environment.config.config("telegram-bot.template") }
        single { pluginConfig.templateConfig }
        single { BotTemplate() }
    })

    val externalBot = Bot.createPolling(token, username, pluginConfig.pollingOptions)
    val telegramBot = TelegramBot(username, externalBot, pluginConfig.messageSource)
    val chainResolver = ChainResolver(ChainExceptionAdvice())
    val updateResolver = UpdateResolver(pluginConfig.contentConverter, telegramBot, chainResolver, username)

    val botHandling = BotHandling(application, chainResolver)

    pluginConfig.configureBot(telegramBot)
    pluginConfig.handling(botHandling)

    loadKoinModules(module {
        single<TelegramBot> { telegramBot } bind io.github.dehuckakpyt.telegrambot.TelegramBot::class
    })

    fun startTelegramBot() {
        application.log.info("Starting telegram-bot '$username'..")
        externalBot.start()
        application.log.info("Telegram-bot '$username' started.")
    }

    fun stopTelegramBot() {
        application.log.info("Stopping telegram-bot '$username'..")
        externalBot.stop()
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

