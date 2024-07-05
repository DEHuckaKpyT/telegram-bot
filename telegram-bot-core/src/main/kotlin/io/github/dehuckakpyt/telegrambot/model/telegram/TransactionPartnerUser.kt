package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

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
) : TransactionPartner
