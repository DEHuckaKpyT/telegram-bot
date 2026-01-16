package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.ext.jakarta.criteria.containsIgnoreCase
import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.BaseTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.source.user.argument.FilterTelegramUserArgument
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Isolation.REPEATABLE_READ
import java.time.LocalDateTime
import java.util.*


/**
 * Base source with all overridden methods for all modules in project (core, admin-ui).
 *
 * If you do not intend to override the [save] method, remember that an entity inherited from BaseTelegramUser must have an empty constructor.
 *
 * @author Denis Matytsin
 */
abstract class BaseTelegramUserSource<EntityT : BaseTelegramUser> : TelegramUserSource<EntityT>, TelegramUserAdminSource<UUID, EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramUserRepository<EntityT>
    protected abstract val entityManager: EntityManager

    protected val entityClass: Class<EntityT> = this::class.firstGenericClass()
    private val createUser: (userId: Long, createdAt: LocalDateTime) -> EntityT

    init {
        val createUserHandle = entityClass.getHandleOfEmptyDeclaredConstructor()

        @Suppress("UNCHECKED_CAST")
        createUser = { userId, createdAt ->
            val user = createUserHandle.invoke() as EntityT

            user.userId = userId
            user.createdAt = createdAt

            user
        }
    }

    override suspend fun save(user: User, available: Boolean): Unit = transactional(isolation = REPEATABLE_READ) {
        val now = LocalDateTime.now()

        val entity = repository.findByUserId(user.id) ?: createUser(user.id, now)

        entity.apply {
            this.username = user.username
            this.firstName = user.firstName
            this.lastName = user.lastName
            this.languageCode = user.languageCode
            this.available = available
            this.updatedAt = now
        }

        repository.save(entity)
    }

    override suspend fun get(id: UUID): EntityT = transactional(readOnly = true) {
        repository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Telegram user with given id '$id' does not exist.")
    }

    override suspend fun page(arg: FilterTelegramUserArgument, pageable: Pageable): Page<EntityT> = transactional(readOnly = true) {
        repository.findAll(arg.toSpecification(), pageable)
    }

    override suspend fun slice(arg: FilterTelegramUserArgument, pageable: Pageable): Slice<EntityT> = transactional(readOnly = true) {
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

    override suspend fun count(arg: FilterTelegramUserArgument): Long = transactional(readOnly = true) {
        repository.count(arg.toSpecification())
    }

    protected fun FilterTelegramUserArgument.toSpecification(): Specification<EntityT>? = Specification { root, _, builder ->
        val predicates = mutableListOf<Predicate>()

        userIdsIn?.let { predicates += root.get<Long>("userId").`in`(it) }
        usernameContainsIgnoreCase?.let { predicates += builder.containsIgnoreCase(root.get("username"), it) }
        anyStringFieldContainsIgnoreCase?.let {
            predicates += builder.or(
                builder.containsIgnoreCase(root.get("username"), it),
                builder.containsIgnoreCase(root.get("username"), it),
                builder.containsIgnoreCase(root.get("firstName"), it),
                builder.containsIgnoreCase(root.get("lastName"), it),
            )
        }

        if (predicates.isNotEmpty()) builder.and(*predicates.toTypedArray())
        else null
    }
}