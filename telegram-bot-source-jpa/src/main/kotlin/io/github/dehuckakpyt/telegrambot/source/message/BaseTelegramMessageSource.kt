package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.message.BaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.repository.message.BaseTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime

abstract class BaseTelegramMessageSource<EntityT : BaseTelegramMessage> : TelegramMessageSource<EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramMessageRepository<EntityT>

    protected val entityClass: Class<EntityT> = this::class.firstGenericClass()
    private val createMessageHandle = entityClass.getHandleOfEmptyDeclaredConstructor()

    override suspend fun save(message: Message, fromBot: Boolean, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = transactional {
        @Suppress("UNCHECKED_CAST")
        val messageEntity = createMessageHandle.invoke() as EntityT

        messageEntity.chatId = message.chat.id
        messageEntity.fromId = message.from?.id
        messageEntity.fromBot = fromBot
        messageEntity.messageId = message.messageId
        messageEntity.type = type
        messageEntity.step = step
        messageEntity.stepContainerType = stepContainerType
        messageEntity.text = text
        messageEntity.fileIds = fileIds
        messageEntity.createdAt = LocalDateTime.now()

        repository.save(messageEntity)
    }
}