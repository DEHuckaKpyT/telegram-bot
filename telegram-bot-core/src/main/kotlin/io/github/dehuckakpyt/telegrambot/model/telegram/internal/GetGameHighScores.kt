package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class GetGameHighScores(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("chat_id")
    public val chatId: Long? = null,
    @get:JsonProperty("message_id")
    public val messageId: Long? = null,
    @get:JsonProperty("inline_message_id")
    public val inlineMessageId: String? = null,
)
