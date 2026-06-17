package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A custom emoji.
 *
 * @see [RichTextCustomEmoji] (https://core.telegram.org/bots/api/#richtextcustomemoji)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “custom_emoji”
 * @param customEmojiId Unique identifier of the custom emoji. Use
 * [getCustomEmojiStickers](https://core.telegram.org/bots/api/#getcustomemojistickers) to get full
 * information about the sticker.
 * @param alternativeText Alternative emoji for the custom emoji
 */
public data class RichTextCustomEmoji(
    /**
     * Type of the rich text, always “custom_emoji”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Unique identifier of the custom emoji. Use
     * [getCustomEmojiStickers](https://core.telegram.org/bots/api/#getcustomemojistickers) to get full
     * information about the sticker.
     */
    @get:JsonProperty("custom_emoji_id")
    @param:JsonProperty("custom_emoji_id")
    public val customEmojiId: String,
    /**
     * Alternative emoji for the custom emoji
     */
    @get:JsonProperty("alternative_text")
    @param:JsonProperty("alternative_text")
    public val alternativeText: String,
) : RichText
