package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * @author KScript
 */
internal data class ApproveSuggestedPost(
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("send_date")
    public val sendDate: Long? = null,
)
