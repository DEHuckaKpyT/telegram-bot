package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.api.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.argument.message.factory.*
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext.loadInternalKoinModules
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.plugin.config.manager.TelegramBotConfigManager
import io.github.dehuckakpyt.telegrambot.plugin.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.DialogUpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.BotTemplate
import io.ktor.server.application.*
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal object TelegramBotFactory : InternalKoinComponent {

    fun load(application: Application, config: TelegramBotConfig) {
        val token = config.token ?: throw RuntimeException("Telegram-bot TOKEN must not be empty!")
        val username = config.username ?: throw RuntimeException("Telegram-bot USERNAME must not be empty!")

        val configManagers = getKoin().getAll<TelegramBotConfigManager>()
        configManagers.forEach(TelegramBotConfigManager::preLoadModules)

        loadPublicModules(username, config)
        loadInternalModules(token, config, application)
        getKoin().declare(TelegramBot())

        initiateHandlers(config)
    }

    private fun loadPublicModules(
        username: String,
        config: TelegramBotConfig
    ) {
        loadKoinModules(module {
            single<String>(named("username")) { username }
            single<MessageSource> { config.messageSource }
            single<HtmlFormatter> { config.htmlFormatter }
            single { BotTemplate() }
        })
    }

    private fun loadInternalModules(
        token: String, config: TelegramBotConfig, application: Application,
    ) = loadInternalKoinModules(module {
        single<CallbackContentSource> { config.callbackContentSource }
        single<ChainSource> { config.chainSource }
        single<CallbackSerializer> { config.callbackSerializer }
        single<ExceptionHandler> { config.exceptionHandler }
        single<ChainExceptionHandler> { config.chainExceptionHandler }
        single<ContentConverter> { config.contentConverter }
        single(named("callbackDataDelimiter")) { config.callbackDataDelimiter }
        single(named("telegramBotTemplate")) { application.environment.config.config("telegram-bot.template") }
        single { config.templateConfig }
        single { ChainResolver() }
        single { DialogUpdateResolver() }
        single { TelegramApiClient(token) }
        single { LongPollingUpdateReceiver(LongPollingConfig()) } bind UpdateReceiver::class

        single { AudioMessageArgumentFactory() } bind MessageArgumentFactory::class
        single { ContactMessageArgumentFactory() } bind MessageArgumentFactory::class
        single { DocumentMessageArgumentFactory() } bind MessageArgumentFactory::class
        single { PhotoMessageArgumentFactory() } bind MessageArgumentFactory::class
        single { TextMessageArgumentFactory() } bind MessageArgumentFactory::class
        single { VoiceMessageArgumentFactory() } bind MessageArgumentFactory::class
    })

    private fun initiateHandlers(config: TelegramBotConfig) {
        // создание обработчиков из методов расширения
        val botHandling = BotHandling()
        config.handling(botHandling)

        // создание обработчиков из классов
        getKoin().getAll<BotHandler>()
    }
}