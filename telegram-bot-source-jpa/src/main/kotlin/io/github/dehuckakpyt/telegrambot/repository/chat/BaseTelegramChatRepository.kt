package io.github.dehuckakpyt.telegrambot.repository.chat

import io.github.dehuckakpyt.telegrambot.model.chat.BaseTelegramChat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*


/**
 * @author Denis Matytsin
 */
@NoRepositoryBean
interface BaseTelegramChatRepository<EntityT : BaseTelegramChat> : JpaRepository<EntityT, UUID> {

    fun findByChatId(chatId: Long): EntityT?
}