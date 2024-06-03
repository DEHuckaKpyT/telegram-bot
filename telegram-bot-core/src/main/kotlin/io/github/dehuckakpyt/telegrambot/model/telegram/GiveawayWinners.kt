package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object represents a message about the completion of a giveaway with public winners.
 *
 * @see [GiveawayWinners] (https://core.telegram.org/bots/api/#giveawaywinners)
 *
 * @author KScript
 *
 * @param chat The chat that created the giveaway
 * @param giveawayMessageId Identifier of the message with the giveaway in the chat
 * @param winnersSelectionDate Point in time (Unix timestamp) when winners of the giveaway were
 * selected
 * @param winnerCount Total number of winners in the giveaway
 * @param winners List of up to 100 winners of the giveaway
 * @param additionalChatCount *Optional*. The number of other chats the user had to join in order to
 * be eligible for the giveaway
 * @param premiumSubscriptionMonthCount *Optional*. The number of months the Telegram Premium
 * subscription won from the giveaway will be active for
 * @param unclaimedPrizeCount *Optional*. Number of undistributed prizes
 * @param onlyNewMembers *Optional*. *True*, if only users who had joined the chats after the
 * giveaway started were eligible to win
 * @param wasRefunded *Optional*. *True*, if the giveaway was canceled because the payment for it
 * was refunded
 * @param prizeDescription *Optional*. Description of additional giveaway prize
 */
public data class GiveawayWinners(
    /**
     * The chat that created the giveaway
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Identifier of the message with the giveaway in the chat
     */
    @get:JsonProperty("giveaway_message_id")
    @param:JsonProperty("giveaway_message_id")
    public val giveawayMessageId: Long,
    /**
     * Point in time (Unix timestamp) when winners of the giveaway were selected
     */
    @get:JsonProperty("winners_selection_date")
    @param:JsonProperty("winners_selection_date")
    public val winnersSelectionDate: Long,
    /**
     * Total number of winners in the giveaway
     */
    @get:JsonProperty("winner_count")
    @param:JsonProperty("winner_count")
    public val winnerCount: Int,
    /**
     * List of up to 100 winners of the giveaway
     */
    @get:JsonProperty("winners")
    @param:JsonProperty("winners")
    public val winners: List<User>,
    /**
     * *Optional*. The number of other chats the user had to join in order to be eligible for the
     * giveaway
     */
    @get:JsonProperty("additional_chat_count")
    @param:JsonProperty("additional_chat_count")
    public val additionalChatCount: Int? = null,
    /**
     * *Optional*. The number of months the Telegram Premium subscription won from the giveaway will
     * be active for
     */
    @get:JsonProperty("premium_subscription_month_count")
    @param:JsonProperty("premium_subscription_month_count")
    public val premiumSubscriptionMonthCount: Int? = null,
    /**
     * *Optional*. Number of undistributed prizes
     */
    @get:JsonProperty("unclaimed_prize_count")
    @param:JsonProperty("unclaimed_prize_count")
    public val unclaimedPrizeCount: Int? = null,
    /**
     * *Optional*. *True*, if only users who had joined the chats after the giveaway started were
     * eligible to win
     */
    @get:JsonProperty("only_new_members")
    @param:JsonProperty("only_new_members")
    public val onlyNewMembers: Boolean? = null,
    /**
     * *Optional*. *True*, if the giveaway was canceled because the payment for it was refunded
     */
    @get:JsonProperty("was_refunded")
    @param:JsonProperty("was_refunded")
    public val wasRefunded: Boolean? = null,
    /**
     * *Optional*. Description of additional giveaway prize
     */
    @get:JsonProperty("prize_description")
    @param:JsonProperty("prize_description")
    public val prizeDescription: String? = null,
)
