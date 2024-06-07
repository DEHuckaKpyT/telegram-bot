package io.github.dehuckakpyt.telegrambot.ext.update.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 20.04.2024.
 *
 * @author Denis Matytsin
 */
val Message.chatId: Long get() = chat.id

internal fun fetchCommand(text: String?, username: String): String? {
    text ?: return null

    val find = commandRegex.find(text) ?: return null
    val groups = find.groups

    val command = groups[1]?.value ?: return null

    val usernameActual = groups[2]?.value
    if (usernameActual != null && usernameActual != username) return null

    return command
}

private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*)(?:__[a-zA-Z0-9-_]+)?(?:@([a-zA-Z_]+))?")