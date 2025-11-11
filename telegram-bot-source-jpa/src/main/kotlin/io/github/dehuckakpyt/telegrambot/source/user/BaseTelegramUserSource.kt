package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.BaseTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.lang.invoke.MethodHandles
import java.lang.reflect.ParameterizedType
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
abstract class BaseTelegramUserSource<EntityT : BaseTelegramUser> : TelegramUserSource<EntityT> {

    protected abstract val transactional: TransactionAction
    protected abstract val repository: BaseTelegramUserRepository<EntityT>

    @Suppress("UNCHECKED_CAST")
    private val entityClass: Class<EntityT> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<EntityT>
    private val createUser: (userId: Long, createDate: LocalDateTime) -> EntityT

    init {
        val lookup = MethodHandles.lookup()
        val constructor = entityClass.getDeclaredConstructor()
        val createUserHandle = lookup.unreflectConstructor(constructor)
        createUser = { userId, createDate ->
            @Suppress("UNCHECKED_CAST")
            val user = createUserHandle.invoke() as EntityT

            user.setUserId(userId)
            user.setCreateDate(createDate)

            user
        }
    }

    override suspend fun save(user: User, available: Boolean): Unit = transactional {
        val now = LocalDateTime.now()

        val entity = repository.findByUserId(userId = user.id) ?: createUser(user.id, now)

        entity.apply {
            this.setUsername(user.username)
            this.setFirstName(user.firstName)
            this.setLastName(user.lastName)
            this.setLanguageCode(user.languageCode)
            this.setAvailable(available)
            this.setUpdateDate(now)
        }

        repository.save(entity)
    }
}