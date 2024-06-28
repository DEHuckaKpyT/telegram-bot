package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * Contains a list of Telegram Star transactions.
 *
 * @see [StarTransactions] (https://core.telegram.org/bots/api/#startransactions)
 *
 * @author KScript
 *
 * @param transactions The list of transactions
 */
public data class StarTransactions(
    /**
     * The list of transactions
     */
    @get:JsonProperty("transactions")
    @param:JsonProperty("transactions")
    public val transactions: List<StarTransaction>,
)
