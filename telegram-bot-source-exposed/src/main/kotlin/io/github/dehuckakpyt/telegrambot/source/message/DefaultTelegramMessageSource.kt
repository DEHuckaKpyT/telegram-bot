package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message

class DefaultTelegramMessageSource : TelegramMessageSource<DefaultTelegramMessage> {

    override suspend fun save(message: Message, fromBot: Boolean, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = executeQuery {
        DefaultTelegramMessage.new {
            this.chatId = message.chat.id
            this.fromId = message.from?.id
            this.fromBot = fromBot
            this.messageId = message.messageId
            this.type = type
            this.step = step
            this.stepContainerType = stepContainerType
            this.text = text
            this.fileIds = fileIds
        }
    }
}