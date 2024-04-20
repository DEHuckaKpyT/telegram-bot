package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 20.04.2024.
 *
 * @author Denis Matytsin
 */
val Message.chatId: Long
    get() = chat.id
