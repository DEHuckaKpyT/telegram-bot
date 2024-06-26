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
 * @param messageId Unique message identifier
 */
public data class MessageId(
    /**
     * Unique message identifier
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long,
)
