package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a service message about the rejection of a suggested post.
 *
 * @see [SuggestedPostDeclined] (https://core.telegram.org/bots/api/#suggestedpostdeclined)
 *
 * @author KScript
 *
 * @param suggestedPostMessage *Optional*. Message containing the suggested post. Note that the
 * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
 * *reply_to_message* field even if it itself is a reply.
 * @param comment *Optional*. Comment with which the post was declined
 */
public data class SuggestedPostDeclined(
    /**
     * *Optional*. Message containing the suggested post. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
     * *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("suggested_post_message")
    @param:JsonProperty("suggested_post_message")
    public val suggestedPostMessage: Message? = null,
    /**
     * *Optional*. Comment with which the post was declined
     */
    @get:JsonProperty("comment")
    @param:JsonProperty("comment")
    public val comment: String? = null,
)
