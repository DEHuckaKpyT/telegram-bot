package io.github.dehuckakpyt.telegrambot.model.chatevent

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
@MappedSuperclass
class BaseTelegramChatStatusEvent : UUIDTable(), TelegramChatStatusEvent {

    @Column(nullable = false)
    override var chatId: Long = 0

    override var title: String? = null

    override var username: String? = null

    override var firstName: String? = null

    override var lastName: String? = null

    @Column(nullable = false)
    override lateinit var status: String

    @Column(nullable = false)
    override lateinit var createdAt: LocalDateTime
} 
