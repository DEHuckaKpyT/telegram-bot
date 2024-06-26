package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SetStickerEmojiList(
    @get:JsonProperty("sticker")
    public val sticker: String,
    @get:JsonProperty("emoji_list")
    public val emojiList: Iterable<String>,
)
