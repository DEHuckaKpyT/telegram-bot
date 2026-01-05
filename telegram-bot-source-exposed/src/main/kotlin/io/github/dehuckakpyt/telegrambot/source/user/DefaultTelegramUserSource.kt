package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramUser
import io.github.dehuckakpyt.telegrambot.model.DefaultTelegramUsers
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
class DefaultTelegramUserSource : TelegramUserSource<DefaultTelegramUser> {

    override suspend fun save(user: User, available: Boolean): Unit = executeQuery {
        val now = LocalDateTime.now()

        DefaultTelegramUser.findSingleByAndUpdate(DefaultTelegramUsers.userId eq user.id) { entity ->
            entity.username = user.username
            entity.firstName = user.firstName
            entity.lastName = user.lastName
            entity.languageCode = user.languageCode
            entity.available = available
            entity.updatedAt = now
        } ?: DefaultTelegramUser.new {
            this.userId = user.id
            this.username = user.username
            this.firstName = user.firstName
            this.lastName = user.lastName
            this.languageCode = user.languageCode
            this.available = available
            this.updatedAt = now
            this.createdAt = now
        }
    }
}
