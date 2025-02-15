package io.github.dehuckakpyt.telegrambot.repository.user

import io.github.dehuckakpyt.telegrambot.model.user.JpaTelegramUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface JpaTelegramUserRepository : JpaRepository<JpaTelegramUser, UUID> {

    fun findByUserId(userId: Long): JpaTelegramUser?
}