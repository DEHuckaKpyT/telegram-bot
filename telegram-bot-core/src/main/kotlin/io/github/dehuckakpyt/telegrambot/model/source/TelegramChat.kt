package io.github.dehuckakpyt.telegrambot.model.source

import java.time.LocalDateTime


/**
 * Interface with info about Chat, which has at least once added a bot.
 * Any chat except private.
 * Updates on every [io.github.dehuckakpyt.telegrambot.model.telegram.Update.myChatMember] (except private type).
 *
 * @author Denis Matytsin
 */
interface TelegramChat {

    /** Unique identifier for this chat (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.id]). */
    public val chatId: Long

    /** Type of the chat, can be either “group”, “supergroup” or “channel” (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.type]). */
    public val type: String

    /** Title, for supergroups, channels and group chats (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.title]). */
    public val title: String

    /** Username, for supergroups and channels if available (from [io.github.dehuckakpyt.telegrambot.model.telegram.Chat.username]). */
    public val username: String?

    /** False if bot was kicked from chat. True if bot can send messages to chat. */
    public val available: Boolean

    /** Date/time when chat with bot was changed last time. */
    public val updatedAt: LocalDateTime

    /** Date/time when bot was added to chat. */
    public val createdAt: LocalDateTime
}