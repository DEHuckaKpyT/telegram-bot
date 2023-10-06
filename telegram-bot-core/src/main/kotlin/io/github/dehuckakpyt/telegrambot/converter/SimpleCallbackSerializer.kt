package io.github.dehuckakpyt.telegrambot.converter

import com.dehucka.microservice.ext.toUUID
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import org.koin.core.component.KoinComponent

class SimpleCallbackSerializer(
    private val callbackContentSource: CallbackContentSource,
    private val dataConverter: ContentConverter,
    private val delimiter: Char
) : CallbackSerializer, KoinComponent {

    override suspend fun toCallback(next: String, instance: Any?): String {
        instance ?: return next

        val data = dataConverter.toContent(instance)

        if (data.length + next.length <= 62) {
            //will be string stepName|scallbackData (string)
            return "$next$delimiter${CallbackDataType.STRING}$data"
        }

        if (next.length > 26) throw RuntimeException("Callback step name should be less or equal than 26 symbols to put max 64 symbols. Because callback data will contain special markers (2 symbols) + UUID id (36 symbols) + callback name.")

        val sourceId = callbackContentSource.save(data).identifier
        //will be string stepName|icallbackData (id)
        return "$next$delimiter${CallbackDataType.ID}$sourceId"
    }

    override suspend fun getNext(callbackData: String): String {
        val delimiterIndex = callbackData.indexOf(delimiter)

        return if (delimiterIndex == -1) callbackData
        else callbackData.substring(0, delimiterIndex)
    }

    override suspend fun getContent(callbackData: String): String? {
        val delimiterIndex = callbackData.indexOf(delimiter)
        if (delimiterIndex == -1) return null

        val type = callbackData.substring(delimiterIndex + 1, delimiterIndex + 2)[0]
        val data = callbackData.substring(delimiterIndex + 2)

        return when (type) {
            CallbackDataType.STRING -> data
            CallbackDataType.ID -> callbackContentSource.get(data.toUUID()).content
            else -> throw RuntimeException("Callback data can not be parsed.")
        }
    }

    override fun validateCallbackName(name: String) {
        if (name.contains(delimiter)) throw RuntimeException("Char \"$delimiter\" was used for split callback data. Please, change symbol in TelegramBotConfig.callbackDataDelimiter or rename callback step.")
    }

    private object CallbackDataType {
        const val STRING = 's'
        const val ID = 'i'
    }
}