package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that was banned in the
 * chat and can't return to the chat or view chat messages.
 *
 * @see [ChatMemberBanned] (https://core.telegram.org/bots/api/#chatmemberbanned)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “kicked”
 * @param user Information about the user
 * @param untilDate Date when restrictions will be lifted for this user; Unix time. If 0, then the
 * user is banned forever
 */
public data class ChatMemberBanned(
    /**
     * The member's status in the chat, always “kicked”
     */
    @get:JsonProperty("status")
    @param:JsonProperty("status")
    override val status: String,
    /**
     * Information about the user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    override val user: User,
    /**
     * Date when restrictions will be lifted for this user; Unix time. If 0, then the user is banned
     * forever
     */
    @get:JsonProperty("until_date")
    @param:JsonProperty("until_date")
    public val untilDate: Long,
) : ChatMember
