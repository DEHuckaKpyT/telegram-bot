package io.github.dehuckakpyt.telegrambot.ext.config.receiver

import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening


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