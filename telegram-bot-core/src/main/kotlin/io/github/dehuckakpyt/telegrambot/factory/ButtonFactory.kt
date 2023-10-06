package io.github.dehuckakpyt.telegrambot.factory

import com.elbekd.bot.types.InlineKeyboardButton
import com.elbekd.bot.types.KeyboardButton
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import org.koin.mp.KoinPlatformTools


/**
 * Created on 06.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val callbackSerializer = KoinPlatformTools.defaultContext().get().get<CallbackSerializer>()

fun callbackButton(text: String, next: String): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = next)
}

suspend fun callbackButton(text: String, next: String, content: Any): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = callbackSerializer.toCallback(next, content))
}

fun contactButton(text: String): KeyboardButton = KeyboardButton(text, requestContact = true)