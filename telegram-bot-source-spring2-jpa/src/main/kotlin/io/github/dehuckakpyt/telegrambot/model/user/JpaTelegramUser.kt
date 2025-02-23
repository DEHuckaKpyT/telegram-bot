package io.github.dehuckakpyt.telegrambot.model.user

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


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