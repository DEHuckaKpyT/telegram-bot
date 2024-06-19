package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.LabeledPrice
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class CreateInvoiceLink(
    @get:JsonProperty("title")
    public val title: String,
    @get:JsonProperty("description")
    public val description: String,
    @get:JsonProperty("payload")
    public val payload: String,
    @get:JsonProperty("currency")
    public val currency: String,
    @get:JsonProperty("prices")
    public val prices: Iterable<LabeledPrice>,
    @get:JsonProperty("provider_token")
    public val providerToken: String? = null,
    @get:JsonProperty("max_tip_amount")
    public val maxTipAmount: Int? = null,
    @get:JsonProperty("suggested_tip_amounts")
    public val suggestedTipAmounts: Iterable<Int>? = null,
    @get:JsonProperty("provider_data")
    public val providerData: String? = null,
    @get:JsonProperty("photo_url")
    public val photoUrl: String? = null,
    @get:JsonProperty("photo_size")
    public val photoSize: Int? = null,
    @get:JsonProperty("photo_width")
    public val photoWidth: Int? = null,
    @get:JsonProperty("photo_height")
    public val photoHeight: Int? = null,
    @get:JsonProperty("need_name")
    public val needName: Boolean? = null,
    @get:JsonProperty("need_phone_number")
    public val needPhoneNumber: Boolean? = null,
    @get:JsonProperty("need_email")
    public val needEmail: Boolean? = null,
    @get:JsonProperty("need_shipping_address")
    public val needShippingAddress: Boolean? = null,
    @get:JsonProperty("send_phone_number_to_provider")
    public val sendPhoneNumberToProvider: Boolean? = null,
    @get:JsonProperty("send_email_to_provider")
    public val sendEmailToProvider: Boolean? = null,
    @get:JsonProperty("is_flexible")
    public val isFlexible: Boolean? = null,
)
