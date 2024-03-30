package io.github.dehuckakpyt.telegrambot.converter

import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import java.util.*

/**
 * Created on 04.10.2023.
 *
 * Simplest serializer for callback any length.
 *
 * There can be only three options:
 * 1. If you don't need to pass additional info, then callback data will just contain step_name. For example, callback.data = "step_name".
 * 2. If you need to pass a small object, it will be converted to a string (json by default) and callback.data will be like "step_name|s{"field":"value"}".
 * 3. If you need to pass a large object, it will be converted to a string, saved in callbackContentSource, and callback.data will contain the id of this object. For example, callback.data = "step_name|i00000001-0000-0000-0000-000000000001".
 * Accordingly, if you pass a large object, then the maximum length of the step name should be equal to 26: step_name (26 symbols) + |i (2 symbols) + uuid (36 symbols) = 64 symbols
 *
 * @author Denis Matytsin
 */
class SimpleCallbackSerializer(
    private val callbackContentSource: CallbackContentSource,
    private val dataConverter: ContentConverter,
    private val delimiter: Char,
) : CallbackSerializer {

    /**
     * Serialize string to callback.data.
     *
     * @param chatId for which chat
     * @param fromId for which user (in private chats equals to chatId)
     * @param next name of the next step for chatId and fromId
     * @param instance an object for transfer to the next step
     *
     * @return callback.data
     */
    override suspend fun toCallback(chatId: Long, fromId: Long, next: String, instance: Any?): String {
        instance ?: return next

        val data = dataConverter.toContent(instance)

        if (data.length + next.length <= 62) {
            //will be string stepName|scallbackData (string)
            return "$next$delimiter${CallbackDataType.STRING}$data"
        }

        if (next.length > 26) throw IllegalArgumentException("Callback step name should be less or equal than 26 symbols to put max 64 symbols. Because callback data will contain special markers (2 symbols) + UUID id (36 symbols) + callback name.")

        val sourceId = callbackContentSource.save(chatId, fromId, data).callbackId
        //will be string stepName|icallbackData (id)
        return "$next$delimiter${CallbackDataType.ID}$sourceId"
    }

    /**
     * Deserialize callback.data to string.
     *
     * @param callbackData incoming string from callback.data
     *
     * @return step name and nullable object
     */
    override suspend fun fromCallback(callbackData: String): CallbackDataInfo {
        val delimiterIndex = callbackData.indexOf(delimiter)
        if (delimiterIndex == -1) return CallbackDataInfo(callbackData)

        val next = callbackData.substring(0, delimiterIndex)
        val content = getContent(callbackData, delimiterIndex)

        return CallbackDataInfo(next, content)
    }

    /**
     * Check that callback name is valid.
     * In this implementation callback name must not contain delimiter from config (default special symbol is '|').
     *
     * @param name name of the callback in handler
     *
     * @throws exception if name is not valid
     */
    override fun validateCallbackName(name: String) {
        if (name.contains(delimiter)) throw IllegalArgumentException("Char \"$delimiter\" was used for split callback data. Please, change symbol in TelegramBotConfig.callbackDataDelimiter or rename callback step.")
    }

    private suspend fun getContent(callbackData: String, delimiterIndex: Int): String {
        val type = callbackData.substring(delimiterIndex + 1, delimiterIndex + 2)[0]
        val data = callbackData.substring(delimiterIndex + 2)

        return when (type) {
            CallbackDataType.STRING -> data
            CallbackDataType.ID -> callbackContentSource.get(UUID.fromString(data)).content
            else -> throw IllegalArgumentException("Callback data can not be parsed. Unknown type '$type'")
        }
    }

    private object CallbackDataType {
        const val STRING = 's'
        const val ID = 'i'
    }
}