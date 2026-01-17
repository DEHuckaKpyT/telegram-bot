package io.github.dehuckakpyt.telegrambot.source.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.DefaultTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.repository.chatevent.DefaultTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.source.chatevent.argument.SimpleFilterTelegramChatEventArgument
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import jakarta.persistence.EntityManager


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatStatusEventSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramChatStatusEventRepository,
    override val entityManager: EntityManager,
) : BaseTelegramChatStatusEventSource<DefaultTelegramChatStatusEvent, SimpleFilterTelegramChatEventArgument>()
