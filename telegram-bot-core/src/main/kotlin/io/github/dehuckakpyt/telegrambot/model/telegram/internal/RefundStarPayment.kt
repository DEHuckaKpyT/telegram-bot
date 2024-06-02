package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class RefundStarPayment(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("telegram_payment_charge_id")
    public val telegramPaymentChargeId: String,
)
