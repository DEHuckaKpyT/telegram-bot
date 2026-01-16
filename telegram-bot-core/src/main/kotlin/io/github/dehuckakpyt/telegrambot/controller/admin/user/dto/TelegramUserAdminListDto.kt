package io.github.dehuckakpyt.telegrambot.controller.admin.user.dto

import io.github.dehuckakpyt.telegrambot.model.telegram.User
import java.time.LocalDateTime
import java.util.*


/**
 * @author Denis Matytsin
 */
data class TelegramUserAdminListDto(

    public val id: UUID,

    /** Unique identifier for user (from [User.id]). */
    public val userId: Long,

    /** User's username (from [User.username]). */
    public val username: String?,

    /** User's first name (from [User.firstName]). */
    public val firstName: String,

    /** User's last name (from [User.lastName]). */
    public val lastName: String?,

    /** [IETF language tag](https://en.wikipedia.org/wiki/IETF_language_tag) of the user's language (from [User.languageCode]). */
    public val languageCode: String?,

    /** False if user blocked this bot. True if bot can send messages to user. */
    public val available: Boolean,

    /** Date/time when user started this bot last time. */
    public val updatedAt: LocalDateTime,

    /** Date/time when user started this bot first time. */
    public val createdAt: LocalDateTime,
)
