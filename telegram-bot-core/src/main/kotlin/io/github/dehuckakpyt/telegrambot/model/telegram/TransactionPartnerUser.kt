package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Describes a transaction with a user.
 *
 * @see [TransactionPartnerUser] (https://core.telegram.org/bots/api/#transactionpartneruser)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “user”
 * @param user Information about the user
 * @param invoicePayload *Optional*. Bot-specified invoice payload
 * @param subscriptionPeriod *Optional*. The duration of the paid subscription
 * @param paidMedia *Optional*. Information about the paid media bought by the user
 * @param paidMediaPayload *Optional*. Bot-specified paid media payload
 * @param gift *Optional*. The gift sent to the user by the bot
 */
public data class TransactionPartnerUser(
    /**
     * Type of the transaction partner, always “user”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Information about the user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
    /**
     * *Optional*. Bot-specified invoice payload
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String? = null,
    /**
     * *Optional*. The duration of the paid subscription
     */
    @get:JsonProperty("subscription_period")
    @param:JsonProperty("subscription_period")
    public val subscriptionPeriod: Int? = null,
    /**
     * *Optional*. Information about the paid media bought by the user
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: List<PaidMedia>? = null,
    /**
     * *Optional*. Bot-specified paid media payload
     */
    @get:JsonProperty("paid_media_payload")
    @param:JsonProperty("paid_media_payload")
    public val paidMediaPayload: String? = null,
    /**
     * *Optional*. The gift sent to the user by the bot
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: String? = null,
) : TransactionPartner
