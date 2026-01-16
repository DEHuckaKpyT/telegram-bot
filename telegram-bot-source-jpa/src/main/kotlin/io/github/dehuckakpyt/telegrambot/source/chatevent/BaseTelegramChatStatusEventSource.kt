package io.github.dehuckakpyt.telegrambot.source.chatevent

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.chatevent.BaseTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated
import io.github.dehuckakpyt.telegrambot.repository.chatevent.BaseTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
abstract class BaseTelegramChatStatusEventSource<EntityT : BaseTelegramChatStatusEvent> : TelegramChatStatusEventSource<EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramChatStatusEventRepository<EntityT>
    protected abstract val entityManager: EntityManager

    protected val entityClass: Class<EntityT> = this::class.firstGenericClass()
    private val createChatStatusEventHandle = entityClass.getHandleOfEmptyDeclaredConstructor()

    override suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit = transactional {
        val chat = chatMemberUpdated.chat
        val status = chatMemberUpdated.newChatMember.status

        @Suppress("UNCHECKED_CAST")
        val chatStatusEvent = createChatStatusEventHandle.invoke() as EntityT

        chatStatusEvent.chatId = chat.id
        chatStatusEvent.username = chat.username
        chatStatusEvent.title = chat.title
        chatStatusEvent.firstName = chat.firstName
        chatStatusEvent.lastName = chat.lastName
        chatStatusEvent.status = status
        chatStatusEvent.createdAt = LocalDateTime.now()

        repository.save(chatStatusEvent)
    }
}