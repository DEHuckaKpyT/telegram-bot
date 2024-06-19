package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that owns the chat and
 * has all administrator privileges.
 *
 * @see [ChatMemberOwner] (https://core.telegram.org/bots/api/#chatmemberowner)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “creator”
 * @param user Information about the user
 * @param isAnonymous *True*, if the user's presence in the chat is hidden
 * @param customTitle *Optional*. Custom title for this user
 */
public data class ChatMemberOwner(
    /**
     * The member's status in the chat, always “creator”
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
     * *True*, if the user's presence in the chat is hidden
     */
    @get:JsonProperty("is_anonymous")
    @param:JsonProperty("is_anonymous")
    public val isAnonymous: Boolean,
    /**
     * *Optional*. Custom title for this user
     */
    @get:JsonProperty("custom_title")
    @param:JsonProperty("custom_title")
    public val customTitle: String? = null,
) : ChatMember
