package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
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
interface TelegramBotReceiverActualConfig {
    val callbackContentSource: CallbackContentSource
    val chainSource: ChainSource
    val telegramUserSource: TelegramUserSource<out TelegramUser>
    val telegramChatSource: TelegramChatSource<out TelegramChat>
    val telegramChatStatusEventSource: TelegramChatStatusEventSource<out TelegramChatStatusEvent>
    val contentConverter: ContentConverter
    val callbackSerializer: CallbackSerializer
    val messageTemplate: MessageTemplate
    val invocationStrategy: HandlerInvocationStrategy
    val exceptionHandler: ExceptionHandler
    val chainExceptionHandler: ChainExceptionHandler
    val updateResolver: UpdateResolver
}