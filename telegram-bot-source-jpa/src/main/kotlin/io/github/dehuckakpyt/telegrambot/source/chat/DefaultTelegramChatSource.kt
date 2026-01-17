package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.chat.DefaultTelegramChat
import io.github.dehuckakpyt.telegrambot.repository.chat.DefaultTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.source.chat.argument.SimpleFilterTelegramChatArgument
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramChatRepository,
    override val entityManager: EntityManager,
) : BaseTelegramChatSource<DefaultTelegramChat, SimpleFilterTelegramChatArgument>()
