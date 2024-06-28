package io.github.dehuckakpyt.telegrambot.repository.callback

import io.github.dehuckakpyt.telegrambot.model.callback.JpaCallbackContent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * Created on 02.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface JpaCallbackContentRepository : JpaRepository<JpaCallbackContent, UUID> {
    fun findFirstByCallbackId(callbackId: UUID): JpaCallbackContent?
    fun findByChatIdAndFromIdOrderByUpdateDateDesc(chatId: Long, fromId: Long, pageable: Pageable): Page<JpaCallbackContent>
}