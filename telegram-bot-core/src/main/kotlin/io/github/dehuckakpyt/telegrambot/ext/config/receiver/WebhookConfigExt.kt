package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.constants.receiver.WebhookUpdateReceiverConstant.WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.WebhookUpdateReceiverConstant.WEBHOOK_RECEIVER_DEFAULT_URL_PATH
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig.SecretTokenRandomGeneration.NONE
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig.SecretTokenRandomGeneration.RANDOM256CHARS
import io.github.dehuckakpyt.telegrambot.factory.string.StringFactory
import io.ktor.http.*


/**
 * @author Denis Matytsin
 */
public val WebhookConfig.urlPathOrDefault: String get() = urlPath ?: WEBHOOK_RECEIVER_DEFAULT_URL_PATH

public fun WebhookConfig.getValidUrl(): String {
    val url = buildUrl {
        takeFrom(urlHost ?: throw IllegalArgumentException("WebhookConfig.urlHost is required."))
        path(urlPathOrDefault)
    }

    if (url.protocol != URLProtocol.HTTPS) throw IllegalArgumentException("WebhookConfig.urlHost must be https.")

    return url.toString()
}

public fun WebhookConfig.getSecretTokenOrGenerateOrNull(): String? {
    secretToken?.let { return it }

    return when (secretTokenRandomGeneration ?: WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION) {
        NONE -> null
        RANDOM256CHARS -> StringFactory.randomString(allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + '_' + '-', length = 256)
    }
}
