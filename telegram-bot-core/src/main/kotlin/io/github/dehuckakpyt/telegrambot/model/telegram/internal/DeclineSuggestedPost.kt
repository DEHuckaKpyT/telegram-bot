package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class DeclineSuggestedPost(
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("comment")
    public val comment: String? = null,
)
