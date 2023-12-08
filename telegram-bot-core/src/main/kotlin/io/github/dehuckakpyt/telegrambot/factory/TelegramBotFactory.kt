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
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.resolver.DialogUpdateResolver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.BotTemplate
import io.ktor.server.application.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.mp.KoinPlatform


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

        val telegramApiClient = TelegramApiClient(token)
        loadPublicModules(username, telegramApiClient, config)
        loadInternalModules(telegramApiClient, config, application)

        initiateHandlers(config)
    }

    private fun loadPublicModules(username: String, telegramApiClient: TelegramApiClient, config: TelegramBotConfig) =
        loadKoinModules(module {
            single<String>(named("username")) { username }
            single<MessageSource>(definition = config.messageSource)
            single<HtmlFormatter>(definition = config.htmlFormatter)
            single { BotTemplate() }
            single { TelegramBot(telegramApiClient, get(), username) }
        })

    private fun loadInternalModules(telegramApiClient: TelegramApiClient, config: TelegramBotConfig, application: Application) =
        loadInternalKoinModules(module {
            single<CallbackContentSource>(definition = config.callbackContentSource)
            single<ChainSource>(definition = config.chainSource)
            single<CallbackSerializer>(definition = config.callbackSerializer)
            single<ExceptionHandler>(definition = config.exceptionHandler)
            single<ChainExceptionHandler>(definition = config.chainExceptionHandler)
            single<ContentConverter>(definition = config.contentConverter)
            single(named("callbackDataDelimiter")) { config.callbackDataDelimiter }
            single(named("telegramBotTemplate")) { application.environment.config.config("telegram-bot.template") }
            single { config.templateConfig }

            single { telegramApiClient }
            singleOf(::ChainResolver)
            singleOf(::UpdateResolver)
            single { DialogUpdateResolver(get(), get(), get(), get(), getAll(), KoinPlatform.getKoin().get()) }
            single<UpdateReceiver>(definition = config.updateReceiver)

            single<MessageArgumentFactory> { AudioMessageArgumentFactory() }
            single<MessageArgumentFactory> { ContactMessageArgumentFactory() }
            single<MessageArgumentFactory> { DocumentMessageArgumentFactory() }
            single<MessageArgumentFactory> { PhotoMessageArgumentFactory() }
            single<MessageArgumentFactory> { TextMessageArgumentFactory() }
            single<MessageArgumentFactory> { VoiceMessageArgumentFactory() }
        })

    private fun initiateHandlers(config: TelegramBotConfig) {
        // создание обработчиков из методов расширения
        val botHandling = BotHandling()
        config.handling(botHandling)

        // создание обработчиков из классов
        getKoin().getAll<BotHandler>()
    }
}