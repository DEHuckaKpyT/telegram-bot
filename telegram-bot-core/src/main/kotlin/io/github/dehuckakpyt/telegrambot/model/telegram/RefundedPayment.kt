package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object contains basic information about a refunded payment.
 *
 * @see [RefundedPayment] (https://core.telegram.org/bots/api/#refundedpayment)
 *
 * @author KScript
 *
 * @param currency Three-letter ISO 4217
 * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for payments
 * in [Telegram Stars](https://t.me/BotNews/90). Currently, always “XTR”
 * @param totalAmount Total refunded price in the *smallest units* of the currency (integer, **not**
 * float/double). For example, for a price of `US$ 1.45`, `total_amount = 145`. See the *exp* parameter
 * in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
 * of digits past the decimal point for each currency (2 for the majority of currencies).
 * @param invoicePayload Bot-specified invoice payload
 * @param telegramPaymentChargeId Telegram payment identifier
 * @param providerPaymentChargeId *Optional*. Provider payment identifier
 */
public data class RefundedPayment(
    /**
     * Three-letter ISO 4217
     * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for
     * payments in [Telegram Stars](https://t.me/BotNews/90). Currently, always “XTR”
     */
    @get:JsonProperty("currency")
    @param:JsonProperty("currency")
    public val currency: String,
    /**
     * Total refunded price in the *smallest units* of the currency (integer, **not** float/double).
     * For example, for a price of `US$ 1.45`, `total_amount = 145`. See the *exp* parameter in
     * [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
     * of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @get:JsonProperty("total_amount")
    @param:JsonProperty("total_amount")
    public val totalAmount: Int,
    /**
     * Bot-specified invoice payload
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String,
    /**
     * Telegram payment identifier
     */
    @get:JsonProperty("telegram_payment_charge_id")
    @param:JsonProperty("telegram_payment_charge_id")
    public val telegramPaymentChargeId: String,
    /**
     * *Optional*. Provider payment identifier
     */
    @get:JsonProperty("provider_payment_charge_id")
    @param:JsonProperty("provider_payment_charge_id")
    public val providerPaymentChargeId: String? = null,
)
