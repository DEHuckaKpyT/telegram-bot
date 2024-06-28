package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class SendGame(
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("game_short_name")
    public val gameShortName: String,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
    @get:JsonProperty("message_effect_id")
    public val messageEffectId: String? = null,
    @get:JsonProperty("reply_parameters")
    public val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
