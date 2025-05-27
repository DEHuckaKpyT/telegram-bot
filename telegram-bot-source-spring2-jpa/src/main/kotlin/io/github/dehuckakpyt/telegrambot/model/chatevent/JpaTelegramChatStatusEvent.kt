package io.github.dehuckakpyt.telegrambot.model.chatevent

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


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
    override val createDate: LocalDateTime,
) : UUIDTable(), TelegramChatStatusEvent