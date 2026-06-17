package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A cashtag.
 *
 * @see [RichTextCashtag] (https://core.telegram.org/bots/api/#richtextcashtag)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “cashtag”
 * @param text The text
 * @param cashtag The cashtag
 */
public data class RichTextCashtag(
    /**
     * Type of the rich text, always “cashtag”
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
     * The cashtag
     */
    @get:JsonProperty("cashtag")
    @param:JsonProperty("cashtag")
    public val cashtag: String,
) : RichText
