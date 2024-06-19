package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Boolean
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class EditMessageCaptionByInlineMessageId(
    @get:JsonProperty("inline_message_id")
    public val inlineMessageId: String,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
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
