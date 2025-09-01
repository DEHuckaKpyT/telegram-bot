package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Describes a service message about a successful payment for a suggested post.
 *
 * @see [SuggestedPostPaid] (https://core.telegram.org/bots/api/#suggestedpostpaid)
 *
 * @author KScript
 *
 * @param suggestedPostMessage *Optional*. Message containing the suggested post. Note that the
 * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
 * *reply_to_message* field even if it itself is a reply.
 * @param currency Currency in which the payment was made. Currently, one of “XTR” for Telegram
 * Stars or “TON” for toncoins
 * @param amount *Optional*. The amount of the currency that was received by the channel in
 * nanotoncoins; for payments in toncoins only
 * @param starAmount *Optional*. The amount of Telegram Stars that was received by the channel; for
 * payments in Telegram Stars only
 */
public data class SuggestedPostPaid(
    /**
     * *Optional*. Message containing the suggested post. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain the
     * *reply_to_message* field even if it itself is a reply.
     */
    @get:JsonProperty("suggested_post_message")
    @param:JsonProperty("suggested_post_message")
    public val suggestedPostMessage: Message? = null,
    /**
     * Currency in which the payment was made. Currently, one of “XTR” for Telegram Stars or “TON”
     * for toncoins
     */
    @get:JsonProperty("currency")
    @param:JsonProperty("currency")
    public val currency: String,
    /**
     * *Optional*. The amount of the currency that was received by the channel in nanotoncoins; for
     * payments in toncoins only
     */
    @get:JsonProperty("amount")
    @param:JsonProperty("amount")
    public val amount: Int? = null,
    /**
     * *Optional*. The amount of Telegram Stars that was received by the channel; for payments in
     * Telegram Stars only
     */
    @get:JsonProperty("star_amount")
    @param:JsonProperty("star_amount")
    public val starAmount: StarAmount? = null,
)
