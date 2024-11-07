package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling
import io.github.dehuckakpyt.telegrambot.receiver.LongPollingUpdateReceiver


/**
 * Created on 07.11.2024.
 *
 * @author Denis Matytsin
 */

/** Activate and configure long polling for receiving updates */
fun UpdateReceiverConfig.longPolling(block: LongPollingConfig.() -> Unit) {
    val longPollingConfig = LongPollingConfig().apply(block)
    updateReceiver = { updateResolver -> LongPollingUpdateReceiver(telegramBot, updateResolver, longPollingConfig) }
}

/** Declare chain handlers */
fun UpdateReceiverConfig.handling(block: BotHandling.() -> Unit) {
    handling = block
}

/** Declare any update handlers */
fun UpdateReceiverConfig.updateHandling(block: BotUpdateHandling.() -> Unit) {
    updateHandling = block
}