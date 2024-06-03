package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class EditMessageReplyMarkupByInlineMessageId(
    @get:JsonProperty("inline_message_id")
    public val inlineMessageId: String,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
