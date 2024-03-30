package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.TelegramBotReceiverActualConfig
import io.github.dehuckakpyt.telegrambot.config.template.TelegramBotTemplatingActualConfig
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBotActualConfig {
    val token: String
    val username: String
    val messageSource: MessageSource
    val telegramBot: TelegramBot
    val templating: TelegramBotTemplatingActualConfig
    val receiving: TelegramBotReceiverActualConfig
}