package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfig
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
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
    var callbackContentSource: (TelegramBotActualConfig.() -> CallbackContentSource)? = null,
    var chainSource: (TelegramBotActualConfig.() -> ChainSource)? = null,
    var callbackDataDelimiter: Char = '|',
    var contentConverter: (TelegramBotActualConfig.() -> ContentConverter)? = null,
    var callbackSerializer: (TelegramBotActualConfig.() -> CallbackSerializer)? = null,
    var messageTemplate: (TelegramBotActualConfig.() -> MessageTemplate)? = null,
    var exceptionHandler: (TelegramBotActualConfig.() -> ExceptionHandler)? = null,
    var chainExceptionHandler: (TelegramBotActualConfig.() -> ChainExceptionHandler)? = null,
) {
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