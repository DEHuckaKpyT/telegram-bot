package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import kotlin.Double
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class EditMessageLiveLocationByInlineMessageId(
    @get:JsonProperty("inline_message_id")
    public val inlineMessageId: String,
    @get:JsonProperty("latitude")
    public val latitude: Double,
    @get:JsonProperty("longitude")
    public val longitude: Double,
    @get:JsonProperty("live_period")
    public val livePeriod: Int? = null,
    @get:JsonProperty("horizontal_accuracy")
    public val horizontalAccuracy: Double? = null,
    @get:JsonProperty("heading")
    public val heading: Int? = null,
    @get:JsonProperty("proximity_alert_radius")
    public val proximityAlertRadius: Int? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
