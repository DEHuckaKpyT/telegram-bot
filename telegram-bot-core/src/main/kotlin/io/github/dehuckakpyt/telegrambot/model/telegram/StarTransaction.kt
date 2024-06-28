package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Describes a Telegram Star transaction.
 *
 * @see [StarTransaction] (https://core.telegram.org/bots/api/#startransaction)
 *
 * @author KScript
 *
 * @param id Unique identifier of the transaction. Coincides with the identifer of the original
 * transaction for refund transactions. Coincides with *SuccessfulPayment.telegram_payment_charge_id*
 * for successful incoming payments from users.
 * @param amount Number of Telegram Stars transferred by the transaction
 * @param date Date the transaction was created in Unix time
 * @param source *Optional*. Source of an incoming transaction (e.g., a user purchasing goods or
 * services, Fragment refunding a failed withdrawal). Only for incoming transactions
 * @param receiver *Optional*. Receiver of an outgoing transaction (e.g., a user for a purchase
 * refund, Fragment for a withdrawal). Only for outgoing transactions
 */
public data class StarTransaction(
    /**
     * Unique identifier of the transaction. Coincides with the identifer of the original
     * transaction for refund transactions. Coincides with
     * *SuccessfulPayment.telegram_payment_charge_id* for successful incoming payments from users.
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * Number of Telegram Stars transferred by the transaction
     */
    @get:JsonProperty("amount")
    @param:JsonProperty("amount")
    public val amount: Int,
    /**
     * Date the transaction was created in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * *Optional*. Source of an incoming transaction (e.g., a user purchasing goods or services,
     * Fragment refunding a failed withdrawal). Only for incoming transactions
     */
    @get:JsonProperty("source")
    @param:JsonProperty("source")
    public val source: TransactionPartner? = null,
    /**
     * *Optional*. Receiver of an outgoing transaction (e.g., a user for a purchase refund, Fragment
     * for a withdrawal). Only for outgoing transactions
     */
    @get:JsonProperty("receiver")
    @param:JsonProperty("receiver")
    public val `receiver`: TransactionPartner? = null,
)
