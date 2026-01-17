package io.github.dehuckakpyt.telegrambot.repository.chat

import io.github.dehuckakpyt.telegrambot.model.chat.BaseTelegramChat
import io.github.dehuckakpyt.telegrambot.repository.base.TelegramEntityRepository
import org.springframework.data.repository.NoRepositoryBean


/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramChatRepository<EntityT : BaseTelegramChat> : TelegramEntityRepository<EntityT> {

    fun findByChatId(chatId: Long): EntityT?
}