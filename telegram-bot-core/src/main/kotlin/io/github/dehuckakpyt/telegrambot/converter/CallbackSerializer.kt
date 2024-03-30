package io.github.dehuckakpyt.telegrambot.converter


/**
 * Created on 04.10.2023.
 *
 * Serializer for callbacks.
 *
 * @author Denis Matytsin
 */
interface CallbackSerializer {

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
    suspend fun toCallback(chatId: Long, fromId: Long, next: String, instance: Any?): String

    /**
     * Deserialize callback.data to string.
     *
     * @param callbackData incoming string from callback.data
     *
     * @return step name and nullable object
     */
    suspend fun fromCallback(callbackData: String): CallbackDataInfo

    /**
     * Check that callback name is valid.
     * For example, don`t contains special symbols (like default special symbol is '|').
     *
     * @param name name of the callback in handler
     *
     * @throws exception if name is not valid
     */
    fun validateCallbackName(name: String)
}