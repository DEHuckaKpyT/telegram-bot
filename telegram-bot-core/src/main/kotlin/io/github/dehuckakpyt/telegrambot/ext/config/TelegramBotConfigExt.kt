package io.github.dehuckakpyt.telegrambot.ext.config

import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.config.properties.TelegramBotProperties
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ResourceContent
import io.ktor.client.*
import io.ktor.client.engine.apache.*


/**
 * Created on 24.12.2024.
 *
 * @author Denis Matytsin
 */

/** Configure listening for react to telegram bot's events */
fun TelegramBotConfig.eventListening(preventDefaults: Boolean = false, block: TelegramBotEventListening.() -> Unit = {}) {
    eventListeningPreventDefaults = preventDefaults
    eventListening = block
}

/** Customize telegram bot's client */
fun TelegramBotConfig.client(block: HttpClientConfig<ApacheEngineConfig>.() -> Unit) {
    clientConfiguration = block
}

/**
 * Merge property-based configuration into this config.
 *
 * This method keeps already configured code values untouched.
 * It fills only missing values (code > properties).
 */
fun TelegramBotConfig.merge(properties: TelegramBotProperties): TelegramBotConfig {
    if (token == null) token = properties.token
    if (username == null) username = properties.username

    val longPolling = receiving.longPolling
    val longPollingProperties = properties.receiving?.longPolling
    if (longPolling.limit == null) longPolling.limit = longPollingProperties?.limit
    if (longPolling.timeout == null) longPolling.timeout = longPollingProperties?.timeout
    if (longPolling.retryDelay == null) longPolling.retryDelay = longPollingProperties?.retryDelay
    if (longPolling.gracefulShutdownTimeout == null) longPolling.gracefulShutdownTimeout = longPollingProperties?.gracefulShutdownTimeout

    val webhook = receiving.webhook
    val webhookProperties = properties.receiving?.webhook
    if (webhook.urlHost == null) webhook.urlHost = webhookProperties?.urlHost
    if (webhook.urlPath == null) webhook.urlPath = webhookProperties?.urlPath
    if (webhook.certificate == null) webhook.certificate = webhookProperties?.certificatePath?.let(::ResourceContent)
    if (webhook.ipAddress == null) webhook.ipAddress = webhookProperties?.ipAddress
    if (webhook.maxConnections == null) webhook.maxConnections = webhookProperties?.maxConnections
    if (webhook.dropPendingUpdates == null) webhook.dropPendingUpdates = webhookProperties?.dropPendingUpdates
    if (webhook.secretToken == null) webhook.secretToken = webhookProperties?.secretToken
    if (webhook.secretTokenRandomGeneration == null) webhook.secretTokenRandomGeneration = webhookProperties?.secretTokenRandomGeneration
    if (webhook.secretTokenRandomGenerationPrintOnStartup == null) webhook.secretTokenRandomGenerationPrintOnStartup = webhookProperties?.secretTokenRandomGenerationPrintOnStartup

    return this
}
