package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class EditForumTopic(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_thread_id")
    public val messageThreadId: Long,
    @get:JsonProperty("name")
    public val name: String? = null,
    @get:JsonProperty("icon_custom_emoji_id")
    public val iconCustomEmojiId: String? = null,
)
