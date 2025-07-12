package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Describes a service message about a regular gift that was sent or received.
 *
 * @see [GiftInfo] (https://core.telegram.org/bots/api/#giftinfo)
 *
 * @author KScript
 *
 * @param gift Information about the gift
 * @param ownedGiftId *Optional*. Unique identifier of the received gift for the bot; only present
 * for gifts received on behalf of business accounts
 * @param convertStarCount *Optional*. Number of Telegram Stars that can be claimed by the receiver
 * by converting the gift; omitted if conversion to Telegram Stars is impossible
 * @param prepaidUpgradeStarCount *Optional*. Number of Telegram Stars that were prepaid by the
 * sender for the ability to upgrade the gift
 * @param canBeUpgraded *Optional*. *True*, if the gift can be upgraded to a unique gift
 * @param text *Optional*. Text of the message that was added to the gift
 * @param entities *Optional*. Special entities that appear in the text
 * @param isPrivate *Optional*. *True*, if the sender and gift text are shown only to the gift
 * receiver; otherwise, everyone will be able to see them
 */
public data class GiftInfo(
    /**
     * Information about the gift
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: Gift,
    /**
     * *Optional*. Unique identifier of the received gift for the bot; only present for gifts
     * received on behalf of business accounts
     */
    @get:JsonProperty("owned_gift_id")
    @param:JsonProperty("owned_gift_id")
    public val ownedGiftId: String? = null,
    /**
     * *Optional*. Number of Telegram Stars that can be claimed by the receiver by converting the
     * gift; omitted if conversion to Telegram Stars is impossible
     */
    @get:JsonProperty("convert_star_count")
    @param:JsonProperty("convert_star_count")
    public val convertStarCount: Int? = null,
    /**
     * *Optional*. Number of Telegram Stars that were prepaid by the sender for the ability to
     * upgrade the gift
     */
    @get:JsonProperty("prepaid_upgrade_star_count")
    @param:JsonProperty("prepaid_upgrade_star_count")
    public val prepaidUpgradeStarCount: Int? = null,
    /**
     * *Optional*. *True*, if the gift can be upgraded to a unique gift
     */
    @get:JsonProperty("can_be_upgraded")
    @param:JsonProperty("can_be_upgraded")
    public val canBeUpgraded: Boolean? = null,
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
)
