package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 11.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public data class Giveaway(
    @param:JsonProperty("chats") val chats: List<Chat>,
    @param:JsonProperty("winners_selection_date") val winnersSelectionDate: Long,
    @param:JsonProperty("winner_count") val winnerCount: Long,
    @param:JsonProperty("only_new_members") val onlyNewMembers: Boolean? = null,
    @param:JsonProperty("has_public_winners") val hasPublicWinners: Boolean? = null,
    @param:JsonProperty("prize_description") val prizeDescription: String? = null,
    @param:JsonProperty("country_codes") val countryCodes: List<String> = emptyList(),
    @param:JsonProperty("premium_subscription_month_count") val premiumSubscriptionMonthCount: Int? = null,
)

public data class GiveawayWinners(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("giveaway_message_id") val giveawayMessageId: Long,
    @param:JsonProperty("winners_selection_date") val winnersSelectionDate: Long,
    @param:JsonProperty("winner_count") val winnerCount: Int,
    @param:JsonProperty("winners") val winners: List<User>,
    @param:JsonProperty("additional_chat_count") val additionalChatCount: Int? = null,
    @param:JsonProperty("premium_subscription_month_count") val premiumSubscriptionMonthCount: Int? = null,
    @param:JsonProperty("unclaimed_prize_count") val unclaimedPrizeCount: Int? = null,
    @param:JsonProperty("only_new_members") val onlyNewMembers: Boolean? = null,
    @param:JsonProperty("was_refunded") val wasRefunded: Boolean? = null,
    @param:JsonProperty("prize_description") val prizeDescription: String? = null,
)

public class GiveawayCreated

public data class GiveawayCompleted(
    @param:JsonProperty("winner_count") val winnerCount: Int,
    @param:JsonProperty("unclaimed_prize_count") val unclaimedPrizeCount: Int,
    @param:JsonProperty("giveaway_message") val giveawayMessage: Message,
)