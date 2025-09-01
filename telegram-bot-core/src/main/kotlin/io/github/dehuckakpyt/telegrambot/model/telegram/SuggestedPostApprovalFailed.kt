package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Describes a service message about the failed approval of a suggested post. Currently, only caused
 * by insufficient user funds at the time of approval.
 *
 * @see [SuggestedPostApprovalFailed]
 * (https://core.telegram.org/bots/api/#suggestedpostapprovalfailed)
 *
 * @author KScript
 *
 * @param suggestedPostMessage *Optional*. Message containing the suggested post whose approval has
 * failed. Note that the [Message](https://core.telegram.org/bots/api/#message) object in this field
 * will not contain the *reply_to_message* field even if it itself is a reply.
 * @param price Expected price of the post
 */
public data class SuggestedPostApprovalFailed(
    /**
     * *Optional*. Message containing the suggested post whose approval has failed. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
     * *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("suggested_post_message")
    @param:JsonProperty("suggested_post_message")
    public val suggestedPostMessage: Message? = null,
    /**
     * Expected price of the post
     */
    @get:JsonProperty("price")
    @param:JsonProperty("price")
    public val price: SuggestedPostPrice,
)
