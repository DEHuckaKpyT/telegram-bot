package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Describes a unique gift received and owned by a user or a chat.
 *
 * @see [OwnedGiftUnique] (https://core.telegram.org/bots/api/#ownedgiftunique)
 *
 * @author KScript
 *
 * @param type Type of the gift, always “unique”
 * @param gift Information about the unique gift
 * @param ownedGiftId *Optional*. Unique identifier of the received gift for the bot; for gifts
 * received on behalf of business accounts only
 * @param senderUser *Optional*. Sender of the gift if it is a known user
 * @param sendDate Date the gift was sent in Unix time
 * @param isSaved *Optional*. *True*, if the gift is displayed on the account's profile page; for
 * gifts received on behalf of business accounts only
 * @param canBeTransferred *Optional*. *True*, if the gift can be transferred to another owner; for
 * gifts received on behalf of business accounts only
 * @param transferStarCount *Optional*. Number of Telegram Stars that must be paid to transfer the
 * gift; omitted if the bot cannot transfer the gift
 * @param nextTransferDate *Optional*. Point in time (Unix timestamp) when the gift can be
 * transferred. If it is in the past, then the gift can be transferred now
 */
public data class OwnedGiftUnique(
    /**
     * Type of the gift, always “unique”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Information about the unique gift
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: UniqueGift,
    /**
     * *Optional*. Unique identifier of the received gift for the bot; for gifts received on behalf
     * of business accounts only
     */
    @get:JsonProperty("owned_gift_id")
    @param:JsonProperty("owned_gift_id")
    override val ownedGiftId: String? = null,
    /**
     * *Optional*. Sender of the gift if it is a known user
     */
    @get:JsonProperty("sender_user")
    @param:JsonProperty("sender_user")
    override val senderUser: User? = null,
    /**
     * Date the gift was sent in Unix time
     */
    @get:JsonProperty("send_date")
    @param:JsonProperty("send_date")
    override val sendDate: Long,
    /**
     * *Optional*. *True*, if the gift is displayed on the account's profile page; for gifts
     * received on behalf of business accounts only
     */
    @get:JsonProperty("is_saved")
    @param:JsonProperty("is_saved")
    override val isSaved: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift can be transferred to another owner; for gifts received on
     * behalf of business accounts only
     */
    @get:JsonProperty("can_be_transferred")
    @param:JsonProperty("can_be_transferred")
    public val canBeTransferred: Boolean? = null,
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
) : OwnedGift
