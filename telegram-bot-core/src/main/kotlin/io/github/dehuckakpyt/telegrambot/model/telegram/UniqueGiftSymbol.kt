package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object describes the symbol shown on the pattern of a unique gift.
 *
 * @see [UniqueGiftSymbol] (https://core.telegram.org/bots/api/#uniquegiftsymbol)
 *
 * @author KScript
 *
 * @param name Name of the symbol
 * @param sticker The sticker that represents the unique gift
 * @param rarityPerMille The number of unique gifts that receive this model for every 1000 gifts
 * upgraded
 */
public data class UniqueGiftSymbol(
    /**
     * Name of the symbol
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
    /**
     * The sticker that represents the unique gift
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker,
    /**
     * The number of unique gifts that receive this model for every 1000 gifts upgraded
     */
    @get:JsonProperty("rarity_per_mille")
    @param:JsonProperty("rarity_per_mille")
    public val rarityPerMille: Int,
)
