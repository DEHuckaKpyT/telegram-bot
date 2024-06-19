package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object contains basic information about an invoice.
 *
 * @see [Invoice] (https://core.telegram.org/bots/api/#invoice)
 *
 * @author KScript
 *
 * @param title Product name
 * @param description Product description
 * @param startParameter Unique bot deep-linking parameter that can be used to generate this invoice
 * @param currency Three-letter ISO 4217
 * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for payments
 * in [Telegram Stars](https://t.me/BotNews/90)
 * @param totalAmount Total price in the *smallest units* of the currency (integer, **not**
 * float/double). For example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp* parameter
 * in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
 * of digits past the decimal point for each currency (2 for the majority of currencies).
 */
public data class Invoice(
    /**
     * Product name
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Product description
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String,
    /**
     * Unique bot deep-linking parameter that can be used to generate this invoice
     */
    @get:JsonProperty("start_parameter")
    @param:JsonProperty("start_parameter")
    public val startParameter: String,
    /**
     * Three-letter ISO 4217
     * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for
     * payments in [Telegram Stars](https://t.me/BotNews/90)
     */
    @get:JsonProperty("currency")
    @param:JsonProperty("currency")
    public val currency: String,
    /**
     * Total price in the *smallest units* of the currency (integer, **not** float/double). For
     * example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp* parameter in
     * [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
     * of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @get:JsonProperty("total_amount")
    @param:JsonProperty("total_amount")
    public val totalAmount: Int,
)
