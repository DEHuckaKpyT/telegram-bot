package io.github.dehuckakpyt.telegrambot.source.user

import io.github.dehuckakpyt.telegrambot.model.user.DefaultTelegramUser
import io.github.dehuckakpyt.telegrambot.repository.user.DefaultTelegramUserRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction


/**
 * @author Denis Matytsin
 */
open class DefaultTelegramUserSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramUserRepository,
) : BaseTelegramUserSource<DefaultTelegramUser>()
