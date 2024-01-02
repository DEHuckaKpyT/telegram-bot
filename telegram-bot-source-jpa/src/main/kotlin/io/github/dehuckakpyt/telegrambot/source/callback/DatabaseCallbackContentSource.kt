package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.constant.Default.MAX_CALLBACK_CONTENTS_PER_USER
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.model.DatabaseCallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import io.github.dehuckakpyt.telegrambot.repository.CallbackContentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

class DatabaseCallbackContentSource(
    private val repository: CallbackContentRepository,
    private val maxCallbackContentsPerUser: Long = MAX_CALLBACK_CONTENTS_PER_USER,
) : CallbackContentSource {

    @Transactional
    override suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent {
        val callbackContent = findLast(chatId, fromId)?.apply {
            this.callbackId = UUID.randomUUID()
            this.content = content
            this.updateDate = LocalDateTime.now()
        } ?: DatabaseCallbackContent(
            chatId = chatId,
            fromId = fromId,
            callbackId = UUID.randomUUID(),
            content = content,
            updateDate = LocalDateTime.now(),
        )

        return repository.save(callbackContent)
    }

    @Transactional(readOnly = true)
    override suspend fun get(callbackId: UUID): CallbackContent {
        return repository.findFirstByCallbackId(callbackId)
            ?: throw ChatException("Содержание для callback'а не найдено :(")
    }

    private fun findLast(chatId: Long, fromId: Long): DatabaseCallbackContent? {
        if (maxCallbackContentsPerUser < 1) return null

        return repository.findByChatIdAndFromIdOrderByUpdateDateDesc(chatId, fromId, OffsetLimitPageable(maxCallbackContentsPerUser - 1, 1)).firstOrNull()
    }
}

class OffsetLimitPageable(private val offset: Long, limit: Int) : PageRequest(offset.toInt(), limit, Sort.unsorted()) {
    override fun getOffset(): Long = offset
}