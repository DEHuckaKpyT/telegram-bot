package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.DatabaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.message.DatabaseTelegramMessageRepository

open class DatabaseTelegramMessageSource(
    private val repository: DatabaseTelegramMessageRepository,
) : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, messageId: Long, type: String, step: String?, text: String?) {
        repository.save(
            DatabaseTelegramMessage(
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