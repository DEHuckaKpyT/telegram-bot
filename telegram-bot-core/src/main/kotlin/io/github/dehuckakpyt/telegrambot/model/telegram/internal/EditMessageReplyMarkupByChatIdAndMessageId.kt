package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class EditMessageReplyMarkupByChatIdAndMessageId(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
