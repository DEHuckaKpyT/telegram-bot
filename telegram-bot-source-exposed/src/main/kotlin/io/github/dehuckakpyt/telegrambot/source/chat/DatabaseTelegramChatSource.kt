package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DatabaseTelegramChat
import io.github.dehuckakpyt.telegrambot.model.TelegramChats
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
class DatabaseTelegramChatSource : TelegramChatSource {

    override suspend fun save(chat: Chat, available: Boolean): Unit = executeQuery {
        val now = LocalDateTime.now()

        DatabaseTelegramChat.findSingleByAndUpdate(TelegramChats.chatId eq chat.id) { entity ->
            entity.chatId = chat.id
            entity.type = chat.type
            entity.title = chat.title ?: ""
            entity.username = chat.username
            entity.available = available
            entity.updateDate = now
        } ?: DatabaseTelegramChat.new {
            this.chatId = chat.id
            this.type = chat.type
            this.title = chat.title ?: ""
            this.username = chat.username
            this.available = available
            this.updateDate = now
            this.createDate = now
        }
    }
}
