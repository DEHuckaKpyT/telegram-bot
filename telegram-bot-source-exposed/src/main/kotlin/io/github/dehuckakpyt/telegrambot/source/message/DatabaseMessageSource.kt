package io.github.dehuckakpyt.telegrambot.source.message

import com.dehucka.exposed.ext.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DatabaseTelegramMessage

class DatabaseMessageSource : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, messageId: Long, type: String, step: String?, text: String?): Unit = executeQuery {
        DatabaseTelegramMessage.new {
            this.chatId = chatId
            this.fromId = fromId
            this.messageId = messageId
            this.type = type
            this.step = step
            this.text = text
        }
    }
}