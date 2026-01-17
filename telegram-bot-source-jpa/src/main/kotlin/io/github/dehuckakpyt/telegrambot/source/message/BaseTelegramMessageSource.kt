package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.model.message.BaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.repository.message.BaseTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.source.base.ReadTelegramEntitySource
import io.github.dehuckakpyt.telegrambot.source.message.argument.SimpleFilterTelegramMessageArgument
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.time.LocalDateTime
import java.util.*

abstract class BaseTelegramMessageSource<EntityT : BaseTelegramMessage, FilterArgumentT : SimpleFilterTelegramMessageArgument> :
    ReadTelegramEntitySource<EntityT, FilterArgumentT>(),
    TelegramMessageSource<EntityT>,
    TelegramMessageAdminSource<UUID, EntityT, FilterArgumentT> {

    abstract override val repository: BaseTelegramMessageRepository<EntityT>

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

    override fun FilterArgumentT.toPredicates(root: Root<EntityT>, query: CriteriaQuery<*>?, builder: CriteriaBuilder): List<Predicate>? {
        val predicates = mutableListOf<Predicate>()

        predicates += defaultPredicates(root, query, builder)

        return predicates
    }

    protected open fun FilterArgumentT.defaultPredicates(root: Root<EntityT>, query: CriteriaQuery<*>?, builder: CriteriaBuilder): List<Predicate> {
        val predicates = mutableListOf<Predicate>()

        chatIdsIn?.let { predicates += root.get<Long>("chatId").`in`(it) }

        return predicates
    }
}