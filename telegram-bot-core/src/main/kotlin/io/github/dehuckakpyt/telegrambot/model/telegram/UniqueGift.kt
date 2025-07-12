package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object describes a unique gift that was upgraded from a regular gift.
 *
 * @see [UniqueGift] (https://core.telegram.org/bots/api/#uniquegift)
 *
 * @author KScript
 *
 * @param baseName Human-readable name of the regular gift from which this unique gift was upgraded
 * @param name Unique name of the gift. This name can be used in `https://t.me/nft/...` links and
 * story areas
 * @param number Unique number of the upgraded gift among gifts upgraded from the same regular gift
 * @param model Model of the gift
 * @param symbol Symbol of the gift
 * @param backdrop Backdrop of the gift
 */
public data class UniqueGift(
    /**
     * Human-readable name of the regular gift from which this unique gift was upgraded
     */
    @get:JsonProperty("base_name")
    @param:JsonProperty("base_name")
    public val baseName: String,
    /**
     * Unique name of the gift. This name can be used in `https://t.me/nft/...` links and story
     * areas
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
    /**
     * Unique number of the upgraded gift among gifts upgraded from the same regular gift
     */
    @get:JsonProperty("number")
    @param:JsonProperty("number")
    public val number: Int,
    /**
     * Model of the gift
     */
    @get:JsonProperty("model")
    @param:JsonProperty("model")
    public val model: UniqueGiftModel,
    /**
     * Symbol of the gift
     */
    @get:JsonProperty("symbol")
    @param:JsonProperty("symbol")
    public val symbol: UniqueGiftSymbol,
    /**
     * Backdrop of the gift
     */
    @get:JsonProperty("backdrop")
    @param:JsonProperty("backdrop")
    public val backdrop: UniqueGiftBackdrop,
)
