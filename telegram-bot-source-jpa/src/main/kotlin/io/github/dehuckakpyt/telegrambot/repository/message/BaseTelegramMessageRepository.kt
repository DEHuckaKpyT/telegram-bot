package io.github.dehuckakpyt.telegrambot.repository.message

import io.github.dehuckakpyt.telegrambot.model.message.BaseTelegramMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramMessageRepository<EntityT : BaseTelegramMessage> : JpaRepository<EntityT, UUID>