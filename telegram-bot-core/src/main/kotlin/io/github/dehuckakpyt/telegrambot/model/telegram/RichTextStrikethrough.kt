package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A strikethrough text.
 *
 * @see [RichTextStrikethrough] (https://core.telegram.org/bots/api/#richtextstrikethrough)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “strikethrough”
 * @param text The text
 */
public data class RichTextStrikethrough(
    /**
     * Type of the rich text, always “strikethrough”
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
