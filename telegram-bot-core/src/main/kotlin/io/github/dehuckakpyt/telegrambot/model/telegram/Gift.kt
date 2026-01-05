package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * This object represents a gift that can be sent by the bot.
 *
 * @see [Gift] (https://core.telegram.org/bots/api/#gift)
 *
 * @author KScript
 *
 * @param id Unique identifier of the gift
 * @param sticker The sticker that represents the gift
 * @param starCount The number of Telegram Stars that must be paid to send the sticker
 * @param upgradeStarCount *Optional*. The number of Telegram Stars that must be paid to upgrade the
 * gift to a unique one
 * @param isPremium *Optional*. *True*, if the gift can only be purchased by Telegram Premium
 * subscribers
 * @param hasColors *Optional*. *True*, if the gift can be used (after being upgraded) to customize
 * a user's appearance
 * @param totalCount *Optional*. The total number of gifts of this type that can be sent by all
 * users; for limited gifts only
 * @param remainingCount *Optional*. The number of remaining gifts of this type that can be sent by
 * all users; for limited gifts only
 * @param personalTotalCount *Optional*. The total number of gifts of this type that can be sent by
 * the bot; for limited gifts only
 * @param personalRemainingCount *Optional*. The number of remaining gifts of this type that can be
 * sent by the bot; for limited gifts only
 * @param background *Optional*. Background of the gift
 * @param uniqueGiftVariantCount *Optional*. The total number of different unique gifts that can be
 * obtained by upgrading the gift
 * @param publisherChat *Optional*. Information about the chat that published the gift
 */
public data class Gift(
    /**
     * Unique identifier of the gift
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * The sticker that represents the gift
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker,
    /**
     * The number of Telegram Stars that must be paid to send the sticker
     */
    @get:JsonProperty("star_count")
    @param:JsonProperty("star_count")
    public val starCount: Int,
    /**
     * *Optional*. The number of Telegram Stars that must be paid to upgrade the gift to a unique
     * one
     */
    @get:JsonProperty("upgrade_star_count")
    @param:JsonProperty("upgrade_star_count")
    public val upgradeStarCount: Int? = null,
    /**
     * *Optional*. *True*, if the gift can only be purchased by Telegram Premium subscribers
     */
    @get:JsonProperty("is_premium")
    @param:JsonProperty("is_premium")
    public val isPremium: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift can be used (after being upgraded) to customize a user's
     * appearance
     */
    @get:JsonProperty("has_colors")
    @param:JsonProperty("has_colors")
    public val hasColors: Boolean? = null,
    /**
     * *Optional*. The total number of gifts of this type that can be sent by all users; for limited
     * gifts only
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int? = null,
    /**
     * *Optional*. The number of remaining gifts of this type that can be sent by all users; for
     * limited gifts only
     */
    @get:JsonProperty("remaining_count")
    @param:JsonProperty("remaining_count")
    public val remainingCount: Int? = null,
    /**
     * *Optional*. The total number of gifts of this type that can be sent by the bot; for limited
     * gifts only
     */
    @get:JsonProperty("personal_total_count")
    @param:JsonProperty("personal_total_count")
    public val personalTotalCount: Int? = null,
    /**
     * *Optional*. The number of remaining gifts of this type that can be sent by the bot; for
     * limited gifts only
     */
    @get:JsonProperty("personal_remaining_count")
    @param:JsonProperty("personal_remaining_count")
    public val personalRemainingCount: Int? = null,
    /**
     * *Optional*. Background of the gift
     */
    @get:JsonProperty("background")
    @param:JsonProperty("background")
    public val background: GiftBackground? = null,
    /**
     * *Optional*. The total number of different unique gifts that can be obtained by upgrading the
     * gift
     */
    @get:JsonProperty("unique_gift_variant_count")
    @param:JsonProperty("unique_gift_variant_count")
    public val uniqueGiftVariantCount: Int? = null,
    /**
     * *Optional*. Information about the chat that published the gift
     */
    @get:JsonProperty("publisher_chat")
    @param:JsonProperty("publisher_chat")
    public val publisherChat: Chat? = null,
)
