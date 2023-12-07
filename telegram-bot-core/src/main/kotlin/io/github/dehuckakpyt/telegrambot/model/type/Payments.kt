package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class LabeledPrice(
    @get:JsonProperty("label") @param:JsonProperty("label") val label: String,
    @get:JsonProperty("amount") @param:JsonProperty("amount") val amount: Int,
)

public data class Invoice(
    @get:JsonProperty("title") @param:JsonProperty("title") val title: String,
    @get:JsonProperty("description") @param:JsonProperty("description") val description: String,
    @get:JsonProperty("start_parameter") @param:JsonProperty("start_parameter") val startParameter: String,
    @get:JsonProperty("currency") @param:JsonProperty("currency") val currency: String,
    @get:JsonProperty("total_amount") @param:JsonProperty("total_amount") val totalAmount: Int,
)

public data class ShippingAddress(
    @get:JsonProperty("country_code") @param:JsonProperty("country_code") val countryCode: String,
    @get:JsonProperty("state") @param:JsonProperty("state") val state: String,
    @get:JsonProperty("city") @param:JsonProperty("city") val city: String,
    @get:JsonProperty("street_line1") @param:JsonProperty("street_line1") val streetLine1: String,
    @get:JsonProperty("street_line2") @param:JsonProperty("street_line2") val streetLine2: String,
    @get:JsonProperty("post_code") @param:JsonProperty("post_code") val postCode: String,
)

public data class OrderInfo(
    @get:JsonProperty("name") @param:JsonProperty("name") val name: String,
    @get:JsonProperty("phone_number") @param:JsonProperty("phone_number") val phoneNumber: String,
    @get:JsonProperty("email") @param:JsonProperty("email") val email: String,
    @get:JsonProperty("shipping_address") @param:JsonProperty("shipping_address") val shippingAddress: ShippingAddress,
)

public data class ShippingOption(
    @get:JsonProperty("id") @param:JsonProperty("id") val id: String,
    @get:JsonProperty("title") @param:JsonProperty("title") val title: String,
    @get:JsonProperty("prices") @param:JsonProperty("prices") val prices: List<LabeledPrice>,
)

public data class SuccessfulPayment(
    @get:JsonProperty("currency") @param:JsonProperty("currency") val currency: String,
    @get:JsonProperty("total_amount") @param:JsonProperty("total_amount") val totalAmount: Int,
    @get:JsonProperty("invoice_payload") @param:JsonProperty("invoice_payload") val invoicePayload: String,
    @get:JsonProperty("shipping_option_id") @param:JsonProperty("shipping_option_id") val shippingOptionId: String,
    @get:JsonProperty("order_info") @param:JsonProperty("order_info") val orderInfo: OrderInfo,
    @get:JsonProperty("telegram_payment_charge_id") @param:JsonProperty("telegram_payment_charge_id") val telegramPaymentChargeId: String,
    @get:JsonProperty("provider_payment_charge_id") @param:JsonProperty("provider_payment_charge_id") val providerPaymentChargeId: String,
)

public data class ShippingQuery(
    @get:JsonProperty("id") @param:JsonProperty("id") val id: String,
    @get:JsonProperty("from") @param:JsonProperty("from") val from: User,
    @get:JsonProperty("invoice_payload") @param:JsonProperty("invoice_payload") val invoicePayload: String,
    @get:JsonProperty("shipping_address") @param:JsonProperty("shipping_address") val shippingAddress: ShippingAddress,
)

public data class PreCheckoutQuery(
    @get:JsonProperty("id") @param:JsonProperty("id") val id: String,
    @get:JsonProperty("from") @param:JsonProperty("from") val from: User,
    @get:JsonProperty("currency") @param:JsonProperty("currency") val currency: String,
    @get:JsonProperty("total_amount") @param:JsonProperty("total_amount") val totalAmount: Int,
    @get:JsonProperty("invoice_payload") @param:JsonProperty("invoice_payload") val invoicePayload: String,
    @get:JsonProperty("shipping_option_id") @param:JsonProperty("shipping_option_id") val shippingOptionId: String,
    @get:JsonProperty("order_info") @param:JsonProperty("order_info") val orderInfo: OrderInfo,
)