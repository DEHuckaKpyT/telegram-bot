package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long

/**
 * @author KScript
 */
internal data class SetGameScoreByChatIdAndMessageId(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("score")
    public val score: Int,
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("force")
    public val force: Boolean? = null,
    @get:JsonProperty("disable_edit_message")
    public val disableEditMessage: Boolean? = null,
)
