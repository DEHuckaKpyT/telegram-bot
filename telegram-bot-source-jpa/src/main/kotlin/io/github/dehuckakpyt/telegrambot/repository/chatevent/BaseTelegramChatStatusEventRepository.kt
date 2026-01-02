package io.github.dehuckakpyt.telegrambot.repository.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.BaseTelegramChatStatusEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*


/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramChatStatusEventRepository<EntityT : BaseTelegramChatStatusEvent> : JpaRepository<EntityT, UUID>
