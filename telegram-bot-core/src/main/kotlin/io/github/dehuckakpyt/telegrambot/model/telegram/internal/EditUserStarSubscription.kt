package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class EditUserStarSubscription(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("telegram_payment_charge_id")
    public val telegramPaymentChargeId: String,
    @get:JsonProperty("is_canceled")
    public val isCanceled: Boolean,
)
