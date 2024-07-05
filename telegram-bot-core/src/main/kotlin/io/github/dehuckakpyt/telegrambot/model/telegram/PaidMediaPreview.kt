package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * The paid media isn't available before the payment.
 *
 * @see [PaidMediaPreview] (https://core.telegram.org/bots/api/#paidmediapreview)
 *
 * @author KScript
 *
 * @param type Type of the paid media, always “preview”
 * @param width *Optional*. Media width as defined by the sender
 * @param height *Optional*. Media height as defined by the sender
 * @param duration *Optional*. Duration of the media in seconds as defined by the sender
 */
public data class PaidMediaPreview(
    /**
     * Type of the paid media, always “preview”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * *Optional*. Media width as defined by the sender
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int? = null,
    /**
     * *Optional*. Media height as defined by the sender
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int? = null,
    /**
     * *Optional*. Duration of the media in seconds as defined by the sender
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int? = null,
) : PaidMedia
