package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
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

    override suspend fun get(id: UUID): CallbackContent {
        return contentById[id] ?: throw ChatException("Содержание для callback'а не найдено :(")
    }
}