package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents an animated emoji that displays a random value.
 *
 * @see [Dice] (https://core.telegram.org/bots/api/#dice)
 *
 * @author KScript
 *
 * @param emoji Emoji on which the dice throw animation is based
 * @param value Value of the dice, 1-6 for “🎲”, “🎯” and “🎳” base emoji, 1-5 for “🏀” and “⚽” base
 * emoji, 1-64 for “🎰” base emoji
 */
public data class Dice(
    /**
     * Emoji on which the dice throw animation is based
     */
    @get:JsonProperty("emoji")
    @param:JsonProperty("emoji")
    public val emoji: String,
    /**
     * Value of the dice, 1-6 for “🎲”, “🎯” and “🎳” base emoji, 1-5 for “🏀” and “⚽” base emoji,
     * 1-64 for “🎰” base emoji
     */
    @get:JsonProperty("value")
    @param:JsonProperty("value")
    public val `value`: Int,
)
