package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * This object represents a unique message identifier.
 *
 * @see [MessageId] (https://core.telegram.org/bots/api/#messageid)
 *
 * @author KScript
 *
 * @param messageId Unique message identifier. In specific instances (e.g., message containing a
 * video sent to a big chat), the server might automatically schedule a message instead of sending it
 * immediately. In such cases, this field will be 0 and the relevant message will be unusable until it
 * is actually sent
 */
public data class MessageId(
    /**
     * Unique message identifier. In specific instances (e.g., message containing a video sent to a
     * big chat), the server might automatically schedule a message instead of sending it immediately.
     * In such cases, this field will be 0 and the relevant message will be unusable until it is
     * actually sent
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
)
