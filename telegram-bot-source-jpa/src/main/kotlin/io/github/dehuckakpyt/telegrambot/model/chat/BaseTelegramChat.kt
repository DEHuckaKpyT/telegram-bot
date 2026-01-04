package io.github.dehuckakpyt.telegrambot.model.chat

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
@MappedSuperclass
class BaseTelegramChat : UUIDTable(), TelegramChat {

    @Column(nullable = false, unique = true)
    override var chatId: Long = 0

    @Column(nullable = false)
    override lateinit var type: String

    override var title: String? = null

    override var username: String? = null

    @Column(nullable = false)
    override var available: Boolean = false

    @Column(nullable = false)
    override lateinit var updatedAt: LocalDateTime

    @Column(nullable = false)
    override lateinit var createdAt: LocalDateTime
}