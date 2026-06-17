package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A link to a reference.
 *
 * @see [RichTextReferenceLink] (https://core.telegram.org/bots/api/#richtextreferencelink)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “reference_link”
 * @param text The link text
 * @param referenceName The name of the reference
 */
public data class RichTextReferenceLink(
    /**
     * Type of the rich text, always “reference_link”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The link text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The name of the reference
     */
    @get:JsonProperty("reference_name")
    @param:JsonProperty("reference_name")
    public val referenceName: String,
) : RichText
