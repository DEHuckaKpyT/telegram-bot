package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a withdrawal transaction with Fragment.
 *
 * @see [TransactionPartnerFragment]
 * (https://core.telegram.org/bots/api/#transactionpartnerfragment)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “fragment”
 * @param withdrawalState *Optional*. State of the transaction if the transaction is outgoing
 */
public data class TransactionPartnerFragment(
    /**
     * Type of the transaction partner, always “fragment”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * *Optional*. State of the transaction if the transaction is outgoing
     */
    @get:JsonProperty("withdrawal_state")
    @param:JsonProperty("withdrawal_state")
    public val withdrawalState: RevenueWithdrawalState? = null,
) : TransactionPartner
