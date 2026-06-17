package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text with a link.
 *
 * @see [RichTextUrl] (https://core.telegram.org/bots/api/#richtexturl)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “url”
 * @param text The text
 * @param url URL of the link
 */
public data class RichTextUrl(
    /**
     * Type of the rich text, always “url”
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
    /**
     * URL of the link
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
) : RichText
