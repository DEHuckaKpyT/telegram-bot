package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.message.DefaultTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.message.DefaultTelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction


/**
 * @author Denis Matytsin
 */
open class DefaultTelegramMessageSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramMessageRepository,
) : BaseTelegramMessageSource<DefaultTelegramMessage>()
