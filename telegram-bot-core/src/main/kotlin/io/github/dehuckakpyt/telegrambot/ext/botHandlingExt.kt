package io.github.dehuckakpyt.telegrambot.ext

import com.dehucka.microservice.ext.shortMapper
import com.elbekd.bot.types.*
import io.github.dehuckakpyt.telegrambot.BotHandling


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
suspend fun BotHandling.callbackData(nextStep: String, content: Any): String {
    return shortMapper.writeValueAsString(content).let { value ->
        if (value.length + nextStep.length + 1 <= 64) {
            "$nextStep$callbackDataDelimiter$value"
        } else {
            if (nextStep.length > 27) throw RuntimeException("Название шага должно быть не больше 27 символов, чтобы в callback поместилось название шага и uuid")

            val sourceId = callbackContentSource.save(value).identifier
            "$nextStep$callbackDataDelimiter$sourceId"
        }
    }
}

suspend fun BotHandling.callbackButton(text: String, next: String, content: Any): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = callbackData(next, content))
}

suspend fun BotHandling.callbackButton(text: String, next: String): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = "$next$callbackDataDelimiter")
}

suspend fun BotHandling.inlineKeyboard(button: InlineKeyboardButton): ReplyKeyboard {
    return InlineKeyboardMarkup(listOf(listOf(button)))
}

suspend fun BotHandling.inlineKeyboard(vararg buttons: InlineKeyboardButton): ReplyKeyboard {
    return buttons.map {
        listOf(it)
    }.let {
        InlineKeyboardMarkup(it)
    }
}

suspend fun BotHandling.contactButton(text: String): ReplyKeyboard {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestContact = true))))
}

suspend fun BotHandling.removeKeyboard(): ReplyKeyboard {
    return ReplyKeyboardRemove(true)
}

