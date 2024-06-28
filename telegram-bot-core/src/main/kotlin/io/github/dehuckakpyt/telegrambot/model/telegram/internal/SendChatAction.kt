package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class SendChatAction(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("action")
    public val action: String,
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
)
