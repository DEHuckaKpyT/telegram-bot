package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class CopyMessage(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("from_chat_id")
    public val fromChatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("caption")
    public val caption: String? = null,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("caption_entities")
    public val captionEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters")
    public val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup")
    public val replyMarkup: ReplyMarkup? = null,
)
