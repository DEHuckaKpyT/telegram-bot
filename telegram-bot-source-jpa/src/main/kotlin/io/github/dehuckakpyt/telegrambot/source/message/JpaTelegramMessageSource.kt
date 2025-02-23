package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.JpaTelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.repository.message.JpaTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime

open class JpaTelegramMessageSource(
    private val transactional: TransactionAction,
    private val repository: JpaTelegramMessageRepository,
) : MessageSource {

    override suspend fun save(message: Message, fromBot: Boolean, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = transactional {
        repository.save(
            JpaTelegramMessage(
                chatId = message.chat.id,
                fromId = message.from!!.id,
                fromBot = fromBot,
                messageId = message.messageId,
                type = type,
                step = step,
                stepContainerType = stepContainerType,
                text = text,
                fileIds = fileIds,
                createDate = LocalDateTime.now(),
            )
        )
    }
}