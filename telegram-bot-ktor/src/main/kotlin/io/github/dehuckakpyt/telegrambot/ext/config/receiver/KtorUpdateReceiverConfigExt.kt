package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.receiver.WebhookUpdateReceiver


/**
 * Created on 07.11.2024.
 *
 * @author Denis Matytsin
 */

/** Activate and configure webhook for receiving updates */
fun UpdateReceiverConfig.webhook(block: WebhookConfig.() -> Unit) {
    val webhookConfig = WebhookConfig().apply(block)
    updateReceiver = { WebhookUpdateReceiver(telegramBot, receiving.updateResolver, webhookConfig) }
}