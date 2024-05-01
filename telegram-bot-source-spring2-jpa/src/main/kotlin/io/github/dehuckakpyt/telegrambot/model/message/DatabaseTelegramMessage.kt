package io.github.dehuckakpyt.telegrambot.model.message

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_message")
class DatabaseTelegramMessage(
    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    @Column(nullable = false)
    override val fromBot: Boolean,

    @Column(nullable = false)
    override val messageId: Long,

    @Column(nullable = false)
    override val type: String,

    override val step: String?,

    override val stepContainerType: String?,

    @Column(columnDefinition = "text")
    override val text: String?,

    @Column(nullable = false)
    @ColumnDefault("'now()'")
    override val createDate: LocalDateTime = LocalDateTime.now(),
) : UUIDTable(), TelegramMessage