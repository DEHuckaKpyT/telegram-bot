package io.github.dehuckakpyt.telegrambot.context

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.manager.chain.ChainManager
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * @author Denis Matytsin
 */
internal class TelegramBotContextImpl : TelegramBotContext {
    override lateinit var telegramBot: TelegramBot
    override lateinit var updateReceiver: UpdateReceiver
    override lateinit var botHandling: BotHandling
    override lateinit var botUpdateHandling: BotUpdateHandling
    override lateinit var templater: Templater
    override lateinit var telegramBotEventListening: TelegramBotEventListening
    override lateinit var buttonFactory: ButtonFactory
    override lateinit var inputFactory: InputFactory
    override lateinit var telegramUserSource: TelegramUserSource<out TelegramUser<out Any>>
    override lateinit var telegramMessageSource: TelegramMessageSource<out TelegramMessage<out Any>>
    override lateinit var telegramChatStatusEventSource: TelegramChatStatusEventSource<out TelegramChatStatusEvent<out Any>>
    override lateinit var telegramChatSource: TelegramChatSource<out TelegramChat<out Any>>
    override lateinit var callbackContentSource: CallbackContentSource
    override lateinit var chainSource: ChainSource
    override lateinit var chainManager: ChainManager
}