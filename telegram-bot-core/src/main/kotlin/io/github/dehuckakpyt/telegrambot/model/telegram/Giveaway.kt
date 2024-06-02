package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * This object represents a message about a scheduled giveaway.
 *
 * @see [Giveaway] (https://core.telegram.org/bots/api/#giveaway)
 *
 * @author KScript
 *
 * @param chats The list of chats which the user must join to participate in the giveaway
 * @param winnersSelectionDate Point in time (Unix timestamp) when winners of the giveaway will be
 * selected
 * @param winnerCount The number of users which are supposed to be selected as winners of the
 * giveaway
 * @param onlyNewMembers *Optional*. *True*, if only users who join the chats after the giveaway
 * started should be eligible to win
 * @param hasPublicWinners *Optional*. *True*, if the list of giveaway winners will be visible to
 * everyone
 * @param prizeDescription *Optional*. Description of additional giveaway prize
 * @param countryCodes *Optional*. A list of two-letter [ISO 3166-1
 * alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) country codes indicating the countries
 * from which eligible users for the giveaway must come. If empty, then all users can participate in
 * the giveaway. Users with a phone number that was bought on Fragment can always participate in
 * giveaways.
 * @param premiumSubscriptionMonthCount *Optional*. The number of months the Telegram Premium
 * subscription won from the giveaway will be active for
 */
public data class Giveaway(
    /**
     * The list of chats which the user must join to participate in the giveaway
     */
    @get:JsonProperty("chats")
    @param:JsonProperty("chats")
    public val chats: List<Chat>,
    /**
     * Point in time (Unix timestamp) when winners of the giveaway will be selected
     */
    @get:JsonProperty("winners_selection_date")
    @param:JsonProperty("winners_selection_date")
    public val winnersSelectionDate: Long,
    /**
     * The number of users which are supposed to be selected as winners of the giveaway
     */
    @get:JsonProperty("winner_count")
    @param:JsonProperty("winner_count")
    public val winnerCount: Int,
    /**
     * *Optional*. *True*, if only users who join the chats after the giveaway started should be
     * eligible to win
     */
    @get:JsonProperty("only_new_members")
    @param:JsonProperty("only_new_members")
    public val onlyNewMembers: Boolean? = null,
    /**
     * *Optional*. *True*, if the list of giveaway winners will be visible to everyone
     */
    @get:JsonProperty("has_public_winners")
    @param:JsonProperty("has_public_winners")
    public val hasPublicWinners: Boolean? = null,
    /**
     * *Optional*. Description of additional giveaway prize
     */
    @get:JsonProperty("prize_description")
    @param:JsonProperty("prize_description")
    public val prizeDescription: String? = null,
    /**
     * *Optional*. A list of two-letter [ISO 3166-1
     * alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) country codes indicating the
     * countries from which eligible users for the giveaway must come. If empty, then all users can
     * participate in the giveaway. Users with a phone number that was bought on Fragment can always
     * participate in giveaways.
     */
    @get:JsonProperty("country_codes")
    @param:JsonProperty("country_codes")
    public val countryCodes: List<String>? = null,
    /**
     * *Optional*. The number of months the Telegram Premium subscription won from the giveaway will
     * be active for
     */
    @get:JsonProperty("premium_subscription_month_count")
    @param:JsonProperty("premium_subscription_month_count")
    public val premiumSubscriptionMonthCount: Int? = null,
)
