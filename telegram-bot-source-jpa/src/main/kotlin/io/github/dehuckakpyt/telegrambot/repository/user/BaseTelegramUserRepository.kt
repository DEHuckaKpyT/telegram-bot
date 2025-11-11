package io.github.dehuckakpyt.telegrambot.repository.user

import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramUserRepository<EntityT : BaseTelegramUser> : JpaRepository<EntityT, UUID> {

    fun findByUserId(userId: Long): EntityT?
}