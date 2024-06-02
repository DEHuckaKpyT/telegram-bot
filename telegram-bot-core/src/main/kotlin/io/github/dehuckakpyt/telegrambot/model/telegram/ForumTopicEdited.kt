package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a service message about an edited forum topic.
 *
 * @see [ForumTopicEdited] (https://core.telegram.org/bots/api/#forumtopicedited)
 *
 * @author KScript
 *
 * @param name *Optional*. New name of the topic, if it was edited
 * @param iconCustomEmojiId *Optional*. New identifier of the custom emoji shown as the topic icon,
 * if it was edited; an empty string if the icon was removed
 */
public data class ForumTopicEdited(
    /**
     * *Optional*. New name of the topic, if it was edited
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String? = null,
    /**
     * *Optional*. New identifier of the custom emoji shown as the topic icon, if it was edited; an
     * empty string if the icon was removed
     */
    @get:JsonProperty("icon_custom_emoji_id")
    @param:JsonProperty("icon_custom_emoji_id")
    public val iconCustomEmojiId: String? = null,
)
