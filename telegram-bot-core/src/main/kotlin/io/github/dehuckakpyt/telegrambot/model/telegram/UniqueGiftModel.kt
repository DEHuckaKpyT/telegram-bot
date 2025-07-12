package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object describes the model of a unique gift.
 *
 * @see [UniqueGiftModel] (https://core.telegram.org/bots/api/#uniquegiftmodel)
 *
 * @author KScript
 *
 * @param name Name of the model
 * @param sticker The sticker that represents the unique gift
 * @param rarityPerMille The number of unique gifts that receive this model for every 1000 gifts
 * upgraded
 */
public data class UniqueGiftModel(
    /**
     * Name of the model
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
