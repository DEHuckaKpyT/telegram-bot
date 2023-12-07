package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.InlineKeyboardMarkup


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class SendGame(
    @get:JsonProperty("chat_id") val chatId: Long,
    @get:JsonProperty("game_short_name") val gameShortName: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_to_message_id") val replyToMessageId: Long? = null,
    @get:JsonProperty("allow_sending_without_reply") val allowSendingWithoutReply: Boolean? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null
)

internal class SetGameScore(
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("score") val score: Long,
    @get:JsonProperty("force") val force: Boolean? = null,
    @get:JsonProperty("disable_edit_message") val disableEditMessage: Boolean? = null,
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null
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

internal class GetGameHighScores(
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null
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