package io.github.dehuckakpyt.telegrambot.ext

import com.dehucka.microservice.ext.shortMapper
import com.dehucka.microservice.ext.toUUID
import com.elbekd.bot.types.*
import io.github.dehuckakpyt.telegrambot.BotChaining
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.ext.CallbackDataType.ID
import io.github.dehuckakpyt.telegrambot.ext.CallbackDataType.STRING


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object CallbackDataType {
    const val STRING = 's'
    const val ID = 'i'
}

suspend fun BotHandling.callbackData(nextStep: String, content: Any): String {
    if (nextStep.contains(callbackDataDelimiter)) throw RuntimeException("Char \"$callbackDataDelimiter\" was used for split callback data. Please, change symbol in TelegramBotConfig.callbackDataDelimiter or rename callback step.")

    val callbackValue = shortMapper.writeValueAsString(content)

    return if (callbackValue.length + nextStep.length <= 62) {
        //will be string stepName|scallbackData (string)
        "$nextStep$callbackDataDelimiter$STRING$callbackValue"
    } else {
        if (nextStep.length > 26) throw RuntimeException("Callback step name should be less or equal than 26 symbols to put max 64 symbols. Because callback data will contain special markers (2 symbols) + UUID id (36 symbols) + callback name.")

        val sourceId = callbackContentSource.save(callbackValue).identifier
        //will be string stepName|icallbackData (id)
        "$nextStep$callbackDataDelimiter$ID$sourceId"
    }
}

suspend fun BotChaining.callbackContent(callbackData: String): String? {
    if (callbackData.isEmpty()) return null
    val callbackContentType = callbackData[0]

    return when (callbackContentType) {
        STRING -> callbackData.substring(1, callbackData.length)
        ID -> callbackContentSource.get(callbackData.substring(1, callbackData.length).toUUID()).content
        else -> throw RuntimeException("Callback data can not be parsed.")
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
    return buttons.map(::listOf).let(::InlineKeyboardMarkup)
}

suspend fun BotHandling.contactButton(text: String): ReplyKeyboard {
    return ReplyKeyboardMarkup(listOf(listOf(KeyboardButton(text, requestContact = true))))
}

suspend fun BotHandling.removeKeyboard(): ReplyKeyboard {
    return ReplyKeyboardRemove(true)
}

