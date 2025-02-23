package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.strategy.invocation.HandlerInvocationStrategy
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TelegramBotReceiverActualConfigImpl : TelegramBotReceiverActualConfig {
    override lateinit var callbackContentSource: CallbackContentSource
    override lateinit var chainSource: ChainSource
    override lateinit var telegramUserSource: TelegramUserSource
    override lateinit var telegramChatSource: TelegramChatSource
    override lateinit var telegramChatStatusEventSource: TelegramChatStatusEventSource
    override lateinit var contentConverter: ContentConverter
    override lateinit var callbackSerializer: CallbackSerializer
    override lateinit var messageTemplate: MessageTemplate
    override lateinit var invocationStrategy: HandlerInvocationStrategy
    override lateinit var exceptionHandler: ExceptionHandler
    override lateinit var chainExceptionHandler: ChainExceptionHandler
}