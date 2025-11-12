package io.github.dehuckakpyt.telegrambot.model.chatevent

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_chat_status_event")
class JpaTelegramChatStatusEvent(

    @Column(nullable = false)
    override val chatId: Long,

    override val title: String?,

    override val username: String?,

    override val firstName: String?,

    override val lastName: String?,

    @Column(nullable = false)
    override val status: String,

    @Column(nullable = false)
    override val createdAt: LocalDateTime,
) : UUIDTable(), TelegramChatStatusEvent