package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Describes a service message about a change in the price of paid messages within a chat.
 *
 * @see [PaidMessagePriceChanged] (https://core.telegram.org/bots/api/#paidmessagepricechanged)
 *
 * @author KScript
 *
 * @param paidMessageStarCount The new number of Telegram Stars that must be paid by
 * non-administrator users of the supergroup chat for each sent message
 */
public data class PaidMessagePriceChanged(
    /**
     * The new number of Telegram Stars that must be paid by non-administrator users of the
     * supergroup chat for each sent message
     */
    @get:JsonProperty("paid_message_star_count")
    @param:JsonProperty("paid_message_star_count")
    public val paidMessageStarCount: Int,
)
