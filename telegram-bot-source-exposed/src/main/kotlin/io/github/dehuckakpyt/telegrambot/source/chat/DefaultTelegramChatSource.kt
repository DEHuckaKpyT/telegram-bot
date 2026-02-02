package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramChat
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramChats
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import org.jetbrains.exposed.v1.core.eq
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatSource : TelegramChatSource<DefaultTelegramChat> {

    override suspend fun save(chat: Chat, available: Boolean): Unit = executeQuery {
        val now = LocalDateTime.now()

        DefaultTelegramChat.findSingleByAndUpdate(DefaultTelegramChats.chatId eq chat.id) { entity ->
            entity.type = chat.type
            entity.title = chat.title
            entity.username = chat.username
            entity.available = available
            entity.updatedAt = now
        } ?: DefaultTelegramChat.new {
            this.chatId = chat.id
            this.type = chat.type
            this.title = chat.title
            this.username = chat.username
            this.available = available
            this.updatedAt = now
            this.createdAt = now
        }
    }
}
