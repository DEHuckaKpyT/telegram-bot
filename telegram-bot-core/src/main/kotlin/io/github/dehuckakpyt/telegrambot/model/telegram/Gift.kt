package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
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
 * @param totalCount *Optional*. The total number of the gifts of this type that can be sent; for
 * limited gifts only
 * @param remainingCount *Optional*. The number of remaining gifts of this type that can be sent;
 * for limited gifts only
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
     * *Optional*. The total number of the gifts of this type that can be sent; for limited gifts
     * only
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int? = null,
    /**
     * *Optional*. The number of remaining gifts of this type that can be sent; for limited gifts
     * only
     */
    @get:JsonProperty("remaining_count")
    @param:JsonProperty("remaining_count")
    public val remainingCount: Int? = null,
    /**
     * *Optional*. Information about the chat that published the gift
     */
    @get:JsonProperty("publisher_chat")
    @param:JsonProperty("publisher_chat")
    public val publisherChat: Chat? = null,
)
