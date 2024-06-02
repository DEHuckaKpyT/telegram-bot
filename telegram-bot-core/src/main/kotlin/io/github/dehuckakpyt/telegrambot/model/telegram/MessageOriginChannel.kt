package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * The message was originally sent to a channel chat.
 *
 * @see [MessageOriginChannel] (https://core.telegram.org/bots/api/#messageoriginchannel)
 *
 * @author KScript
 *
 * @param type Type of the message origin, always “channel”
 * @param date Date the message was sent originally in Unix time
 * @param chat Channel chat to which the message was originally sent
 * @param messageId Unique message identifier inside the chat
 * @param authorSignature *Optional*. Signature of the original post author
 */
public data class MessageOriginChannel(
    /**
     * Type of the message origin, always “channel”
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
     * Channel chat to which the message was originally sent
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Unique message identifier inside the chat
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
    /**
     * *Optional*. Signature of the original post author
     */
    @get:JsonProperty("author_signature")
    @param:JsonProperty("author_signature")
    public val authorSignature: String? = null,
) : MessageOrigin
