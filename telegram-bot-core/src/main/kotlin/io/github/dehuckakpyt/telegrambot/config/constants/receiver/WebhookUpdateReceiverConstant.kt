package io.github.dehuckakpyt.telegrambot.config.constants.receiver

import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig.SecretTokenRandomGeneration.RANDOM_UUID


/**
 * @author Denis Matytsin
 */
object WebhookUpdateReceiverConstant {

    public const val WEBHOOK_RECEIVER_DEFAULT_URL_PATH = "/updates/receive"
    public val WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION = RANDOM_UUID
    public const val WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION_PRINT_ON_STARTUP = false
}