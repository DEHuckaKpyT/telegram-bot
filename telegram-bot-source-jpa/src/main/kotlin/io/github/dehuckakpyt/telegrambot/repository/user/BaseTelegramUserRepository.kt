package io.github.dehuckakpyt.telegrambot.repository.user

import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.base.TelegramEntityRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramUserRepository<EntityT : BaseTelegramUser> : TelegramEntityRepository<EntityT> {

    fun findByUserId(userId: Long): EntityT?
}