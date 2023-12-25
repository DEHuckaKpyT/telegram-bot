package io.github.dehuckakpyt.telegrambot.config.receiver

import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate


/**
 * Created on 21.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class UpdateReceiverConfig(
    var callbackContentSource: CallbackContentSource? = null,
    var chainSource: ChainSource? = null,
    var callbackDataDelimiter: Char? = null,
    var contentConverter: ContentConverter? = null,
    var callbackSerializer: CallbackSerializer? = null,
    var messageTemplate: MessageTemplate? = null,
    var exceptionHandler: ExceptionHandler? = null,
    var chainExceptionHandler: ChainExceptionHandler? = null,
) {
    internal val freemarkerConfig: Configuration = Configuration(Version("2.3.32"))
    internal var htmlFormatter: HtmlFormatter? = null
    internal var handling: BotHandling.() -> Unit = {}
    internal var updateReceiver: ((TelegramBot, UpdateResolver) -> UpdateReceiver)? = null
    fun longPolling(block: LongPollingConfig.() -> Unit) {
        val longPollingConfig = LongPollingConfig().apply(block)
        updateReceiver = { telegramBot, updateResolver -> LongPollingUpdateReceiver(telegramBot, updateResolver, longPollingConfig) }
    }

    fun handling(block: BotHandling.() -> Unit) {
        handling = block
    }
}