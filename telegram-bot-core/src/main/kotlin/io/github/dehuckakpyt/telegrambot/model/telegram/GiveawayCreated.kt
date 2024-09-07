package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents a service message about the creation of a scheduled giveaway.
 *
 * @see [GiveawayCreated] (https://core.telegram.org/bots/api/#giveawaycreated)
 *
 * @author KScript
 *
 * @param prizeStarCount *Optional*. The number of Telegram Stars to be split between giveaway
 * winners; for Telegram Star giveaways only
 */
public data class GiveawayCreated(
    /**
     * *Optional*. The number of Telegram Stars to be split between giveaway winners; for Telegram
     * Star giveaways only
     */
    @get:JsonProperty("prize_star_count")
    @param:JsonProperty("prize_star_count")
    public val prizeStarCount: Int? = null,
)
