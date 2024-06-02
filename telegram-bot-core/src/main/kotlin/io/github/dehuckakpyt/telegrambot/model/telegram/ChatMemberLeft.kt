package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that isn't currently a
 * member of the chat, but may join it themselves.
 *
 * @see [ChatMemberLeft] (https://core.telegram.org/bots/api/#chatmemberleft)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “left”
 * @param user Information about the user
 */
public data class ChatMemberLeft(
    /**
     * The member's status in the chat, always “left”
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
) : ChatMember
