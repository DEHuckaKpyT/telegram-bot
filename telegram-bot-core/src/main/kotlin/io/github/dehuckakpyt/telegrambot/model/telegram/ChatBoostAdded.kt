package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents a service message about a user boosting a chat.
 *
 * @see [ChatBoostAdded] (https://core.telegram.org/bots/api/#chatboostadded)
 *
 * @author KScript
 *
 * @param boostCount Number of boosts added by the user
 */
public data class ChatBoostAdded(
    /**
     * Number of boosts added by the user
     */
    @get:JsonProperty("boost_count")
    @param:JsonProperty("boost_count")
    public val boostCount: Int,
)
