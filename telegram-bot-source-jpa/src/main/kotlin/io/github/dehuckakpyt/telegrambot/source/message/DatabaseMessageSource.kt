package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.DatabaseMessage
import io.github.dehuckakpyt.telegrambot.repository.MessageRepository

class DatabaseMessageSource(
    private val repository: MessageRepository,
) : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, messageId: Long, type: String, step: String?, text: String?) {
        repository.save(
            DatabaseMessage(
                chatId = chatId,
                fromId = fromId,
                messageId = messageId,
                type = type,
                step = step,
                text = text,
            )
        )
    }
}