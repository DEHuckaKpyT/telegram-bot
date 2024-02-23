package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.type.LabeledPrice
import io.github.dehuckakpyt.telegrambot.model.type.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.type.ShippingOption


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class SendInvoice(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("description") val description: String,
    @get:JsonProperty("payload") val payload: String,
    @get:JsonProperty("provider_token") val providerToken: String,
    @get:JsonProperty("currency") val currency: String,
    @get:JsonProperty("prices") val prices: List<LabeledPrice>,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("max_tip_amount") val maxTipAmount: Int? = null,
    @get:JsonProperty("suggested_tip_amounts") val suggestedTipAmount: List<Int>? = null,
    @get:JsonProperty("start_parameter") val startParameter: String? = null,
    @get:JsonProperty("provider_data") val providerData: String? = null,
    @get:JsonProperty("photo_url") val photoUrl: String? = null,
    @get:JsonProperty("photo_size") val photoSize: Int? = null,
    @get:JsonProperty("photo_width") val photoWidth: Int? = null,
    @get:JsonProperty("photo_height") val photoHeight: Int? = null,
    @get:JsonProperty("need_name") val needName: Boolean? = null,
    @get:JsonProperty("need_phone_number") val needPhoneNumber: Boolean? = null,
    @get:JsonProperty("need_email") val needEmail: Boolean? = null,
    @get:JsonProperty("need_shipping_address") val needShippingAddress: Boolean? = null,
    @get:JsonProperty("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean? = null,
    @get:JsonProperty("send_email_to_provider") val sendEmailToProvider: Boolean? = null,
    @get:JsonProperty("is_flexible") val isFlexible: Boolean? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
)

internal class CreateInvoiceLink(
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("description") val description: String,
    @get:JsonProperty("payload") val payload: String,
    @get:JsonProperty("provider_token") val providerToken: String,
    @get:JsonProperty("currency") val currency: String,
    @get:JsonProperty("prices") val prices: List<LabeledPrice>,
    @get:JsonProperty("max_tip_amount") val maxTipAmount: Int? = null,
    @get:JsonProperty("suggested_tip_amounts") val suggestedTipAmount: List<Int>? = null,
    @get:JsonProperty("provider_data") val providerData: String? = null,
    @get:JsonProperty("photo_url") val photoUrl: String? = null,
    @get:JsonProperty("photo_size") val photoSize: Int? = null,
    @get:JsonProperty("photo_width") val photoWidth: Int? = null,
    @get:JsonProperty("photo_height") val photoHeight: Int? = null,
    @get:JsonProperty("need_name") val needName: Boolean? = null,
    @get:JsonProperty("need_phone_number") val needPhoneNumber: Boolean? = null,
    @get:JsonProperty("need_email") val needEmail: Boolean? = null,
    @get:JsonProperty("need_shipping_address") val needShippingAddress: Boolean? = null,
    @get:JsonProperty("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean? = null,
    @get:JsonProperty("send_email_to_provider") val sendEmailToProvider: Boolean? = null,
    @get:JsonProperty("is_flexible") val isFlexible: Boolean? = null,
)

internal class AnswerShippingQuery(
    @get:JsonProperty("shipping_query_id") val shippingQueryId: String,
    @get:JsonProperty("ok") val ok: Boolean,
    @get:JsonProperty("shipping_options") val shippingOptions: List<ShippingOption>? = null,
    @get:JsonProperty("error_message") val errorMessage: String? = null,
)

internal class AnswerPreCheckoutQuery(
    @get:JsonProperty("pre_checkout_query_id") val preCheckoutQueryId: String,
    @get:JsonProperty("ok") val ok: Boolean,
    @get:JsonProperty("error_message") val errorMessage: String? = null,
)