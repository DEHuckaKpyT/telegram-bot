package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramPaymentApi
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.type.LabeledPrice
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 19.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramPaymentExApi : TelegramPaymentApi {

    suspend fun sendInvoice(
        chatId: Long,
        title: String,
        description: String,
        payload: String,
        providerToken: String,
        currency: String,
        prices: List<LabeledPrice>,
        messageThreadId: Long? = null,
        maxTipAmount: Int? = null,
        suggestedTipAmount: List<Int>? = null,
        startParameter: String? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = sendInvoice(
        chatId = chatId.toString(),
        title = title,
        description = description,
        payload = payload,
        providerToken = providerToken,
        currency = currency,
        prices = prices,
        messageThreadId = messageThreadId,
        maxTipAmount = maxTipAmount,
        suggestedTipAmount = suggestedTipAmount,
        startParameter = startParameter,
        providerData = providerData,
        photoUrl = photoUrl,
        photoSize = photoSize,
        photoWidth = photoWidth,
        photoHeight = photoHeight,
        needName = needName,
        needPhoneNumber = needPhoneNumber,
        needEmail = needEmail,
        needShippingAddress = needShippingAddress,
        sendPhoneNumberToProvider = sendPhoneNumberToProvider,
        sendEmailToProvider = sendEmailToProvider,
        isFlexible = isFlexible,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
}