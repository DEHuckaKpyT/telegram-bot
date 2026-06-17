package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * An italicized text.
 *
 * @see [RichTextItalic] (https://core.telegram.org/bots/api/#richtextitalic)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “italic”
 * @param text The text
 */
public data class RichTextItalic(
    /**
     * Type of the rich text, always “italic”
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
