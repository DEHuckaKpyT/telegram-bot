package io.github.dehuckakpyt.telegrambot.source.message

import com.dehucka.exposed.ext.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DatabaseTelegramMessage

class DatabaseMessageSource : MessageSource {

    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?): Unit = executeQuery {
        DatabaseTelegramMessage.new {
            this.chatId = chatId
            this.fromId = fromId
            this.fromBot = fromBot
            this.messageId = messageId
            this.type = type
            this.step = step
            this.stepContainerType = stepContainerType
            this.text = text
        }
    }
}