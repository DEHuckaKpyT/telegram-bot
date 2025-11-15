package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.user.DefaultTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.DefaultTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager


/**
 * Default bean of TelegramUserSource.
 *
 * @author Denis Matytsin
 */
open class DefaultTelegramUserSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramUserRepository,
    override val entityManager: EntityManager,
) : BaseTelegramUserSource<DefaultTelegramUser>()
