package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 17.07.2023.
 *
 * Configuration for telegram bot context.
 *
 * IMPORTANT: Values in ...ActualConfig are filling in strict order.
 * It means that you can`t use instance from config, which is not yet initialized.
 * Limitation due to the fact that no DI is used.
 *
 * Order of instances:
 * - TelegramBotActualConfig.token
 * - TelegramBotActualConfig.username
 * - TelegramBotActualConfig.messageSource
 * - TelegramBotActualConfig.telegramBot
 * - TelegramBotTemplatingActualConfig.templater
 * - TelegramBotReceiverActualConfig.messageTemplate
 * - TelegramBotReceiverActualConfig.contentConverter
 * - TelegramBotReceiverActualConfig.callbackContentSource
 * - TelegramBotReceiverActualConfig.chainSource
 * - TelegramBotReceiverActualConfig.callbackSerializer
 * - TelegramBotReceiverActualConfig.exceptionHandler
 * - TelegramBotReceiverActualConfig.chainExceptionHandler
 *
 * @author Denis Matytsin
 */
class TelegramBotConfig(

    /** Telegram bot token */
    var token: String? = null,

    /** Telegram bot username */
    var username: String? = null,

    /** Source for saving messages */
    var messageSource: (TelegramBotActualConfig.() -> MessageSource)? = null,

    /** Templater for build message templates */
    var templater: (TelegramBotActualConfig.() -> Templater)? = null,
) {
    var receiving: UpdateReceiverConfig = UpdateReceiverConfig()

    /** Configure receiving */
    fun receiving(block: UpdateReceiverConfig.() -> Unit) {
        receiving.apply(block)
    }
}