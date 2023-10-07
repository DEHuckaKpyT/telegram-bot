package io.github.dehuckakpyt.telegrambot.plugin.config

import com.elbekd.bot.PollingOptions
import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
import io.github.dehuckakpyt.telegrambot.converter.SimpleCallbackSerializer
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatterImpl
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.InMemoryCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.InMemoryChainSource
import io.github.dehuckakpyt.telegrambot.source.message.EmptyMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.ktor.server.config.*


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class TelegramBotConfig(private val config: ApplicationConfig) {
    var enabled = config.propertyOrNull("enabled")?.getString()?.toBooleanStrict() ?: true
    var token = config.propertyOrNull("token")?.getString()
    var username = config.propertyOrNull("username")?.getString()
    var pollingOptions: PollingOptions.() -> Unit = { PollingOptions() }
    var configureBot: TelegramBot.() -> Unit = {}
    var handling: BotHandling.() -> Unit = {}
    var templateConfig = Configuration(Version("2.3.32"))
    var callbackContentSource: CallbackContentSource = InMemoryCallbackContentSource()
    var chainSource: ChainSource = InMemoryChainSource()
    var messageSource: MessageSource = EmptyMessageSource()
    var callbackDataDelimiter: Char = '|'
    var contentConverter: ContentConverter = JsonContentConverter()
    var callbackSerializer: CallbackSerializer =
        SimpleCallbackSerializer(callbackContentSource, contentConverter, callbackDataDelimiter)
    var htmlFormatter: HtmlFormatter = HtmlFormatterImpl()

    fun pollingOptions(block: PollingOptions.() -> Unit) {
        pollingOptions = block
    }

    fun configureBot(block: TelegramBot.() -> Unit) {
        configureBot = block
    }

    fun handling(block: BotHandling.() -> Unit) {
        handling = block
    }

    fun configureTemplating(block: Configuration.() -> Unit) {
        templateConfig.block()
    }
}
