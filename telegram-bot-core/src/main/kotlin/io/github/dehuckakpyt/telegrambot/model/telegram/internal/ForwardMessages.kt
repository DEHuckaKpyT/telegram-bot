package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class ForwardMessages(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("from_chat_id")
    public val fromChatId: String,
    @get:JsonProperty("message_ids")
    public val messageIds: Iterable<Long>,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
)
