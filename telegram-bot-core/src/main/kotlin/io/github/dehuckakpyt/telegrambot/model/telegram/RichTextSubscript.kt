package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A subscript text.
 *
 * @see [RichTextSubscript] (https://core.telegram.org/bots/api/#richtextsubscript)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “subscript”
 * @param text The text
 */
public data class RichTextSubscript(
    /**
     * Type of the rich text, always “subscript”
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
