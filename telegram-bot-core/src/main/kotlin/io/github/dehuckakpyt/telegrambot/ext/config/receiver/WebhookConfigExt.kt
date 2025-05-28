package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.constants.receiver.WebhookUpdateReceiverConstant.WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.WebhookUpdateReceiverConstant.WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION_PRINT_ON_STARTUP
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.WebhookUpdateReceiverConstant.WEBHOOK_RECEIVER_DEFAULT_URL_PATH
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig.SecretTokenRandomGeneration.*
import io.github.dehuckakpyt.telegrambot.factory.string.StringFactory
import org.slf4j.LoggerFactory
import java.util.*


/**
 * @author Denis Matytsin
 */
private val logger = LoggerFactory.getLogger("io.github.dehuckakpyt.telegrambot.ext.config.receiver.WebhookConfigExt")

public val WebhookConfig.urlPathOrDefault: String get() = urlPath ?: WEBHOOK_RECEIVER_DEFAULT_URL_PATH
public val WebhookConfig.secretTokenRandomGenerationPrintOnStartupOrDefault: Boolean get() = secretTokenRandomGenerationPrintOnStartup ?: WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION_PRINT_ON_STARTUP

public fun WebhookConfig.getValidUrl(): String {
    val urlHost = urlHost ?: throw IllegalArgumentException("WebhookConfig.urlHost is required.")
    val urlPath = urlPathOrDefault
    val url = urlHost + urlPath

    if (url.startsWith("https").not()) throw IllegalArgumentException("WebhookConfig.urlHost must be https.")

    logger.info("Configured webhook url is '$url' (api part is '$urlPath').")

    return url
}

public fun WebhookConfig.getSecretTokenOrGenerateOrNull(printGenerated: Boolean = false): String? {
    secretToken?.let { return it }

    val token = when (secretTokenRandomGeneration ?: WEBHOOK_RECEIVER_DEFAULT_SECRET_TOKEN_RANDOM_GENERATION) {
        NONE -> return null
        RANDOM_UUID -> UUID.randomUUID().toString()
        RANDOM_256_CHARS -> StringFactory.randomString(allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9'), length = 256)
    }

    if (printGenerated) {
        logger.info("Generated secret token is '$token'.")
    }

    return token
}
