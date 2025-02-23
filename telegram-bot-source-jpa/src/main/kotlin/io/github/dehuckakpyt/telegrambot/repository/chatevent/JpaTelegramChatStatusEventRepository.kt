package io.github.dehuckakpyt.telegrambot.repository.chatevent

import io.github.dehuckakpyt.telegrambot.model.chatevent.JpaTelegramChatStatusEvent
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


/**
 * @author Denis Matytsin
 */
interface JpaTelegramChatStatusEventRepository : JpaRepository<JpaTelegramChatStatusEvent, UUID>