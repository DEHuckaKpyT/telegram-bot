package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a service message about a payment refund for a suggested post.
 *
 * @see [SuggestedPostRefunded] (https://core.telegram.org/bots/api/#suggestedpostrefunded)
 *
 * @author KScript
 *
 * @param suggestedPostMessage *Optional*. Message containing the suggested post. Note that the
 * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
 * *reply_to_message* field even if it itself is a reply.
 * @param reason Reason for the refund. Currently, one of “post_deleted” if the post was deleted
 * within 24 hours of being posted or removed from scheduled messages without being posted, or
 * “payment_refunded” if the payer refunded their payment.
 */
public data class SuggestedPostRefunded(
    /**
     * *Optional*. Message containing the suggested post. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
     * *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("suggested_post_message")
    @param:JsonProperty("suggested_post_message")
    public val suggestedPostMessage: Message? = null,
    /**
     * Reason for the refund. Currently, one of “post_deleted” if the post was deleted within 24
     * hours of being posted or removed from scheduled messages without being posted, or
     * “payment_refunded” if the payer refunded their payment.
     */
    @get:JsonProperty("reason")
    @param:JsonProperty("reason")
    public val reason: String,
)
