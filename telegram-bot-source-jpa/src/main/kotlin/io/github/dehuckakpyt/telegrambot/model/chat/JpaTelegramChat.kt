package io.github.dehuckakpyt.telegrambot.model.chat

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_chat")
class JpaTelegramChat(

    @Column(nullable = false, unique = true)
    override val chatId: Long,

    @Column(nullable = false)
    override var type: String,

    @Column(nullable = false)
    override var title: String,

    override var username: String?,

    @Column(nullable = false)
    override var available: Boolean,

    @Column(nullable = false)
    override var updateDate: LocalDateTime,

    @Column(nullable = false)
    override val createDate: LocalDateTime,
) : UUIDTable(), TelegramChat