package io.github.dehuckakpyt.telegrambot.context

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
import io.github.dehuckakpyt.telegrambot.factory.input.InputFactory
import io.github.dehuckakpyt.telegrambot.factory.keyboard.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *
 * Isolated context for telegram bot instance.
 *
 * Isolated means that you can create few contexts and use them independently of each other.
 *
 * @author Denis Matytsin
 */
interface TelegramBotContext {

    /** Telegram bot for making requests. */
    val telegramBot: TelegramBot

    /** Updates receiver for react to users actions. */
    val updateReceiver: UpdateReceiver

    /** Handler for declare dialog actions to react on updates. */
    val botHandling: BotHandling

    /** Handler for declare actions to react on updates. */
    val botUpdateHandling: BotUpdateHandling

    /** Factory for make text by template. */
    val templater: Templater

    /** Listening for react to telegram bot's events. */
    val telegramBotEventListening: TelegramBotEventListening

    /** Factory for creating buttons. */
    val buttonFactory: ButtonFactory

    /** Factory for creating InputFile. */
    val inputFactory: InputFactory

    /** Interface for saving users. */
    val telegramUserSource: TelegramUserSource<out TelegramUser>

    /** Interface for saving messages. */
    val telegramMessageSource: TelegramMessageSource<out TelegramMessage>

    /** Interface for saving the state of the dialog between the user and the bot. */
    val chainSource: ChainSource

    /** Interface for saving long callback data. */
    val callbackContentSource: CallbackContentSource
}