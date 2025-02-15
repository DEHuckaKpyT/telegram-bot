package io.github.dehuckakpyt.telegrambot.source.chat.event

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DatabaseTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated


/**
 * @author Denis Matytsin
 */
class DatabaseTelegramChatStatusEventSource : TelegramChatStatusEventSource {

    override suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit = executeQuery {
        val chat = chatMemberUpdated.chat
        val newChatMember = chatMemberUpdated.newChatMember

        DatabaseTelegramChatStatusEvent.new {
            this.chatId = chat.id
            this.title = chat.title
            this.username = chat.username
            this.firstName = chat.firstName
            this.lastName = chat.lastName
            this.status = newChatMember.status
        }
    }
}
