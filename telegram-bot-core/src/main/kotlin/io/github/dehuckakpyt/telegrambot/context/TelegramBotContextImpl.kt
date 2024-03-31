package io.github.dehuckakpyt.telegrambot.context

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TelegramBotContextImpl : TelegramBotContext {
    override lateinit var telegramBot: TelegramBot
    override lateinit var updateReceiver: UpdateReceiver
    override lateinit var botHandling: BotHandling
    override lateinit var botUpdateHandling: BotUpdateHandling
    override lateinit var templater: Templater
    override lateinit var buttonFactory: ButtonFactory
    override lateinit var messageSource: MessageSource
    override lateinit var callbackContentSource: CallbackContentSource
    override lateinit var chainSource: ChainSource
}