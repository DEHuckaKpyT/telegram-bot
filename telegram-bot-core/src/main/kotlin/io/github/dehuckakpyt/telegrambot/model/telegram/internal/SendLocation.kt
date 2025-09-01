package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.telegram.SuggestedPostParameters
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class SendLocation(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("latitude")
    public val latitude: Double,
    @get:JsonProperty("longitude")
    public val longitude: Double,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("direct_messages_topic_id")
    public val directMessagesTopicId: Long? = null,
    @get:JsonProperty("horizontal_accuracy")
    public val horizontalAccuracy: Double? = null,
    @get:JsonProperty("live_period")
    public val livePeriod: Int? = null,
    @get:JsonProperty("heading")
    public val heading: Int? = null,
    @get:JsonProperty("proximity_alert_radius")
    public val proximityAlertRadius: Int? = null,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
    @get:JsonProperty("allow_paid_broadcast")
    public val allowPaidBroadcast: Boolean? = null,
    @get:JsonProperty("message_effect_id")
    public val messageEffectId: String? = null,
    @get:JsonProperty("suggested_post_parameters")
    public val suggestedPostParameters: SuggestedPostParameters? = null,
    @get:JsonProperty("reply_parameters")
    public val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: ReplyMarkup? = null,
)
