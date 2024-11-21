package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputPollOption
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SendPoll(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("question")
    public val question: String,
    @get:JsonProperty("options")
    public val options: Iterable<InputPollOption>,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("question_parse_mode")
    public val questionParseMode: String? = null,
    @get:JsonProperty("question_entities")
    public val questionEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("is_anonymous")
    public val isAnonymous: Boolean? = null,
    @get:JsonProperty("type")
    public val type: String? = null,
    @get:JsonProperty("allows_multiple_answers")
    public val allowsMultipleAnswers: Boolean? = null,
    @get:JsonProperty("correct_option_id")
    public val correctOptionId: Long? = null,
    @get:JsonProperty("explanation")
    public val explanation: String? = null,
    @get:JsonProperty("explanation_parse_mode")
    public val explanationParseMode: String? = null,
    @get:JsonProperty("explanation_entities")
    public val explanationEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("open_period")
    public val openPeriod: Int? = null,
    @get:JsonProperty("close_date")
    public val closeDate: Long? = null,
    @get:JsonProperty("is_closed")
    public val isClosed: Boolean? = null,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
    @get:JsonProperty("allow_paid_broadcast")
    public val allowPaidBroadcast: Boolean? = null,
    @get:JsonProperty("message_effect_id")
    public val messageEffectId: String? = null,
    @get:JsonProperty("reply_parameters")
    public val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: ReplyMarkup? = null,
)
