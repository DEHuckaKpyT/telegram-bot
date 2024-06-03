package io.github.dehuckakpyt.telegrambot.factory

import io.github.dehuckakpyt.telegrambot.model.telegram.*


/**
 * Created on 06.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val removeKeyboard = ReplyKeyboardRemove(true)

fun inlineKeyboard(button: InlineKeyboardButton): InlineKeyboardMarkup {
    return InlineKeyboardMarkup(listOf(listOf(button)))
}

fun inlineKeyboard(vararg buttons: InlineKeyboardButton): InlineKeyboardMarkup {
    return buttons.map(::listOf).run(::InlineKeyboardMarkup)
}

fun contactKeyboard(text: String): ReplyKeyboardMarkup {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestContact = true))))
}

fun locationKeyboard(text: String): ReplyKeyboardMarkup {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestLocation = true))))
}

fun removeKeyboard(): ReplyKeyboardRemove = removeKeyboard