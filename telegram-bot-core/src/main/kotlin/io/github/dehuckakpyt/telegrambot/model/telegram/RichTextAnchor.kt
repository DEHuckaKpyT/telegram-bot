package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * An anchor.
 *
 * @see [RichTextAnchor] (https://core.telegram.org/bots/api/#richtextanchor)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “anchor”
 * @param name The name of the anchor
 */
public data class RichTextAnchor(
    /**
     * Type of the rich text, always “anchor”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The name of the anchor
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
) : RichText
