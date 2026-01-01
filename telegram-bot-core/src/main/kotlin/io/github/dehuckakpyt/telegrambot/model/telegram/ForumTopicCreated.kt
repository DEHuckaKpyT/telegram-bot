package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * This object represents a service message about a new forum topic created in the chat.
 *
 * @see [ForumTopicCreated] (https://core.telegram.org/bots/api/#forumtopiccreated)
 *
 * @author KScript
 *
 * @param name Name of the topic
 * @param iconColor Color of the topic icon in RGB format
 * @param iconCustomEmojiId *Optional*. Unique identifier of the custom emoji shown as the topic
 * icon
 * @param isNameImplicit *Optional*. *True*, if the name of the topic wasn't specified explicitly by
 * its creator and likely needs to be changed by the bot
 */
public data class ForumTopicCreated(
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
    /**
     * *Optional*. *True*, if the name of the topic wasn't specified explicitly by its creator and
     * likely needs to be changed by the bot
     */
    @get:JsonProperty("is_name_implicit")
    @param:JsonProperty("is_name_implicit")
    public val isNameImplicit: Boolean? = null,
)
