package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.user.JpaTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.JpaTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
open class JpaTelegramUserSource(
    private val transactional: TransactionAction,
    private val repository: JpaTelegramUserRepository,
) : TelegramUserSource {

    override suspend fun save(user: User, available: Boolean): Unit = transactional {
        val now = LocalDateTime.now()

        val entity = repository.findByUserId(userId = user.id)?.apply {
            this.username = user.username
            this.firstName = user.firstName
            this.lastName = user.lastName
            this.languageCode = user.languageCode
            this.available = available
            this.updateDate = now
        } ?: JpaTelegramUser(
            userId = user.id,
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            languageCode = user.languageCode,
            available = available,
            updateDate = now,
            createDate = now,
        )

        repository.save(entity)
    }
}