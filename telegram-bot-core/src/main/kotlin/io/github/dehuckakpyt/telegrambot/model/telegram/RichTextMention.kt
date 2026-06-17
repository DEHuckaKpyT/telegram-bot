package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A mention by a username.
 *
 * @see [RichTextMention] (https://core.telegram.org/bots/api/#richtextmention)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “mention”
 * @param text The text
 * @param username The username
 */
public data class RichTextMention(
    /**
     * Type of the rich text, always “mention”
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
     * The username
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String,
) : RichText
