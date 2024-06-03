package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * Represents the [content](https://core.telegram.org/bots/api/#inputmessagecontent) of an invoice
 * message to be sent as the result of an inline query.
 *
 * @see [InputInvoiceMessageContent]
 * (https://core.telegram.org/bots/api/#inputinvoicemessagecontent)
 *
 * @author KScript
 *
 * @param title Product name, 1-32 characters
 * @param description Product description, 1-255 characters
 * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user,
 * use for your internal processes.
 * @param providerToken *Optional*. Payment provider token, obtained via
 * [@BotFather](https://t.me/botfather). Pass an empty string for payments in [Telegram
 * Stars](https://t.me/BotNews/90).
 * @param currency Three-letter ISO 4217 currency code, see [more on
 * currencies](https://core.telegram.org/bots/payments#supported-currencies). Pass “XTR” for payments
 * in [Telegram Stars](https://t.me/BotNews/90).
 * @param prices Price breakdown, a JSON-serialized list of components (e.g. product price, tax,
 * discount, delivery cost, delivery tax, bonus, etc.). Must contain exactly one item for payments in
 * [Telegram Stars](https://t.me/BotNews/90).
 * @param maxTipAmount *Optional*. The maximum accepted amount for tips in the *smallest units* of
 * the currency (integer, **not** float/double). For example, for a maximum tip of `US$ 1.45` pass
 * `max_tip_amount = 145`. See the *exp* parameter in
 * [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number of
 * digits past the decimal point for each currency (2 for the majority of currencies). Defaults to 0.
 * Not supported for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param suggestedTipAmounts *Optional*. A JSON-serialized array of suggested amounts of tip in the
 * *smallest units* of the currency (integer, **not** float/double). At most 4 suggested tip amounts
 * can be specified. The suggested tip amounts must be positive, passed in a strictly increased order
 * and must not exceed *max_tip_amount*.
 * @param providerData *Optional*. A JSON-serialized object for data about the invoice, which will
 * be shared with the payment provider. A detailed description of the required fields should be
 * provided by the payment provider.
 * @param photoUrl *Optional*. URL of the product photo for the invoice. Can be a photo of the goods
 * or a marketing image for a service.
 * @param photoSize *Optional*. Photo size in bytes
 * @param photoWidth *Optional*. Photo width
 * @param photoHeight *Optional*. Photo height
 * @param needName *Optional*. Pass *True* if you require the user's full name to complete the
 * order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param needPhoneNumber *Optional*. Pass *True* if you require the user's phone number to complete
 * the order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param needEmail *Optional*. Pass *True* if you require the user's email address to complete the
 * order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param needShippingAddress *Optional*. Pass *True* if you require the user's shipping address to
 * complete the order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param sendPhoneNumberToProvider *Optional*. Pass *True* if the user's phone number should be
 * sent to the provider. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param sendEmailToProvider *Optional*. Pass *True* if the user's email address should be sent to
 * the provider. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 * @param isFlexible *Optional*. Pass *True* if the final price depends on the shipping method.
 * Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
 */
public data class InputInvoiceMessageContent(
    /**
     * Product name, 1-32 characters
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Product description, 1-255 characters
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String,
    /**
     * Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for
     * your internal processes.
     */
    @get:JsonProperty("payload")
    @param:JsonProperty("payload")
    public val payload: String,
    /**
     * *Optional*. Payment provider token, obtained via [@BotFather](https://t.me/botfather). Pass
     * an empty string for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("provider_token")
    @param:JsonProperty("provider_token")
    public val providerToken: String? = null,
    /**
     * Three-letter ISO 4217 currency code, see [more on
     * currencies](https://core.telegram.org/bots/payments#supported-currencies). Pass “XTR” for
     * payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("currency")
    @param:JsonProperty("currency")
    public val currency: String,
    /**
     * Price breakdown, a JSON-serialized list of components (e.g. product price, tax, discount,
     * delivery cost, delivery tax, bonus, etc.). Must contain exactly one item for payments in
     * [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("prices")
    @param:JsonProperty("prices")
    public val prices: List<LabeledPrice>,
    /**
     * *Optional*. The maximum accepted amount for tips in the *smallest units* of the currency
     * (integer, **not** float/double). For example, for a maximum tip of `US$ 1.45` pass
     * `max_tip_amount = 145`. See the *exp* parameter in
     * [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
     * of digits past the decimal point for each currency (2 for the majority of currencies). Defaults
     * to 0. Not supported for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("max_tip_amount")
    @param:JsonProperty("max_tip_amount")
    public val maxTipAmount: Int? = null,
    /**
     * *Optional*. A JSON-serialized array of suggested amounts of tip in the *smallest units* of
     * the currency (integer, **not** float/double). At most 4 suggested tip amounts can be specified.
     * The suggested tip amounts must be positive, passed in a strictly increased order and must not
     * exceed *max_tip_amount*.
     */
    @get:JsonProperty("suggested_tip_amounts")
    @param:JsonProperty("suggested_tip_amounts")
    public val suggestedTipAmounts: List<Int>? = null,
    /**
     * *Optional*. A JSON-serialized object for data about the invoice, which will be shared with
     * the payment provider. A detailed description of the required fields should be provided by the
     * payment provider.
     */
    @get:JsonProperty("provider_data")
    @param:JsonProperty("provider_data")
    public val providerData: String? = null,
    /**
     * *Optional*. URL of the product photo for the invoice. Can be a photo of the goods or a
     * marketing image for a service.
     */
    @get:JsonProperty("photo_url")
    @param:JsonProperty("photo_url")
    public val photoUrl: String? = null,
    /**
     * *Optional*. Photo size in bytes
     */
    @get:JsonProperty("photo_size")
    @param:JsonProperty("photo_size")
    public val photoSize: Int? = null,
    /**
     * *Optional*. Photo width
     */
    @get:JsonProperty("photo_width")
    @param:JsonProperty("photo_width")
    public val photoWidth: Int? = null,
    /**
     * *Optional*. Photo height
     */
    @get:JsonProperty("photo_height")
    @param:JsonProperty("photo_height")
    public val photoHeight: Int? = null,
    /**
     * *Optional*. Pass *True* if you require the user's full name to complete the order. Ignored
     * for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("need_name")
    @param:JsonProperty("need_name")
    public val needName: Boolean? = null,
    /**
     * *Optional*. Pass *True* if you require the user's phone number to complete the order. Ignored
     * for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("need_phone_number")
    @param:JsonProperty("need_phone_number")
    public val needPhoneNumber: Boolean? = null,
    /**
     * *Optional*. Pass *True* if you require the user's email address to complete the order.
     * Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("need_email")
    @param:JsonProperty("need_email")
    public val needEmail: Boolean? = null,
    /**
     * *Optional*. Pass *True* if you require the user's shipping address to complete the order.
     * Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("need_shipping_address")
    @param:JsonProperty("need_shipping_address")
    public val needShippingAddress: Boolean? = null,
    /**
     * *Optional*. Pass *True* if the user's phone number should be sent to the provider. Ignored
     * for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("send_phone_number_to_provider")
    @param:JsonProperty("send_phone_number_to_provider")
    public val sendPhoneNumberToProvider: Boolean? = null,
    /**
     * *Optional*. Pass *True* if the user's email address should be sent to the provider. Ignored
     * for payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("send_email_to_provider")
    @param:JsonProperty("send_email_to_provider")
    public val sendEmailToProvider: Boolean? = null,
    /**
     * *Optional*. Pass *True* if the final price depends on the shipping method. Ignored for
     * payments in [Telegram Stars](https://t.me/BotNews/90).
     */
    @get:JsonProperty("is_flexible")
    @param:JsonProperty("is_flexible")
    public val isFlexible: Boolean? = null,
) : InputMessageContent
