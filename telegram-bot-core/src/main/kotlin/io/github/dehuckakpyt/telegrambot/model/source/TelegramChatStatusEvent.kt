package io.github.dehuckakpyt.telegrambot.model.source

import java.time.LocalDateTime


/**
 * Event on every [io.github.dehuckakpyt.telegrambot.model.telegram.Update.myChatMember].
 * Saving all changes with any chats.
 *
 * @author Denis Matytsin
 */
interface TelegramChatStatusEvent {

    /** Unique identifier for chat (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.id]). */
    val chatId: Long

    /** Title, for supergroups, channels and group chats (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.title]). */
    val title: String?

    /** Username, for private chats, supergroups and channels if available (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.username]). */
    val username: String?

    /** First name of the other party in a private chat (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.firstName]). */
    val firstName: String?

    /** Last name of the other party in a private chat (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.lastName]). */
    val lastName: String?

    /** The member's status in the chat (from [io.github.dehuckakpyt.telegrambot.model.telegram.ChatMember.status]). */
    val status: String

    /** Date/time when status was changed. */
    val createdAt: LocalDateTime
}