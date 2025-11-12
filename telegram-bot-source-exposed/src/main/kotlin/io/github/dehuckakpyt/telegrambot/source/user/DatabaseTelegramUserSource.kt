package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.ext.transaction.executeQuery
import io.github.dehuckakpyt.telegrambot.model.DatabaseTelegramUser
import io.github.dehuckakpyt.telegrambot.model.TelegramUsers
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
class DatabaseTelegramUserSource : TelegramUserSource {

    override suspend fun save(user: User, available: Boolean): Unit = executeQuery {
        val now = LocalDateTime.now()

        DatabaseTelegramUser.findSingleByAndUpdate(TelegramUsers.userId eq user.id) { entity ->
            entity.userId = user.id
            entity.username = user.username
            entity.firstName = user.firstName
            entity.lastName = user.lastName
            entity.languageCode = user.languageCode
            entity.available = available
            entity.updatedAt = now
        } ?: DatabaseTelegramUser.new {
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
