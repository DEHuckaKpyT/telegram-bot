package io.github.dehuckakpyt.telegrambot.factory.button

import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardButton
import io.github.dehuckakpyt.telegrambot.model.type.KeyboardButton


/**
 * Created on 25.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ButtonFactory {
    fun callbackButton(text: String, next: String): InlineKeyboardButton
    suspend fun Argument.callbackButton(text: String, next: String, content: Any): InlineKeyboardButton = callbackButton(chatId, from.id, text, next, content)
    suspend fun callbackButton(chatId: Long, fromId: Long, text: String, next: String, content: Any): InlineKeyboardButton
    fun contactButton(text: String): KeyboardButton = KeyboardButton(text, requestContact = true)
}