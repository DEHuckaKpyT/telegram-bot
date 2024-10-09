package io.github.dehuckakpyt.telegrambot.factory.keyboard

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

fun replyKeyboard(
    button: KeyboardButton,
    isPersistent: Boolean? = null,
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    keyboard = listOf(listOf(button)),
    isPersistent = isPersistent,
    resizeKeyboard = resizeKeyboard,
    oneTimeKeyboard = oneTimeKeyboard,
    inputFieldPlaceholder = inputFieldPlaceholder,
    selective = selective,
)

fun replyKeyboard(
    vararg buttons: KeyboardButton,
    isPersistent: Boolean? = null,
    resizeKeyboard: Boolean? = null,
    oneTimeKeyboard: Boolean? = null,
    inputFieldPlaceholder: String? = null,
    selective: Boolean? = null,
): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    keyboard = buttons.map(::listOf),
    isPersistent = isPersistent,
    resizeKeyboard = resizeKeyboard,
    oneTimeKeyboard = oneTimeKeyboard,
    inputFieldPlaceholder = inputFieldPlaceholder,
    selective = selective,
)

fun contactKeyboard(text: String): ReplyKeyboardMarkup {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestContact = true))))
}

fun locationKeyboard(text: String): ReplyKeyboardMarkup {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestLocation = true))))
}

fun webAppKeyboard(text: String, webApp: WebAppInfo): ReplyKeyboardMarkup {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, webApp = webApp))))
}

fun removeKeyboard(): ReplyKeyboardRemove = removeKeyboard