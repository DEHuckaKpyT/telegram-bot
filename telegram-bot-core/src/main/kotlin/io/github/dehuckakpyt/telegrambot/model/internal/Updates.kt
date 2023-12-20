package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.type.InputMedia
import io.github.dehuckakpyt.telegrambot.model.type.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.type.ParseMode


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class UpdateRequest(
    @get:JsonProperty("offset") val offset: Int? = null,
    @get:JsonProperty("limit") val limit: Int? = null,
    @get:JsonProperty("timeout") val timeout: Int? = null,
    @get:JsonProperty("allowed_updates") val allowedUpdates: List<AllowedUpdate>? = null,
)

internal class EditMessageText(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("text") val text: String,
    @get:JsonProperty("parse_mode") val parseMode: ParseMode? = null,
    @get:JsonProperty("entities") val entities: List<MessageEntity>? = null,
    @get:JsonProperty("disable_web_page_preview") val disableWebPagePreview: Boolean? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatId and messageId are required if inlineMessageId not provided")
        }
    }
}

internal class EditMessageCaption(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: ParseMode? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatId and messageId are required if inlineMessageId not provided")
        }
    }
}

internal class EditMessageMedia(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("media") val inputMedia: InputMedia,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatId and messageId are required if inlineMessageId not provided")
        }
    }
}

internal class EditMessageReplyMarkup(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatId and messageId are required if inlineMessageId not provided")
        }
    }
}

internal class StopPoll(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
)

internal class DeleteMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
)

public enum class AllowedUpdate {
    @field:JsonProperty("message")
    Message,

    @field:JsonProperty("edited_message")
    EditedMessage,

    @field:JsonProperty("channel_post")
    ChannelPost,

    @field:JsonProperty("edited_channel_post")
    EditedChannelPost,

    @field:JsonProperty("inline_query")
    InlineQuery,

    @field:JsonProperty("chosen_inline_result")
    ChosenInlineQuery,

    @field:JsonProperty("callback_query")
    CallbackQuery,

    @field:JsonProperty("shipping_query")
    ShippingQuery,

    @field:JsonProperty("pre_checkout_query")
    PreCheckoutQuery,

    @field:JsonProperty("poll")
    Poll,

    @field:JsonProperty("poll_answer")
    PollAnswer,

    @field:JsonProperty("my_chat_member")
    MyChatMember,

    @field:JsonProperty("chat_member")
    ChatMember,

    @field:JsonProperty("chat_join_request")
    ChatJoinRequest,
}