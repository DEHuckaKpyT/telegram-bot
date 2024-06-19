package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * The message was originally sent on behalf of a chat to a group chat.
 *
 * @see [MessageOriginChat] (https://core.telegram.org/bots/api/#messageoriginchat)
 *
 * @author KScript
 *
 * @param type Type of the message origin, always “chat”
 * @param date Date the message was sent originally in Unix time
 * @param senderChat Chat that sent the message originally
 * @param authorSignature *Optional*. For messages originally sent by an anonymous chat
 * administrator, original message author signature
 */
public data class MessageOriginChat(
    /**
     * Type of the message origin, always “chat”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Date the message was sent originally in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    override val date: Long,
    /**
     * Chat that sent the message originally
     */
    @get:JsonProperty("sender_chat")
    @param:JsonProperty("sender_chat")
    public val senderChat: Chat,
    /**
     * *Optional*. For messages originally sent by an anonymous chat administrator, original message
     * author signature
     */
    @get:JsonProperty("author_signature")
    @param:JsonProperty("author_signature")
    public val authorSignature: String? = null,
) : MessageOrigin
