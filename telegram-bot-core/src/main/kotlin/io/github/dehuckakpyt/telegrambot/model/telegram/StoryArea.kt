package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Describes a clickable area on a story media.
 *
 * @see [StoryArea] (https://core.telegram.org/bots/api/#storyarea)
 *
 * @author KScript
 *
 * @param position Position of the area
 * @param type Type of the area
 */
public data class StoryArea(
    /**
     * Position of the area
     */
    @get:JsonProperty("position")
    @param:JsonProperty("position")
    public val position: StoryAreaPosition,
    /**
     * Type of the area
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: StoryAreaType,
)
