package io.github.dehuckakpyt.telegrambot.repository.message

import io.github.dehuckakpyt.telegrambot.model.message.BaseTelegramMessage
import io.github.dehuckakpyt.telegrambot.repository.base.TelegramEntityRepository
import org.springframework.data.repository.NoRepositoryBean


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramMessageRepository<EntityT : BaseTelegramMessage> : TelegramEntityRepository<EntityT>
