package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.chat.BaseTelegramChat
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.repository.chat.BaseTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
abstract class BaseTelegramChatSource<EntityT : BaseTelegramChat> : TelegramChatSource<EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramChatRepository<EntityT>
    protected abstract val entityManager: EntityManager

    protected val entityClass: Class<EntityT> = this::class.firstGenericClass()
    private val createChat: (chatId: Long, createdAt: LocalDateTime) -> EntityT

    init {
        val createChatHandle = entityClass.getHandleOfEmptyDeclaredConstructor()

        @Suppress("UNCHECKED_CAST")
        createChat = { chatId, createdAt ->
            val chat = createChatHandle.invoke() as EntityT

            chat.chatId = chatId
            chat.createdAt = createdAt

            chat
        }
    }

    override suspend fun save(chat: Chat, available: Boolean): Unit = transactional {
        val now = LocalDateTime.now()

        val entity = repository.findByChatId(chat.id) ?: createChat(chat.id, now)

        entity.apply {
            this.type = chat.type
            this.title = chat.title
            this.username = chat.username
            this.available = available
            this.updatedAt = now
        }

        repository.save(entity)
    }
}
