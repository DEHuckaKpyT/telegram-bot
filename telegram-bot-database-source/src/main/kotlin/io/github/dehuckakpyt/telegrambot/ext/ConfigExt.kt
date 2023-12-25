package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.config.DatabaseSourceConfig
import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun TelegramBotConfig.databaseSource(block: DatabaseSourceConfig.() -> Unit) {
    DatabaseSourceConfig().apply(block)
}