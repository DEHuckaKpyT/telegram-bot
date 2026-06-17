package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A hashtag.
 *
 * @see [RichTextHashtag] (https://core.telegram.org/bots/api/#richtexthashtag)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “hashtag”
 * @param text The text
 * @param hashtag The hashtag
 */
public data class RichTextHashtag(
    /**
     * Type of the rich text, always “hashtag”
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
     * The hashtag
     */
    @get:JsonProperty("hashtag")
    @param:JsonProperty("hashtag")
    public val hashtag: String,
) : RichText
