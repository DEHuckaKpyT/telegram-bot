package io.github.dehuckakpyt.telegrambot.source.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.DefaultTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.repository.chatevent.DefaultTelegramChatStatusEventRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatStatusEventSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramChatStatusEventRepository,
) : BaseTelegramChatStatusEventSource<DefaultTelegramChatStatusEvent>()
