package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SendMessageDraft(
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("draft_id")
    public val draftId: Long,
    @get:JsonProperty("text")
    public val text: String,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("entities")
    public val entities: Iterable<MessageEntity>? = null,
)
