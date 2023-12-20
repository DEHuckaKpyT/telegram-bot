package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardButton
import io.github.dehuckakpyt.telegrambot.model.type.KeyboardButton


/**
 * Created on 06.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val callbackSerializer = InternalKoinContext.koin.get<CallbackSerializer>()

fun callbackButton(text: String, next: String): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = next)
}

suspend fun Argument.callbackButton(text: String, next: String, content: Any): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = callbackSerializer.toCallback(chatId, from.id, next, content))
}

suspend fun callbackButton(chatId: Long, fromId: Long, text: String, next: String, content: Any): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = callbackSerializer.toCallback(chatId, fromId, next, content))
}

fun contactButton(text: String): KeyboardButton = KeyboardButton(text, requestContact = true)