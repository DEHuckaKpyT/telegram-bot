package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.DatabaseMessage
import io.github.dehuckakpyt.telegrambot.repository.MessageRepository
import java.time.LocalDateTime

class DatabaseMessageSource(
    private val repository: MessageRepository,
) : MessageSource {

    override suspend fun save(
        chatId: Long,
        fromId: Long,
        messageId: Long,
        text: String?,
    ): Unit {
        repository.save(
            DatabaseMessage(
                chatId = chatId,
                fromId = fromId,
                messageId = messageId,
                text = text,
                createDate = LocalDateTime.now()
            )
        )
    }
}