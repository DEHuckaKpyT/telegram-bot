package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.model.type.CallbackQuery


/**
 * Created on 20.04.2024.
 *
 * @author Denis Matytsin
 */
val CallbackQuery.chatId: Long
    get() = message?.chat?.id ?: from.id
