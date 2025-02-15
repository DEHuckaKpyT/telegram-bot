package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.chat.JpaTelegramChat
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.repository.chat.JpaTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
class JpaTelegramChatSource(
    private val transactional: TransactionAction,
    private val repository: JpaTelegramChatRepository,
) : TelegramChatSource {

    override suspend fun save(chat: Chat, available: Boolean): Unit = transactional {
        val now = LocalDateTime.now()

        val entity = repository.findByChatId(chatId = chat.id)?.apply {
            this.type = chat.type
            this.title = chat.title ?: ""
            this.username = chat.username
            this.available = available
            this.updateDate = now
        } ?: JpaTelegramChat(
            chatId = chat.id,
            type = chat.type,
            title = chat.title ?: "",
            username = chat.username,
            available = available,
            updateDate = now,
            createDate = now,
        )

        repository.save(entity)
    }
}
