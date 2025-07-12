package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a story area pointing to a unique gift. Currently, a story can have at most 1 unique
 * gift area.
 *
 * @see [StoryAreaTypeUniqueGift] (https://core.telegram.org/bots/api/#storyareatypeuniquegift)
 *
 * @author KScript
 *
 * @param type Type of the area, always “unique_gift”
 * @param name Unique name of the gift
 */
public data class StoryAreaTypeUniqueGift(
    /**
     * Type of the area, always “unique_gift”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Unique name of the gift
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
) : StoryAreaType
