package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.model.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.CallbackContentImpl
import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class InMemoryCallbackContentSource : CallbackContentSource {

    private val contentByIdentifier: MutableMap<UUID, CallbackContent> = mutableMapOf()

    override suspend fun save(content: String): CallbackContent {
        val identifier = UUID.randomUUID()
        val callbackContent = CallbackContentImpl(identifier, content)

        contentByIdentifier[identifier] = callbackContent

        return callbackContent
    }

    override suspend fun get(identifier: UUID): CallbackContent {
        return contentByIdentifier[identifier] ?: throw ChatException("Содержание для callback'а не найдено :(")
    }
}