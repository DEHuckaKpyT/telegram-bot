package com.dehucka.telegrambot.source.message

import com.dehucka.exposed.ext.execute
import com.dehucka.telegrambot.model.TelegramMessage

class MessageSourceImpl : MessageSource {

    override suspend fun save(
        chatId: Long,
        fromId: Long?,
        messageId: Long,
        text: String?
    ): TelegramMessage = execute {
        TelegramMessage.new {
            this.chatId = chatId
            this.fromId = fromId
            this.messageId = messageId
            this.text = text
        }
    }
}