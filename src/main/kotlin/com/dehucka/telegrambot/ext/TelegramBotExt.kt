package com.dehucka.telegrambot.ext

import com.dehucka.microservice.ext.shortMapper
import com.dehucka.telegrambot.BotHandling
import com.elbekd.bot.types.InlineKeyboardButton
import com.elbekd.bot.types.InlineKeyboardMarkup


/**
 * Created on 23.07.2023.
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

            val sourceId = callbackContentSource.save(value).id.value
            "$nextStep$callbackDataDelimiter$sourceId"
        }
    }
}

suspend fun BotHandling.callbackButton(text: String, nextStep: String, content: Any): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = callbackData(nextStep, content))
}

suspend fun BotHandling.callbackButton(text: String, nextStep: String): InlineKeyboardButton {
    return InlineKeyboardButton(text, callbackData = "$nextStep$callbackDataDelimiter")
}

suspend fun BotHandling.inlineKeyboard(button: InlineKeyboardButton): InlineKeyboardMarkup {
    return InlineKeyboardMarkup(listOf(listOf(button)))
}

suspend fun BotHandling.inlineKeyboard(vararg buttons: InlineKeyboardButton): InlineKeyboardMarkup {
    return buttons.map {
        listOf(it)
    }.let {
        InlineKeyboardMarkup(it)
    }
}