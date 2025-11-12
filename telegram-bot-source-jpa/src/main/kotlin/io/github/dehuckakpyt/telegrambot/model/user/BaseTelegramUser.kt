package io.github.dehuckakpyt.telegrambot.model.user

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime


/**
 * Base realization with default fields. All fields will be filled automatically when user enter `/start`.
 *
 * I had to set zero, false, null values and use lateinit var.
 * I couldn't find another way to leave the instance creation to the empty constructor.
 * With an empty constructor, it is much easier to make convenient inheritance.
 *
 * @author Denis Matytsin
 */
@MappedSuperclass
abstract class BaseTelegramUser : UUIDTable(), TelegramUser {

    @Column(nullable = false, unique = true)
    override var userId: Long = 0

    override var username: String? = null

    @Column(nullable = false)
    override lateinit var firstName: String

    override var lastName: String? = null

    override var languageCode: String? = null

    @Column(nullable = false)
    override var available: Boolean = false

    @Column(nullable = false)
    override lateinit var updatedAt: LocalDateTime

    @Column(nullable = false)
    override lateinit var createdAt: LocalDateTime
}