package io.github.dehuckakpyt.telegrambot.repository.chat

import io.github.dehuckakpyt.telegrambot.model.chat.JpaTelegramChat
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * @author Denis Matytsin
 */
interface JpaTelegramChatRepository : JpaRepository<JpaTelegramChat, UUID> {

    fun findByChatId(chatId: Long): JpaTelegramChat?
}