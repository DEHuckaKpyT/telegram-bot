package io.github.dehuckakpyt.telegrambot.model.user

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_user")
class JpaTelegramUser(
    @Column(nullable = false, unique = true)
    override val userId: Long,

    override var username: String?,

    @Column(nullable = false)
    override var firstName: String,

    override var lastName: String?,

    override var languageCode: String?,

    @Column(nullable = false)
    override var available: Boolean,

    @Column(nullable = false)
    override var updateDate: LocalDateTime,

    @Column(nullable = false)
    override val createDate: LocalDateTime,
) : UUIDTable(), TelegramUser