package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.JpaTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.message.JpaTelegramMessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class JpaTelegramMessageSource(
    private val repository: JpaTelegramMessageRepository,
) : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = withContext(Dispatchers.IO) {
        repository.save(
            JpaTelegramMessage(
                chatId = chatId,
                fromId = fromId,
                fromBot = fromBot,
                messageId = messageId,
                type = type,
                step = step,
                stepContainerType = stepContainerType,
                text = text,
                fileIds = fileIds,
            )
        )
    }
}