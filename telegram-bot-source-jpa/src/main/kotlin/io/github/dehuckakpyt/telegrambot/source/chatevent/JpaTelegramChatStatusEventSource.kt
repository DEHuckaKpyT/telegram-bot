package io.github.dehuckakpyt.telegrambot.source.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.JpaTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated
import io.github.dehuckakpyt.telegrambot.repository.chatevent.JpaTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
open class JpaTelegramChatStatusEventSource(
    private val transactional: TransactionAction,
    private val repository: JpaTelegramChatStatusEventRepository,
) : TelegramChatStatusEventSource {

    override suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit = transactional {
        val chat = chatMemberUpdated.chat
        val status = chatMemberUpdated.newChatMember.status

        JpaTelegramChatStatusEvent(
            chatId = chat.id,
            username = chat.username,
            title = chat.title,
            firstName = chat.firstName,
            lastName = chat.lastName,
            status = status,
            createdAt = LocalDateTime.now(),
        ).let(repository::save)
    }
}