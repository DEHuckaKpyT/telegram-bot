package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object represents a portion of the price for goods or services.
 *
 * @see [LabeledPrice] (https://core.telegram.org/bots/api/#labeledprice)
 *
 * @author KScript
 *
 * @param label Portion label
 * @param amount Price of the product in the *smallest units* of the
 * [currency](https://core.telegram.org/bots/payments#supported-currencies) (integer, **not**
 * float/double). For example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp* parameter
 * in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
 * of digits past the decimal point for each currency (2 for the majority of currencies).
 */
public data class LabeledPrice(
    /**
     * Portion label
     */
    @get:JsonProperty("label")
    @param:JsonProperty("label")
    public val label: String,
    /**
     * Price of the product in the *smallest units* of the
     * [currency](https://core.telegram.org/bots/payments#supported-currencies) (integer, **not**
     * float/double). For example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp*
     * parameter in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it
     * shows the number of digits past the decimal point for each currency (2 for the majority of
     * currencies).
     */
    @get:JsonProperty("amount")
    @param:JsonProperty("amount")
    public val amount: Int,
)
