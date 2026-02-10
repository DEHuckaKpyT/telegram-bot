package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Describes a service message about the chat owner leaving the chat.
 *
 * @see [ChatOwnerLeft] (https://core.telegram.org/bots/api/#chatownerleft)
 *
 * @author KScript
 *
 * @param newOwner *Optional*. The user which will be the new owner of the chat if the previous
 * owner does not return to the chat
 */
public data class ChatOwnerLeft(
    /**
     * *Optional*. The user which will be the new owner of the chat if the previous owner does not
     * return to the chat
     */
    @get:JsonProperty("new_owner")
    @param:JsonProperty("new_owner")
    public val newOwner: User? = null,
)
