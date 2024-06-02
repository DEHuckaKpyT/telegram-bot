package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Created on 02.06.2024.
 *
 * Represents a reaction added to a message along with the number of times it was added.
 *
 * @see [ReactionCount] (https://core.telegram.org/bots/api/#reactioncount)
 *
 * @author KScript
 *
 * @param type Type of the reaction
 * @param totalCount Number of times the reaction was added
 */
public data class ReactionCount(
    /**
     * Type of the reaction
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: ReactionType,
    /**
     * Number of times the reaction was added
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int,
)
