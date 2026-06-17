package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputRichMessage
import kotlin.Long

/**
 * @author KScript
 */
internal data class SendRichMessageDraft(
    @get:JsonProperty("chat_id")
    public val chatId: Long,
    @get:JsonProperty("draft_id")
    public val draftId: Long,
    @get:JsonProperty("rich_message")
    public val richMessage: InputRichMessage,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
)
