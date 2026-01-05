package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean

/**
 * This object describes the types of gifts that can be gifted to a user or a chat.
 *
 * @see [AcceptedGiftTypes] (https://core.telegram.org/bots/api/#acceptedgifttypes)
 *
 * @author KScript
 *
 * @param unlimitedGifts *True*, if unlimited regular gifts are accepted
 * @param limitedGifts *True*, if limited regular gifts are accepted
 * @param uniqueGifts *True*, if unique gifts or gifts that can be upgraded to unique for free are
 * accepted
 * @param premiumSubscription *True*, if a Telegram Premium subscription is accepted
 * @param giftsFromChannels *True*, if transfers of unique gifts from channels are accepted
 */
public data class AcceptedGiftTypes(
    /**
     * *True*, if unlimited regular gifts are accepted
     */
    @get:JsonProperty("unlimited_gifts")
    @param:JsonProperty("unlimited_gifts")
    public val unlimitedGifts: Boolean,
    /**
     * *True*, if limited regular gifts are accepted
     */
    @get:JsonProperty("limited_gifts")
    @param:JsonProperty("limited_gifts")
    public val limitedGifts: Boolean,
    /**
     * *True*, if unique gifts or gifts that can be upgraded to unique for free are accepted
     */
    @get:JsonProperty("unique_gifts")
    @param:JsonProperty("unique_gifts")
    public val uniqueGifts: Boolean,
    /**
     * *True*, if a Telegram Premium subscription is accepted
     */
    @get:JsonProperty("premium_subscription")
    @param:JsonProperty("premium_subscription")
    public val premiumSubscription: Boolean,
    /**
     * *True*, if transfers of unique gifts from channels are accepted
     */
    @get:JsonProperty("gifts_from_channels")
    @param:JsonProperty("gifts_from_channels")
    public val giftsFromChannels: Boolean,
)
