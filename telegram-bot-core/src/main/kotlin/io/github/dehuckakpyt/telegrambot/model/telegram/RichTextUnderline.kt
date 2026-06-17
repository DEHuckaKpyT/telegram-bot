package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * An underlined text.
 *
 * @see [RichTextUnderline] (https://core.telegram.org/bots/api/#richtextunderline)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “underline”
 * @param text The text
 */
public data class RichTextUnderline(
    /**
     * Type of the rich text, always “underline”
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
