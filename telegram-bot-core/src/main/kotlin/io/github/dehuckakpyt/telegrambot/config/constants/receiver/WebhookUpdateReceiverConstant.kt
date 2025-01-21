package io.github.dehuckakpyt.telegrambot.config.constants.receiver

import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig.SecretTokenRandomGeneration.RANDOM256CHARS


/**
 * @author Denis Matytsin
 */
object WebhookUpdateReceiverConstant {

    public const val WEBHOOK_RECEIVER_DEFAULT_URL_PATH = "/updates/receive"
    public val WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION = RANDOM256CHARS
}