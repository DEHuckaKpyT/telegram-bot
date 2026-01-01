package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Describes a service message about a unique gift that was sent or received.
 *
 * @see [UniqueGiftInfo] (https://core.telegram.org/bots/api/#uniquegiftinfo)
 *
 * @author KScript
 *
 * @param gift Information about the gift
 * @param origin Origin of the gift. Currently, either “upgrade” for gifts upgraded from regular
 * gifts, “transfer” for gifts transferred from other users or channels, “resale” for gifts bought from
 * other users, “gifted_upgrade” for upgrades purchased after the gift was sent, or “offer” for gifts
 * bought or sold through gift purchase offers
 * @param lastResaleCurrency *Optional*. For gifts bought from other users, the currency in which
 * the payment for the gift was done. Currently, one of “XTR” for Telegram Stars or “TON” for toncoins.
 * @param lastResaleAmount *Optional*. For gifts bought from other users, the price paid for the
 * gift in either Telegram Stars or nanotoncoins
 * @param ownedGiftId *Optional*. Unique identifier of the received gift for the bot; only present
 * for gifts received on behalf of business accounts
 * @param transferStarCount *Optional*. Number of Telegram Stars that must be paid to transfer the
 * gift; omitted if the bot cannot transfer the gift
 * @param nextTransferDate *Optional*. Point in time (Unix timestamp) when the gift can be
 * transferred. If it is in the past, then the gift can be transferred now
 */
public data class UniqueGiftInfo(
    /**
     * Information about the gift
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: UniqueGift,
    /**
     * Origin of the gift. Currently, either “upgrade” for gifts upgraded from regular gifts,
     * “transfer” for gifts transferred from other users or channels, “resale” for gifts bought from
     * other users, “gifted_upgrade” for upgrades purchased after the gift was sent, or “offer” for
     * gifts bought or sold through gift purchase offers
     */
    @get:JsonProperty("origin")
    @param:JsonProperty("origin")
    public val origin: String,
    /**
     * *Optional*. For gifts bought from other users, the currency in which the payment for the gift
     * was done. Currently, one of “XTR” for Telegram Stars or “TON” for toncoins.
     */
    @get:JsonProperty("last_resale_currency")
    @param:JsonProperty("last_resale_currency")
    public val lastResaleCurrency: String? = null,
    /**
     * *Optional*. For gifts bought from other users, the price paid for the gift in either Telegram
     * Stars or nanotoncoins
     */
    @get:JsonProperty("last_resale_amount")
    @param:JsonProperty("last_resale_amount")
    public val lastResaleAmount: Int? = null,
    /**
     * *Optional*. Unique identifier of the received gift for the bot; only present for gifts
     * received on behalf of business accounts
     */
    @get:JsonProperty("owned_gift_id")
    @param:JsonProperty("owned_gift_id")
    public val ownedGiftId: String? = null,
    /**
     * *Optional*. Number of Telegram Stars that must be paid to transfer the gift; omitted if the
     * bot cannot transfer the gift
     */
    @get:JsonProperty("transfer_star_count")
    @param:JsonProperty("transfer_star_count")
    public val transferStarCount: Int? = null,
    /**
     * *Optional*. Point in time (Unix timestamp) when the gift can be transferred. If it is in the
     * past, then the gift can be transferred now
     */
    @get:JsonProperty("next_transfer_date")
    @param:JsonProperty("next_transfer_date")
    public val nextTransferDate: Long? = null,
)
