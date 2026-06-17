package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A mention of a Telegram user by their identifier.
 *
 * @see [RichTextTextMention] (https://core.telegram.org/bots/api/#richtexttextmention)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “text_mention”
 * @param text The text
 * @param user The mentioned user
 */
public data class RichTextTextMention(
    /**
     * Type of the rich text, always “text_mention”
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
     * The mentioned user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User,
) : RichText
