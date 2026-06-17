package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A marked text.
 *
 * @see [RichTextMarked] (https://core.telegram.org/bots/api/#richtextmarked)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “marked”
 * @param text The text
 */
public data class RichTextMarked(
    /**
     * Type of the rich text, always “marked”
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
