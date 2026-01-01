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
 * @param topicId Unique identifier of the topic. This number may have more than 32 significant bits
 * and some programming languages may have difficulty/silent defects in interpreting it. But it has at
 * most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing
 * this identifier.
 * @param user *Optional*. Information about the user that created the topic. Currently, it is
 * always present
 */
public data class DirectMessagesTopic(
    /**
     * Unique identifier of the topic. This number may have more than 32 significant bits and some
     * programming languages may have difficulty/silent defects in interpreting it. But it has at most
     * 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing
     * this identifier.
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
