package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * This object describes a unique gift that was upgraded from a regular gift.
 *
 * @see [UniqueGift] (https://core.telegram.org/bots/api/#uniquegift)
 *
 * @author KScript
 *
 * @param giftId Identifier of the regular gift from which the gift was upgraded
 * @param baseName Human-readable name of the regular gift from which this unique gift was upgraded
 * @param name Unique name of the gift. This name can be used in `https://t.me/nft/...` links and
 * story areas
 * @param number Unique number of the upgraded gift among gifts upgraded from the same regular gift
 * @param model Model of the gift
 * @param symbol Symbol of the gift
 * @param backdrop Backdrop of the gift
 * @param isPremium *Optional*. *True*, if the original regular gift was exclusively purchaseable by
 * Telegram Premium subscribers
 * @param isBurned *Optional*. *True*, if the gift was used to craft another gift and isn't
 * available anymore
 * @param isFromBlockchain *Optional*. *True*, if the gift is assigned from the TON blockchain and
 * can't be resold or transferred in Telegram
 * @param colors *Optional*. The color scheme that can be used by the gift's owner for the chat's
 * name, replies to messages and link previews; for business account gifts and gifts that are currently
 * on sale only
 * @param publisherChat *Optional*. Information about the chat that published the gift
 */
public data class UniqueGift(
    /**
     * Identifier of the regular gift from which the gift was upgraded
     */
    @get:JsonProperty("gift_id")
    @param:JsonProperty("gift_id")
    public val giftId: String,
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
    /**
     * *Optional*. *True*, if the original regular gift was exclusively purchaseable by Telegram
     * Premium subscribers
     */
    @get:JsonProperty("is_premium")
    @param:JsonProperty("is_premium")
    public val isPremium: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift was used to craft another gift and isn't available anymore
     */
    @get:JsonProperty("is_burned")
    @param:JsonProperty("is_burned")
    public val isBurned: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift is assigned from the TON blockchain and can't be resold or
     * transferred in Telegram
     */
    @get:JsonProperty("is_from_blockchain")
    @param:JsonProperty("is_from_blockchain")
    public val isFromBlockchain: Boolean? = null,
    /**
     * *Optional*. The color scheme that can be used by the gift's owner for the chat's name,
     * replies to messages and link previews; for business account gifts and gifts that are currently
     * on sale only
     */
    @get:JsonProperty("colors")
    @param:JsonProperty("colors")
    public val colors: UniqueGiftColors? = null,
    /**
     * *Optional*. Information about the chat that published the gift
     */
    @get:JsonProperty("publisher_chat")
    @param:JsonProperty("publisher_chat")
    public val publisherChat: Chat? = null,
)
