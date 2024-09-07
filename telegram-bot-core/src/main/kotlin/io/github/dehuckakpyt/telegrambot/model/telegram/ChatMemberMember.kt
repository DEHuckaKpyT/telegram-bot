package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that has no additional
 * privileges or restrictions.
 *
 * @see [ChatMemberMember] (https://core.telegram.org/bots/api/#chatmembermember)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “member”
 * @param user Information about the user
 * @param untilDate *Optional*. Date when the user's subscription will expire; Unix time
 */
public data class ChatMemberMember(
    /**
     * The member's status in the chat, always “member”
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
     * *Optional*. Date when the user's subscription will expire; Unix time
     */
    @get:JsonProperty("until_date")
    @param:JsonProperty("until_date")
    public val untilDate: Long? = null,
) : ChatMember
