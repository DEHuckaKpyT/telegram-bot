package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.ext.java.lang.getHandleOfEmptyDeclaredConstructor
import io.github.dehuckakpyt.telegrambot.ext.kotlin.reflect.firstGenericClass
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.BaseTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import org.springframework.transaction.annotation.Isolation.REPEATABLE_READ
import java.time.LocalDateTime


/**
 * If you do not intend to override the [save] method, remember that an entity inherited from BaseTelegramUser must have an empty constructor.
 *
 * @author Denis Matytsin
 */
abstract class BaseTelegramUserSource<EntityT : BaseTelegramUser> : TelegramUserSource<EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramUserRepository<EntityT>

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
}