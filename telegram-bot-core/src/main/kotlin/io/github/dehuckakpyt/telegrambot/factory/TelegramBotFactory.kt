package io.github.dehuckakpyt.telegrambot.factory

import com.elbekd.bot.Bot
import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext.loadInternalKoinModules
import io.github.dehuckakpyt.telegrambot.context.getInternal
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
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
internal object TelegramBotFactory : InternalKoinComponent {

    fun load(application: Application, config: TelegramBotConfig): TelegramBot {
        val token = config.token ?: throw RuntimeException("Telegram-bot TOKEN must not be empty!")
        val username = config.username ?: throw RuntimeException("Telegram-bot USERNAME must not be empty!")

        val telegramBot = loadRequiredModules(token, username, application, config)
        loadInternalModules(config)
        initiateHandlers(config)
        subscribeToUpdates(telegramBot)

        return telegramBot
    }

    private fun loadRequiredModules(
        token: String,
        username: String,
        application: Application,
        config: TelegramBotConfig
    ): TelegramBot {
        loadKoinModules(module {
            single<String>(named("username")) { username }
            single<Application> { application }
            single<CallbackContentSource> { config.callbackContentSource }
            single<ChainSource> { config.chainSource }
            single<MessageSource> { config.messageSource }
            single<CallbackSerializer> { config.callbackSerializer }
            single<HtmlFormatter> { config.htmlFormatter }
            single(named("telegramBotTemplate")) { application.environment.config.config("telegram-bot.template") }
            single { config.templateConfig }
            single { BotTemplate() }
        })

        val externalBot = Bot.createPolling(token, username, config.pollingOptions)
        val telegramBot = TelegramBot(externalBot)

        loadKoinModules(module {
            single { telegramBot }
        })

        return telegramBot
    }

    private fun loadInternalModules(config: TelegramBotConfig) = loadInternalKoinModules(module {
        single<ExceptionHandler> { config.exceptionHandler }
        single<ChainExceptionHandler> { config.chainExceptionHandler }
        single<ContentConverter> { config.contentConverter }
        single { ChainResolver() }
        single { UpdateResolver() }
    })

    private fun initiateHandlers(config: TelegramBotConfig) {
        // создание обработчиков из методов расширения
        val botHandling = BotHandling()
        config.handling(botHandling)

        // создание обработчиков из классов
        getKoin().getAll<BotHandler>()
    }

    private fun subscribeToUpdates(telegramBot: TelegramBot) {
        val updateResolver = getInternal<UpdateResolver>()

        telegramBot.onCallbackQuery(updateResolver::processCallback)
        telegramBot.onAnyUpdate(updateResolver::processUpdate)
    }
}