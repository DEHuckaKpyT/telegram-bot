package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a transaction with an unknown source or recipient.
 *
 * @see [TransactionPartnerOther] (https://core.telegram.org/bots/api/#transactionpartnerother)
 *
 * @author KScript
 *
 * @param type Type of the transaction partner, always “other”
 */
public data class TransactionPartnerOther(
    /**
     * Type of the transaction partner, always “other”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : TransactionPartner
