package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.TelegramBotReceiverActualConfig
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TelegramBotActualConfigImpl : TelegramBotActualConfig {
    override lateinit var token: String
    override lateinit var username: String
    override lateinit var messageSource: MessageSource
    override lateinit var telegramBot: TelegramBot
    override lateinit var templater: Templater
    override lateinit var receiving: TelegramBotReceiverActualConfig
}