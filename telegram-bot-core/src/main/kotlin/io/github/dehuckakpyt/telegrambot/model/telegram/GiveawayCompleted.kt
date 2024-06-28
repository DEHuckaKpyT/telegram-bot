package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents a service message about the completion of a giveaway without public
 * winners.
 *
 * @see [GiveawayCompleted] (https://core.telegram.org/bots/api/#giveawaycompleted)
 *
 * @author KScript
 *
 * @param winnerCount Number of winners in the giveaway
 * @param unclaimedPrizeCount *Optional*. Number of undistributed prizes
 * @param giveawayMessage *Optional*. Message with the giveaway that was completed, if it wasn't
 * deleted
 */
public data class GiveawayCompleted(
    /**
     * Number of winners in the giveaway
     */
    @get:JsonProperty("winner_count")
    @param:JsonProperty("winner_count")
    public val winnerCount: Int,
    /**
     * *Optional*. Number of undistributed prizes
     */
    @get:JsonProperty("unclaimed_prize_count")
    @param:JsonProperty("unclaimed_prize_count")
    public val unclaimedPrizeCount: Int? = null,
    /**
     * *Optional*. Message with the giveaway that was completed, if it wasn't deleted
     */
    @get:JsonProperty("giveaway_message")
    @param:JsonProperty("giveaway_message")
    public val giveawayMessage: Message? = null,
)
