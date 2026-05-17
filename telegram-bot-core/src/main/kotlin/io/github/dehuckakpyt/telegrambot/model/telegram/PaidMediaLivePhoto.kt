package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * The paid media is a [live photo](https://core.telegram.org/bots/api/#livephoto).
 *
 * @see [PaidMediaLivePhoto] (https://core.telegram.org/bots/api/#paidmedialivephoto)
 *
 * @author KScript
 *
 * @param type Type of the paid media, always “live_photo”
 * @param livePhoto The photo
 */
public data class PaidMediaLivePhoto(
    /**
     * Type of the paid media, always “live_photo”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The photo
     */
    @get:JsonProperty("live_photo")
    @param:JsonProperty("live_photo")
    public val livePhoto: LivePhoto,
) : PaidMedia
