package io.github.dehuckakpyt.telegrambot.factory

import com.elbekd.bot.Bot
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
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
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal object TelegramBotFactory {

    fun load(application: Application, config: TelegramBotConfig): TelegramBot {
        val token = config.token ?: throw RuntimeException("Telegram-bot TOKEN must not be empty!")
        val username = config.username ?: throw RuntimeException("Telegram-bot USERNAME must not be empty!")

        loadRequiredModules(application, config)

        val externalBot = Bot.createPolling(token, username, config.pollingOptions)
        val telegramBot = TelegramBot(username, externalBot, config.messageSource)
        val chainResolver = ChainResolver(config.chainExceptionHandler)
        val updateResolver = UpdateResolver(
            config.contentConverter, telegramBot, chainResolver, config.exceptionHandler, username
        )

        val botHandling = BotHandling(application, chainResolver)

        telegramBot.onCallbackQuery(updateResolver::processCallback)
        telegramBot.onAnyUpdate(updateResolver::processUpdate)

        config.configureBot(telegramBot)
        config.handling(botHandling)

        return telegramBot
    }

    private fun loadRequiredModules(application: Application, config: TelegramBotConfig) = loadKoinModules(module {
        single<CallbackContentSource> { config.callbackContentSource }
        single<ChainSource> { config.chainSource }
        single<MessageSource> { config.messageSource }
        single<CallbackSerializer> { config.callbackSerializer }
        single<HtmlFormatter> { config.htmlFormatter }
        single(named("telegramBotTemplate")) { application.environment.config.config("telegram-bot.template") }
        single { config.templateConfig }
        single { BotTemplate() }
    })
}