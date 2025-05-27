package io.github.dehuckakpyt.telegrambot.ext.config

import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
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
