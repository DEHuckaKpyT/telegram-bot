package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a forum topic.
 *
 * @see [ForumTopic] (https://core.telegram.org/bots/api/#forumtopic)
 *
 * @author KScript
 *
 * @param messageThreadId Unique identifier of the forum topic
 * @param name Name of the topic
 * @param iconColor Color of the topic icon in RGB format
 * @param iconCustomEmojiId *Optional*. Unique identifier of the custom emoji shown as the topic
 * icon
 */
public data class ForumTopic(
    /**
     * Unique identifier of the forum topic
     */
    @get:JsonProperty("message_thread_id")
    @param:JsonProperty("message_thread_id")
    public val messageThreadId: Long,
    /**
     * Name of the topic
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
    /**
     * Color of the topic icon in RGB format
     */
    @get:JsonProperty("icon_color")
    @param:JsonProperty("icon_color")
    public val iconColor: Int,
    /**
     * *Optional*. Unique identifier of the custom emoji shown as the topic icon
     */
    @get:JsonProperty("icon_custom_emoji_id")
    @param:JsonProperty("icon_custom_emoji_id")
    public val iconCustomEmojiId: String? = null,
)
