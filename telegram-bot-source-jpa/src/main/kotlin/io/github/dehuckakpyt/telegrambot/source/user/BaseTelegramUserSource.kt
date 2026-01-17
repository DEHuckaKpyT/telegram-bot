package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.ext.jakarta.criteria.containsIgnoreCase
import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.BaseTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.source.base.ReadTelegramEntitySource
import io.github.dehuckakpyt.telegrambot.source.user.argument.SimpleFilterTelegramUserArgument
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
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
abstract class BaseTelegramUserSource<EntityT : BaseTelegramUser, FilterArgumentT : SimpleFilterTelegramUserArgument> :
    ReadTelegramEntitySource<EntityT, FilterArgumentT>(),
    TelegramUserSource<EntityT>,
    TelegramUserAdminSource<UUID, EntityT, FilterArgumentT> {

    abstract override val repository: BaseTelegramUserRepository<EntityT>

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

    override fun FilterArgumentT.toPredicates(root: Root<EntityT>, query: CriteriaQuery<*>?, builder: CriteriaBuilder): List<Predicate>? {
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

        return predicates
    }
}