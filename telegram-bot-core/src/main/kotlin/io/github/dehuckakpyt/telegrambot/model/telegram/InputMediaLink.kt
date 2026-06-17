package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonIgnore
import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String

/**
 * Represents an HTTP link to be sent.
 *
 * @see [InputMediaLink] (https://core.telegram.org/bots/api/#inputmedialink)
 *
 * @author KScript
 *
 * @param url HTTP URL of the link
 */
public data class InputMediaLink(
    /**
     * HTTP URL of the link
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
) : InputPollOptionMedia {
    @get:JsonProperty("type")
    override val type: String = "link"

    @get:JsonIgnore
    override val media: Input? = null

    @get:JsonIgnore
    override val thumbnail: Input? = null

    @get:JsonIgnore
    override val photo: Input? = null

    @get:JsonIgnore
    override val cover: Input? = null
}
