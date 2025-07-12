package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Describes a story area pointing to a suggested reaction. Currently, a story can have up to 5
 * suggested reaction areas.
 *
 * @see [StoryAreaTypeSuggestedReaction]
 * (https://core.telegram.org/bots/api/#storyareatypesuggestedreaction)
 *
 * @author KScript
 *
 * @param type Type of the area, always “suggested_reaction”
 * @param reactionType Type of the reaction
 * @param isDark *Optional*. Pass *True* if the reaction area has a dark background
 * @param isFlipped *Optional*. Pass *True* if reaction area corner is flipped
 */
public data class StoryAreaTypeSuggestedReaction(
    /**
     * Type of the area, always “suggested_reaction”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Type of the reaction
     */
    @get:JsonProperty("reaction_type")
    @param:JsonProperty("reaction_type")
    public val reactionType: ReactionType,
    /**
     * *Optional*. Pass *True* if the reaction area has a dark background
     */
    @get:JsonProperty("is_dark")
    @param:JsonProperty("is_dark")
    public val isDark: Boolean? = null,
    /**
     * *Optional*. Pass *True* if reaction area corner is flipped
     */
    @get:JsonProperty("is_flipped")
    @param:JsonProperty("is_flipped")
    public val isFlipped: Boolean? = null,
) : StoryAreaType
