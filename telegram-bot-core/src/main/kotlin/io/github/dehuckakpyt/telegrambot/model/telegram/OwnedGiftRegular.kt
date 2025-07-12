package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Describes a regular gift owned by a user or a chat.
 *
 * @see [OwnedGiftRegular] (https://core.telegram.org/bots/api/#ownedgiftregular)
 *
 * @author KScript
 *
 * @param type Type of the gift, always “regular”
 * @param gift Information about the regular gift
 * @param ownedGiftId *Optional*. Unique identifier of the gift for the bot; for gifts received on
 * behalf of business accounts only
 * @param senderUser *Optional*. Sender of the gift if it is a known user
 * @param sendDate Date the gift was sent in Unix time
 * @param text *Optional*. Text of the message that was added to the gift
 * @param entities *Optional*. Special entities that appear in the text
 * @param isPrivate *Optional*. *True*, if the sender and gift text are shown only to the gift
 * receiver; otherwise, everyone will be able to see them
 * @param isSaved *Optional*. *True*, if the gift is displayed on the account's profile page; for
 * gifts received on behalf of business accounts only
 * @param canBeUpgraded *Optional*. *True*, if the gift can be upgraded to a unique gift; for gifts
 * received on behalf of business accounts only
 * @param wasRefunded *Optional*. *True*, if the gift was refunded and isn't available anymore
 * @param convertStarCount *Optional*. Number of Telegram Stars that can be claimed by the receiver
 * instead of the gift; omitted if the gift cannot be converted to Telegram Stars
 * @param prepaidUpgradeStarCount *Optional*. Number of Telegram Stars that were paid by the sender
 * for the ability to upgrade the gift
 */
public data class OwnedGiftRegular(
    /**
     * Type of the gift, always “regular”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Information about the regular gift
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: Gift,
    /**
     * *Optional*. Unique identifier of the gift for the bot; for gifts received on behalf of
     * business accounts only
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
     * *Optional*. Text of the message that was added to the gift
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String? = null,
    /**
     * *Optional*. Special entities that appear in the text
     */
    @get:JsonProperty("entities")
    @param:JsonProperty("entities")
    public val entities: List<MessageEntity>? = null,
    /**
     * *Optional*. *True*, if the sender and gift text are shown only to the gift receiver;
     * otherwise, everyone will be able to see them
     */
    @get:JsonProperty("is_private")
    @param:JsonProperty("is_private")
    public val isPrivate: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift is displayed on the account's profile page; for gifts
     * received on behalf of business accounts only
     */
    @get:JsonProperty("is_saved")
    @param:JsonProperty("is_saved")
    override val isSaved: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift can be upgraded to a unique gift; for gifts received on
     * behalf of business accounts only
     */
    @get:JsonProperty("can_be_upgraded")
    @param:JsonProperty("can_be_upgraded")
    public val canBeUpgraded: Boolean? = null,
    /**
     * *Optional*. *True*, if the gift was refunded and isn't available anymore
     */
    @get:JsonProperty("was_refunded")
    @param:JsonProperty("was_refunded")
    public val wasRefunded: Boolean? = null,
    /**
     * *Optional*. Number of Telegram Stars that can be claimed by the receiver instead of the gift;
     * omitted if the gift cannot be converted to Telegram Stars
     */
    @get:JsonProperty("convert_star_count")
    @param:JsonProperty("convert_star_count")
    public val convertStarCount: Int? = null,
    /**
     * *Optional*. Number of Telegram Stars that were paid by the sender for the ability to upgrade
     * the gift
     */
    @get:JsonProperty("prepaid_upgrade_star_count")
    @param:JsonProperty("prepaid_upgrade_star_count")
    public val prepaidUpgradeStarCount: Int? = null,
) : OwnedGift
