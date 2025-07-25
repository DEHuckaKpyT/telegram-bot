package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * This object contains basic information about a successful payment. Note that if the buyer
 * initiates a chargeback with the relevant payment provider following this transaction, the funds may
 * be debited from your balance. This is outside of Telegram's control.
 *
 * @see [SuccessfulPayment] (https://core.telegram.org/bots/api/#successfulpayment)
 *
 * @author KScript
 *
 * @param currency Three-letter ISO 4217
 * [currency](https://core.telegram.org/bots/payments#supported-currencies) code, or “XTR” for payments
 * in [Telegram Stars](https://t.me/BotNews/90)
 * @param totalAmount Total price in the *smallest units* of the currency (integer, **not**
 * float/double). For example, for a price of `US$ 1.45` pass `amount = 145`. See the *exp* parameter
 * in [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
 * of digits past the decimal point for each currency (2 for the majority of currencies).
 * @param invoicePayload Bot-specified invoice payload
 * @param subscriptionExpirationDate *Optional*. Expiration date of the subscription, in Unix time;
 * for recurring payments only
 * @param isRecurring *Optional*. *True*, if the payment is a recurring payment for a subscription
 * @param isFirstRecurring *Optional*. *True*, if the payment is the first payment for a
 * subscription
 * @param shippingOptionId *Optional*. Identifier of the shipping option chosen by the user
 * @param orderInfo *Optional*. Order information provided by the user
 * @param telegramPaymentChargeId Telegram payment identifier
 * @param providerPaymentChargeId Provider payment identifier
 */
public data class SuccessfulPayment(
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
     * Bot-specified invoice payload
     */
    @get:JsonProperty("invoice_payload")
    @param:JsonProperty("invoice_payload")
    public val invoicePayload: String,
    /**
     * *Optional*. Expiration date of the subscription, in Unix time; for recurring payments only
     */
    @get:JsonProperty("subscription_expiration_date")
    @param:JsonProperty("subscription_expiration_date")
    public val subscriptionExpirationDate: Long? = null,
    /**
     * *Optional*. *True*, if the payment is a recurring payment for a subscription
     */
    @get:JsonProperty("is_recurring")
    @param:JsonProperty("is_recurring")
    public val isRecurring: Boolean? = null,
    /**
     * *Optional*. *True*, if the payment is the first payment for a subscription
     */
    @get:JsonProperty("is_first_recurring")
    @param:JsonProperty("is_first_recurring")
    public val isFirstRecurring: Boolean? = null,
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
    /**
     * Telegram payment identifier
     */
    @get:JsonProperty("telegram_payment_charge_id")
    @param:JsonProperty("telegram_payment_charge_id")
    public val telegramPaymentChargeId: String,
    /**
     * Provider payment identifier
     */
    @get:JsonProperty("provider_payment_charge_id")
    @param:JsonProperty("provider_payment_charge_id")
    public val providerPaymentChargeId: String,
)
