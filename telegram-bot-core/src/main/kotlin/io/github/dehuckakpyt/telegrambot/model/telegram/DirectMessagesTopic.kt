package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * Describes a topic of a direct messages chat.
 *
 * @see [DirectMessagesTopic] (https://core.telegram.org/bots/api/#directmessagestopic)
 *
 * @author KScript
 *
 * @param topicId Unique identifier of the topic
 * @param user *Optional*. Information about the user that created the topic. Currently, it is
 * always present
 */
public data class DirectMessagesTopic(
    /**
     * Unique identifier of the topic
     */
    @get:JsonProperty("topic_id")
    @param:JsonProperty("topic_id")
    public val topicId: Long,
    /**
     * *Optional*. Information about the user that created the topic. Currently, it is always
     * present
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User? = null,
)
