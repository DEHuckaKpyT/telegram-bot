package io.github.dehuckakpyt.telegrambot.context

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
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

    /** Factory for creating buttons. */
    val buttonFactory: ButtonFactory
}