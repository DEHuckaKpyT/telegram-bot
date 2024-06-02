package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object contains information about an incoming pre-checkout query.
 *
 * @see [PreCheckoutQuery] (https://core.telegram.org/bots/api/#precheckoutquery)
 *
 * @author KScript
 *
 * @param id Unique query identifier
 * @param from User who sent the query
 * @param currency Three-letter ISO 4217
 * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for payments
 * in [Telegram Stars](https://t.me/BotNews/90)
 * @param totalAmount Total price in the *smallest units* of the currency (integer, **not**
 * float/double). For example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp* parameter
 * in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
 * of digits past the decimal point for each currency (2 for the majority of currencies).
 * @param invoicePayload Bot specified invoice payload
 * @param shippingOptionId *Optional*. Identifier of the shipping option chosen by the user
 * @param orderInfo *Optional*. Order information provided by the user
 */
public data class PreCheckoutQuery(
    /**
     * Unique query identifier
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: String,
    /**
     * User who sent the query
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
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
    /**
     * Bot specified invoice payload
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String,
    /**
     * *Optional*. Identifier of the shipping option chosen by the user
     */
    @get:JsonProperty("shipping_option_id")
    @param:JsonProperty("shipping_option_id")
    public val shippingOptionId: String? = null,
    /**
     * *Optional*. Order information provided by the user
     */
    @get:JsonProperty("order_info")
    @param:JsonProperty("order_info")
    public val orderInfo: OrderInfo? = null,
)
