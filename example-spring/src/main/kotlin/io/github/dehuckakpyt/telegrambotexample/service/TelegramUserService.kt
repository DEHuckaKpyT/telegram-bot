package io.github.dehuckakpyt.telegrambotexample.service

import io.github.dehuckakpyt.telegrambot.source.user.BaseTelegramUserSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import io.github.dehuckakpyt.telegrambotexample.model.TelegramUser
import io.github.dehuckakpyt.telegrambotexample.repository.TelegramUserRepository
import org.springframework.stereotype.Service


/**
 * @author Denis Matytsin
 */
@Service
class TelegramUserService(
    override val transactional: TransactionAction,
    override val repository: TelegramUserRepository,
) : BaseTelegramUserSource<TelegramUser>() {

    suspend fun setPhone(userId: Long, phone: String): TelegramUser = transactional {
        val user = repository.findByUserId(userId)
            ?: throw IllegalStateException("User $userId does not exist.")

        user.phone = phone

        repository.save(user)
    }
}