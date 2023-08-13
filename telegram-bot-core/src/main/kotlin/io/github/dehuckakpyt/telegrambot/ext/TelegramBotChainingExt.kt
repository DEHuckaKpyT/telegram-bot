package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.TelegramBotChaining


/**
 * Created on 25.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z]+)*)")

fun TelegramBotChaining.fetchCommand(input: String): String? {
    return commandRegex.find(input)?.groupValues?.get(1) ?: return null
}