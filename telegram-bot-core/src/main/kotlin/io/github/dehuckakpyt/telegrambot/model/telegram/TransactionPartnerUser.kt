package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
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
 * @param paidMedia *Optional*. Information about the paid media bought by the user
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
     * *Optional*. Information about the paid media bought by the user
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: List<PaidMedia>? = null,
) : TransactionPartner
