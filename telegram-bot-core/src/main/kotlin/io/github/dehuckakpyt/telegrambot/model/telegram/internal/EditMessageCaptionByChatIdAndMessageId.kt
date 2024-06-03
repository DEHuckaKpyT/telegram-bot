package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class EditMessageCaptionByChatIdAndMessageId(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("caption")
    public val caption: String? = null,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("caption_entities")
    public val captionEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
