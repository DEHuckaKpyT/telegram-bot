package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.LinkPreviewOptions
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class EditMessageTextByChatIdAndMessageId(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("text")
    public val text: String,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("entities")
    public val entities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("link_preview_options")
    public val linkPreviewOptions: LinkPreviewOptions? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
)
