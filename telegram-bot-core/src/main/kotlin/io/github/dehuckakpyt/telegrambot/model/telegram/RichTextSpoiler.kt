package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text covered by a spoiler.
 *
 * @see [RichTextSpoiler] (https://core.telegram.org/bots/api/#richtextspoiler)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “spoiler”
 * @param text The text
 */
public data class RichTextSpoiler(
    /**
     * Type of the rich text, always “spoiler”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
) : RichText
