package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A superscript text.
 *
 * @see [RichTextSuperscript] (https://core.telegram.org/bots/api/#richtextsuperscript)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “superscript”
 * @param text The text
 */
public data class RichTextSuperscript(
    /**
     * Type of the rich text, always “superscript”
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
