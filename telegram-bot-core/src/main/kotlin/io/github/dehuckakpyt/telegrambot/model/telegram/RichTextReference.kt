package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A reference.
 *
 * @see [RichTextReference] (https://core.telegram.org/bots/api/#richtextreference)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “reference”
 * @param text Text of the reference
 * @param name The name of the reference
 */
public data class RichTextReference(
    /**
     * Type of the rich text, always “reference”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Text of the reference
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The name of the reference
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
) : RichText
