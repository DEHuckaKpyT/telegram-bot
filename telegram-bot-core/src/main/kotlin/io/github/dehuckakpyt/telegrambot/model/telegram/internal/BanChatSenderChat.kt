package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class BanChatSenderChat(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("sender_chat_id")
    public val senderChatId: Long,
)
