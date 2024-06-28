package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class DeleteMessages(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_ids")
    public val messageIds: Iterable<Long>,
)
