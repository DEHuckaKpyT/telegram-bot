package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * @author KScript
 */
internal data class CreateForumTopic(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("name")
    public val name: String,
    @get:JsonProperty("icon_color")
    public val iconColor: Int? = null,
    @get:JsonProperty("icon_custom_emoji_id")
    public val iconCustomEmojiId: String? = null,
)
