package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object describes the backdrop of a unique gift.
 *
 * @see [UniqueGiftBackdrop] (https://core.telegram.org/bots/api/#uniquegiftbackdrop)
 *
 * @author KScript
 *
 * @param name Name of the backdrop
 * @param colors Colors of the backdrop
 * @param rarityPerMille The number of unique gifts that receive this backdrop for every 1000 gifts
 * upgraded
 */
public data class UniqueGiftBackdrop(
    /**
     * Name of the backdrop
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
    /**
     * Colors of the backdrop
     */
    @get:JsonProperty("colors")
    @param:JsonProperty("colors")
    public val colors: UniqueGiftBackdropColors,
    /**
     * The number of unique gifts that receive this backdrop for every 1000 gifts upgraded
     */
    @get:JsonProperty("rarity_per_mille")
    @param:JsonProperty("rarity_per_mille")
    public val rarityPerMille: Int,
)
