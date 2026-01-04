package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.chat.DefaultTelegramChat
import io.github.dehuckakpyt.telegrambot.repository.chat.DefaultTelegramChatRepository
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction


/**
 * @author Denis Matytsin
 */
class DefaultTelegramChatSource(
    override val transactional: TransactionAction,
    override val repository: DefaultTelegramChatRepository,
) : BaseTelegramChatSource<DefaultTelegramChat>()
