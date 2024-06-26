package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContentImpl
import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class InMemoryCallbackContentSource : CallbackContentSource {

    private val contentById: MutableMap<UUID, CallbackContent> = mutableMapOf()

    override suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent {
        val id = UUID.randomUUID()
        val callbackContent = CallbackContentImpl(id, chatId, fromId, content)

        contentById[id] = callbackContent

        return callbackContent
    }

    override suspend fun get(callbackId: UUID): CallbackContent {
        return contentById[callbackId] ?: throw RuntimeException("No content was found for the callback :( Нou might have forgotten that restarting the program erases all callbacks. Recommended to use the implementation with the database.")
    }
}