package io.github.dehuckakpyt.telegrambot.repository.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.BaseTelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.repository.base.TelegramEntityRepository
import org.springframework.data.repository.NoRepositoryBean


/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramChatStatusEventRepository<EntityT : BaseTelegramChatStatusEvent> : TelegramEntityRepository<EntityT>
