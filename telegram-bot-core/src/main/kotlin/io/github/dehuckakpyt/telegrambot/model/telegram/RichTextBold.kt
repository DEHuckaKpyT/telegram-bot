package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A bold text.
 *
 * @see [RichTextBold] (https://core.telegram.org/bots/api/#richtextbold)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “bold”
 * @param text The text
 */
public data class RichTextBold(
    /**
     * Type of the rich text, always “bold”
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
