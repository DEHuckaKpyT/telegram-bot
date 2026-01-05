package io.github.dehuckakpyt.telegrambot.source.chat.event

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatStatusEventSource : TelegramChatStatusEventSource<DefaultTelegramChatStatusEvent> {

    override suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit = executeQuery {
        val chat = chatMemberUpdated.chat
        val newChatMember = chatMemberUpdated.newChatMember

        DefaultTelegramChatStatusEvent.new {
            this.chatId = chat.id
            this.title = chat.title
            this.username = chat.username
            this.firstName = chat.firstName
            this.lastName = chat.lastName
            this.status = newChatMember.status
        }
    }
}
