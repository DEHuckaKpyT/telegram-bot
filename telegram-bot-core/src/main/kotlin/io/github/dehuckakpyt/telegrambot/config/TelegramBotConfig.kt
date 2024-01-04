package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.config.template.TelegramBotTemplatingConfig
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBotConfig(
    var token: String? = null,
    var username: String? = null,
    var messageSource: (TelegramBotActualConfig.() -> MessageSource)? = null,
) {
    internal var templating: TelegramBotTemplatingConfig.() -> Unit = {}
    internal var receiving: UpdateReceiverConfig.() -> Unit = {}

    fun templating(block: TelegramBotTemplatingConfig.() -> Unit) {
        templating = block
    }

    fun receiving(block: UpdateReceiverConfig.() -> Unit) {
        receiving = block
    }
}