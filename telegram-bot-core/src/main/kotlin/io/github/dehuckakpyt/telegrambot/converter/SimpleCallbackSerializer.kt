package io.github.dehuckakpyt.telegrambot.converter

import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import java.util.*

class SimpleCallbackSerializer(
    private val callbackContentSource: CallbackContentSource,
    private val dataConverter: ContentConverter,
    private val delimiter: Char,
) : CallbackSerializer {

    override suspend fun toCallback(chatId: Long, fromId: Long, next: String, instance: Any?): String {
        instance ?: return next

        val data = dataConverter.toContent(instance)

        if (data.length + next.length <= 62) {
            //will be string stepName|scallbackData (string)
            return "$next$delimiter${CallbackDataType.STRING}$data"
        }

        if (next.length > 26) throw RuntimeException("Callback step name should be less or equal than 26 symbols to put max 64 symbols. Because callback data will contain special markers (2 symbols) + UUID id (36 symbols) + callback name.")

        val sourceId = callbackContentSource.save(chatId, fromId, data).callbackId
        //will be string stepName|icallbackData (id)
        return "$next$delimiter${CallbackDataType.ID}$sourceId"
    }

    override suspend fun fromCallback(callbackData: String): CallbackDataInfo {
        val delimiterIndex = callbackData.indexOf(delimiter)
        if (delimiterIndex == -1) return CallbackDataInfo(callbackData)

        val next = callbackData.substring(0, delimiterIndex)
        val content = getContent(callbackData, delimiterIndex)

        return CallbackDataInfo(next, content)
    }

    override fun validateCallbackName(name: String) {
        if (name.contains(delimiter)) throw RuntimeException("Char \"$delimiter\" was used for split callback data. Please, change symbol in TelegramBotConfig.callbackDataDelimiter or rename callback step.")
    }

    private suspend fun getContent(callbackData: String, delimiterIndex: Int): String {
        val type = callbackData.substring(delimiterIndex + 1, delimiterIndex + 2)[0]
        val data = callbackData.substring(delimiterIndex + 2)

        return when (type) {
            CallbackDataType.STRING -> data
            CallbackDataType.ID -> callbackContentSource.get(UUID.fromString(data)).content
            else -> throw RuntimeException("Callback data can not be parsed. Unknown type '$type'")
        }
    }

    private object CallbackDataType {
        const val STRING = 's'
        const val ID = 'i'
    }
}