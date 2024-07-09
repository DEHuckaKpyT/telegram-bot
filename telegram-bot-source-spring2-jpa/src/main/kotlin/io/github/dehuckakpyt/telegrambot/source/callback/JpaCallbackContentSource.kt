package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.model.callback.JpaCallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import io.github.dehuckakpyt.telegrambot.repository.OffsetLimitPageable
import io.github.dehuckakpyt.telegrambot.repository.callback.JpaCallbackContentRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime
import java.util.*

open class JpaCallbackContentSource(
    private val transactional: TransactionAction,
    private val repository: JpaCallbackContentRepository,
    private val maxCallbackContentsPerUser: Long,
) : CallbackContentSource {

    protected val pageable = OffsetLimitPageable(maxCallbackContentsPerUser - 1, 1)

    override suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent = transactional {
        val callbackContent = findLast(chatId, fromId)?.apply {
            this.callbackId = UUID.randomUUID()
            this.content = content
            this.updateDate = LocalDateTime.now()
        } ?: JpaCallbackContent(
            chatId = chatId,
            fromId = fromId,
            callbackId = UUID.randomUUID(),
            content = content,
            updateDate = LocalDateTime.now(),
        )

        repository.save(callbackContent)
    }

    override suspend fun get(callbackId: UUID): CallbackContent = transactional(readOnly = true) {
        repository.findFirstByCallbackId(callbackId)
            ?: throw ChatException("Содержание для callback'а не найдено :(")
    }

    private fun findLast(chatId: Long, fromId: Long): JpaCallbackContent? {
        if (maxCallbackContentsPerUser < 1) return null

        return repository.findByChatIdAndFromIdOrderByUpdateDateDesc(chatId, fromId, pageable).firstOrNull()
    }
}