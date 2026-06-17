package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents an HTTP link.
 *
 * @see [Link] (https://core.telegram.org/bots/api/#link)
 *
 * @author KScript
 *
 * @param url URL of the link
 */
public data class Link(
    /**
     * URL of the link
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
)
