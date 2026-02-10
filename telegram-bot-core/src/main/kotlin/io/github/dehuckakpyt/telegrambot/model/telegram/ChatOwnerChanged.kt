package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Describes a service message about an ownership change in the chat.
 *
 * @see [ChatOwnerChanged] (https://core.telegram.org/bots/api/#chatownerchanged)
 *
 * @author KScript
 *
 * @param newOwner The new owner of the chat
 */
public data class ChatOwnerChanged(
    /**
     * The new owner of the chat
     */
    @get:JsonProperty("new_owner")
    @param:JsonProperty("new_owner")
    public val newOwner: User,
)
