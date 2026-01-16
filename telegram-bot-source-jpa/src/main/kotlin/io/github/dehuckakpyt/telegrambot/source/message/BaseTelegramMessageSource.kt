package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.message.BaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.repository.message.BaseTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.source.message.argument.FilterTelegramMessageArgument
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import java.util.*

abstract class BaseTelegramMessageSource<EntityT : BaseTelegramMessage> : TelegramMessageSource<EntityT>, TelegramMessageAdminSource<UUID, EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramMessageRepository<EntityT>
    protected abstract val entityManager: EntityManager

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

    override suspend fun get(id: UUID): EntityT = transactional(readOnly = true) {
        repository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Telegram user with given id '$id' does not exist.")
    }

    override suspend fun page(arg: FilterTelegramMessageArgument, pageable: Pageable): Page<EntityT> = transactional(readOnly = true) {
        repository.findAll(arg.toSpecification(), pageable)
    }

    override suspend fun slice(arg: FilterTelegramMessageArgument, pageable: Pageable): Slice<EntityT> = transactional(readOnly = true) {
        // TODO move to utilities parts of code
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(entityClass)
        val root = criteriaQuery.from(entityClass)

        arg.toSpecification()
            ?.toPredicate(root, criteriaQuery, criteriaBuilder)
            ?.let(criteriaQuery::where)

        // sort from Pageable
        if (pageable.sort.isSorted) {
            val orders = pageable.sort.mapTo(mutableListOf()) { order ->
                if (order.isAscending) criteriaBuilder.asc(root.get<Any>(order.property))
                else criteriaBuilder.desc(root.get<Any>(order.property))
            }
            criteriaQuery.orderBy(orders)
        }

        val query = entityManager.createQuery(criteriaQuery)
        query.firstResult = pageable.offset.toInt()
        query.maxResults = pageable.pageSize + 1 // +1 to know hasNext

        var resultList = query.resultList
        val hasNext = resultList.size > pageable.pageSize
        if (hasNext) {
            resultList = resultList.dropLast(1)
        }

        SliceImpl(resultList, pageable, hasNext)
    }

    override suspend fun count(arg: FilterTelegramMessageArgument): Long = transactional(readOnly = true) {
        repository.count(arg.toSpecification())
    }

    protected fun FilterTelegramMessageArgument.toSpecification(): Specification<EntityT>? = Specification { root, _, builder ->
        val predicates = mutableListOf<Predicate>()

        chatIdsIn?.let { predicates += root.get<Long>("chatId").`in`(it) }

        if (predicates.isNotEmpty()) builder.and(*predicates.toTypedArray())
        else null
    }
}