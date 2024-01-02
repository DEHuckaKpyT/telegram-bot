package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_message")
class DatabaseMessage(
    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    @Column(nullable = false)
    override val messageId: Long,

    @Column(columnDefinition = "text")
    override val text: String?,

    @Column(nullable = false)
    override val createDate: LocalDateTime,
) : UUIDTable(), TelegramMessage