package io.github.dehuckakpyt.telegrambot.plugin.config

import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
import io.github.dehuckakpyt.telegrambot.converter.SimpleCallbackSerializer
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandlerImpl
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatterImpl
import io.github.dehuckakpyt.telegrambot.plugin.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.ktor.server.config.*
import org.koin.core.definition.Definition
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBotConfig(val config: ApplicationConfig) {
    var enabled: Boolean = config.propertyOrNull("enabled")?.getString()?.toBooleanStrict() ?: true
    var token: String? = config.propertyOrNull("token")?.getString()
    var username: String? = config.propertyOrNull("username")?.getString()
    internal var updateReceiver: Definition<UpdateReceiver> = { LongPollingUpdateReceiver(LongPollingConfig()) }
    var handling: BotHandling.() -> Unit = {}
    var templateConfig: Configuration = Configuration(Version("2.3.32"))
    var callbackContentSource: Definition<CallbackContentSource> = { InMemoryCallbackContentSource() }
    var chainSource: Definition<ChainSource> = { InMemoryChainSource() }
    var messageSource: Definition<MessageSource> = { EmptyMessageSource() }
    var callbackDataDelimiter: Char = '|'
    var contentConverter: Definition<ContentConverter> = { JsonContentConverter() }
    var callbackSerializer: Definition<CallbackSerializer> = { SimpleCallbackSerializer(get(), get(), get(named("callbackDataDelimiter"))) }
    var htmlFormatter: Definition<HtmlFormatter> = { HtmlFormatterImpl() }
    var exceptionHandler: Definition<ExceptionHandler> = { ExceptionHandlerImpl(KoinPlatform.getKoin().get()) }
    var chainExceptionHandler: Definition<ChainExceptionHandler> = { ChainExceptionHandlerImpl() }

    fun longPolling(block: LongPollingConfig.() -> Unit) {
        updateReceiver = { LongPollingUpdateReceiver(LongPollingConfig().apply(block)) }
    }

    fun configureTemplating(block: Configuration.() -> Unit) {
        templateConfig.block()
    }

    fun handling(block: BotHandling.() -> Unit) {
        handling = block
    }
}
