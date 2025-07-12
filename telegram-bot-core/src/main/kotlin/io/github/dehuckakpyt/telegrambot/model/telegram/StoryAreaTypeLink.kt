package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a story area pointing to an HTTP or tg:// link. Currently, a story can have up to 3
 * link areas.
 *
 * @see [StoryAreaTypeLink] (https://core.telegram.org/bots/api/#storyareatypelink)
 *
 * @author KScript
 *
 * @param type Type of the area, always “link”
 * @param url HTTP or tg:// URL to be opened when the area is clicked
 */
public data class StoryAreaTypeLink(
    /**
     * Type of the area, always “link”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * HTTP or tg:// URL to be opened when the area is clicked
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
) : StoryAreaType
