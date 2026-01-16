package io.github.dehuckakpyt.telegrambot.model.source

import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMember
import java.time.LocalDateTime


/**
 * Event on every [io.github.dehuckakpyt.telegrambot.model.telegram.Update.myChatMember].
 * Saving all changes with any chats.
 *
 * @author Denis Matytsin
 */
interface TelegramChatStatusEvent<IdT : Any> {

    /** Unique internal identifier. */
    val id: IdT

    /** Unique identifier for chat (from [Chat.id]). */
    val chatId: Long

    /** Title, for supergroups, channels and group chats (from [Chat.title]). */
    val title: String?

    /** Username, for private chats, supergroups and channels if available (from [Chat.username]). */
    val username: String?

    /** First name of the other party in a private chat (from [Chat.firstName]). */
    val firstName: String?

    /** Last name of the other party in a private chat (from [Chat.lastName]). */
    val lastName: String?

    /** The member's status in the chat (from [ChatMember.status]). */
    val status: String

    /** Date/time when status was changed. */
    val createdAt: LocalDateTime
}