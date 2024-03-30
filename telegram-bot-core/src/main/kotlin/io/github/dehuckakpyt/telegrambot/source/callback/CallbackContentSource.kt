package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import java.util.*


/**
 * Created on 23.07.2023.
 *
 * Interface for saving long callback data.
 *
 * @author Denis Matytsin
 */
interface CallbackContentSource {
    /**
     * Save callback stringified object.
     *
     * Params chatId and fromId need for filter contents from user.
     * For example, in DatabaseCallbackContentSource they used for save only N contents per user.
     *
     * @param chatId from which chat
     * @param fromId from which user (in private chats equals to chatId)
     * @param content stringified object for transfer (defaults json from JsonContentConverter)
     *
     * @return object with info about saved content (with generated callbackId)
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent

    /**
     * Get callback info by its id.
     *
     * @param callbackId id of saved content
     *
     * @return object with info about saved content
     */
    suspend fun get(callbackId: UUID): CallbackContent

    companion object
}