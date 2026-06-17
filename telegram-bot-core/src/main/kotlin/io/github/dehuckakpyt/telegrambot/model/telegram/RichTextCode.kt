package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A monowidth text.
 *
 * @see [RichTextCode] (https://core.telegram.org/bots/api/#richtextcode)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “code”
 * @param text The text
 */
public data class RichTextCode(
    /**
     * Type of the rich text, always “code”
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
