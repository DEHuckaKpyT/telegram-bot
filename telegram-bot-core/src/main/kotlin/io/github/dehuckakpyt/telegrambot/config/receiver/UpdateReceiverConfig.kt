package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfig
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.strategy.invocation.HandlerInvocationStrategy
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate


/**
 * Created on 21.12.2023.
 *
 * Configuration for receiving updates.
 *
 * @author Denis Matytsin
 */
data class UpdateReceiverConfig(

    /** Source for saving callback.data */
    var callbackContentSource: (TelegramBotActualConfig.() -> CallbackContentSource)? = null,

    /** Source for saving chain state */
    var chainSource: (TelegramBotActualConfig.() -> ChainSource)? = null,

    /** Source for saving users */
    var telegramUserSource: (TelegramBotActualConfig.() -> TelegramUserSource)? = null,

    /** Source for saving chats (except private) */
    var telegramChatSource: (TelegramBotActualConfig.() -> TelegramChatSource)? = null,

    /** Source for saving all changes of bot`s status in all chats */
    var telegramChatStatusEventSource: (TelegramBotActualConfig.() -> TelegramChatStatusEventSource)? = null,

    /** Converter from object to string and back */
    var contentConverter: (TelegramBotActualConfig.() -> ContentConverter)? = null,

    /** Serializer from string to callback.data and back */
    var callbackSerializer: (TelegramBotActualConfig.() -> CallbackSerializer)? = null,

    /** Text templates for show to user when exception throws */
    var messageTemplate: (TelegramBotActualConfig.() -> MessageTemplate)? = null,

    /** Strategy for invoking BotHandler actions */
    var invocationStrategy: (TelegramBotActualConfig.() -> HandlerInvocationStrategy)? = null,

    /** Handler for catch internal exceptions */
    var exceptionHandler: (TelegramBotActualConfig.() -> ExceptionHandler)? = null,

    /** Handler for process exceptions in message chains */
    var chainExceptionHandler: (TelegramBotActualConfig.() -> ChainExceptionHandler)? = null,

    /** Receiver for getting and handling updates */
    var updateReceiver: (TelegramBotActualConfig.() -> UpdateReceiver)? = null,

    /** Handlers declaration */
    var handling: BotHandling.() -> Unit = {},

    /** Update handlers declaration */
    var updateHandling: BotUpdateHandling.() -> Unit = {},
)
