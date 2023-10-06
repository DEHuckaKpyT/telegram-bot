package io.github.dehuckakpyt.telegrambot.factory

import com.elbekd.bot.types.*


/**
 * Created on 06.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val removeKeyboard = ReplyKeyboardRemove(true)

fun inlineKeyboard(button: InlineKeyboardButton): ReplyKeyboard {
    return InlineKeyboardMarkup(listOf(listOf(button)))
}

fun inlineKeyboard(vararg buttons: InlineKeyboardButton): ReplyKeyboard {
    return buttons.map(::listOf).run(::InlineKeyboardMarkup)
}

fun contactKeyboard(text: String): ReplyKeyboard {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestContact = true))))
}

fun removeKeyboard(): ReplyKeyboard = removeKeyboard