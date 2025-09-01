package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Describes a service message about the approval of a suggested post.
 *
 * @see [SuggestedPostApproved] (https://core.telegram.org/bots/api/#suggestedpostapproved)
 *
 * @author KScript
 *
 * @param suggestedPostMessage *Optional*. Message containing the suggested post. Note that the
 * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
 * *reply_to_message* field even if it itself is a reply.
 * @param price *Optional*. Amount paid for the post
 * @param sendDate Date when the post will be published
 */
public data class SuggestedPostApproved(
    /**
     * *Optional*. Message containing the suggested post. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
     * *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("suggested_post_message")
    @param:JsonProperty("suggested_post_message")
    public val suggestedPostMessage: Message? = null,
    /**
     * *Optional*. Amount paid for the post
     */
    @get:JsonProperty("price")
    @param:JsonProperty("price")
    public val price: SuggestedPostPrice? = null,
    /**
     * Date when the post will be published
     */
    @get:JsonProperty("send_date")
    @param:JsonProperty("send_date")
    public val sendDate: Long,
)
