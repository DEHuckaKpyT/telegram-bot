package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.config.constants.source.callback.CallbackContentSourceConstant.CALLBACK_CONTENT_SOURCE_MAX_CONTENTS_PER_USER
import io.github.dehuckakpyt.telegrambot.ext.sync.ASync
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContentImpl
import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class InMemoryCallbackContentSource(
    maxCallbackContentsPerUser: Long = CALLBACK_CONTENT_SOURCE_MAX_CONTENTS_PER_USER,
) : CallbackContentSource {
    private val sync = ASync<Long>()
    private val maxCallbackContentsPerUser: Int = maxCallbackContentsPerUser.toInt()
    private val contentById: MutableMap<UUID, CallbackContent> = linkedMapOf()

    override suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent {
        val id = UUID.randomUUID()
        val callbackContent = CallbackContentImpl(id, chatId, fromId, content)

        sync.parallelisedBy(fromId) {
            val callbackContents = contentById.values.filter { it.fromId == fromId }
            if (callbackContents.count() == maxCallbackContentsPerUser) {
                contentById.remove(callbackContents.first().callbackId)
            }

            contentById[id] = callbackContent
        }

        return callbackContent
    }

    override suspend fun get(callbackId: UUID): CallbackContent {
        return contentById[callbackId] ?: throw RuntimeException("No content was found for the callback :( You might have forgotten that restarting the program erases all callbacks. Recommended to use the implementation with the database.")
    }
}