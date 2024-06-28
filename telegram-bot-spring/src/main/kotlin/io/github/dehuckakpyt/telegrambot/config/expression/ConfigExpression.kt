package io.github.dehuckakpyt.telegrambot.config.expression

import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfig


/**
 * Created on 24.06.2024.
 *
 * @author Denis Matytsin
 */
fun interface ConfigExpression<T> {
    fun configure(actual: TelegramBotActualConfig): T
}