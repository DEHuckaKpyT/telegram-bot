package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.handling.BotUpdateHandling


/**
 * Created on 07.11.2024.
 *
 * @author Denis Matytsin
 */

/** Activate and configure long polling for receiving updates */
fun UpdateReceiverConfig.longPolling(block: LongPollingConfig.() -> Unit) {
    mode = ReceivingMode.LONG_POLLING
    longPolling.apply(block)
}

/** Activate and configure webhook for receiving updates */
fun UpdateReceiverConfig.webhook(block: WebhookConfig.() -> Unit) {
    mode = ReceivingMode.WEBHOOK
    webhook.apply(block)
}

/** Declare chain handlers */
fun UpdateReceiverConfig.handling(block: BotHandling.() -> Unit) {
    handling = block
}

/** Declare any update handlers */
fun UpdateReceiverConfig.updateHandling(block: BotUpdateHandling.() -> Unit) {
    updateHandling = block
}