package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBotConfig {
    var token: String? = null
    var username: String? = null
    var messageSource: MessageSource? = null

    internal var receiving: UpdateReceiverConfig.(TelegramBot) -> Unit = {}

    fun receiving(block: UpdateReceiverConfig.(TelegramBot) -> Unit) {
        receiving = block
    }
}
