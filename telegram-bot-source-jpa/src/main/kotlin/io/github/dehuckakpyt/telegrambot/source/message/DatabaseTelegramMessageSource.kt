package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.DatabaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.message.DatabaseTelegramMessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class DatabaseTelegramMessageSource(
    private val repository: DatabaseTelegramMessageRepository,
) : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?): Unit = withContext(Dispatchers.IO) {
        repository.save(
            DatabaseTelegramMessage(
                chatId = chatId,
                fromId = fromId,
                fromBot = fromBot,
                messageId = messageId,
                type = type,
                step = step,
                stepContainerType = stepContainerType,
                text = text,
            )
        )
    }
}