package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.JpaTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.message.JpaTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction

open class JpaTelegramMessageSource(
    private val transactional: TransactionAction,
    private val repository: JpaTelegramMessageRepository,
) : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = transactional {
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