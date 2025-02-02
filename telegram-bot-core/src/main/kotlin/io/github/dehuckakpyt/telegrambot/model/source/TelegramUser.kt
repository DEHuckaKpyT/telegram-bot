package io.github.dehuckakpyt.telegrambot.model.source

import java.time.LocalDateTime


/**
 * Interface with info about User which starts bot at least once.
 * Private chat only.
 * Updates on every `/start` command from user when added and every [io.github.dehuckakpyt.telegrambot.model.telegram.Update.myChatMember] when blocked.
 *
 * @author Denis Matytsin
 */
interface TelegramUser {

    /** Unique identifier for user (from [io.github.dehuckakpyt.telegrambot.model.telegram.User.id]). */
    public val userId: Long

    /** User's username (from [io.github.dehuckakpyt.telegrambot.model.telegram.User.username]). */
    public val username: String?

    /** User's first name (from [io.github.dehuckakpyt.telegrambot.model.telegram.User.firstName]). */
    public val firstName: String

    /** User's last name (from [io.github.dehuckakpyt.telegrambot.model.telegram.User.lastName]). */
    public val lastName: String?

    /** [IETF language tag](https://en.wikipedia.org/wiki/IETF_language_tag) of the user's language (from [io.github.dehuckakpyt.telegrambot.model.telegram.User.languageCode]). */
    public val languageCode: String?

    /** False if user blocked this bot. True if bot can send messages to user. */
    public val available: Boolean

    /** Date/time when user started this bot. */
    public val createDate: LocalDateTime
}