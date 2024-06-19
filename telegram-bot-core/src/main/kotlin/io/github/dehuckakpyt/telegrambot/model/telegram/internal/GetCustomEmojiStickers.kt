package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class GetCustomEmojiStickers(
    @get:JsonProperty("custom_emoji_ids")
    public val customEmojiIds: Iterable<String>,
)
