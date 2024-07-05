package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * The paid media is a photo.
 *
 * @see [PaidMediaPhoto] (https://core.telegram.org/bots/api/#paidmediaphoto)
 *
 * @author KScript
 *
 * @param type Type of the paid media, always “photo”
 * @param photo The photo
 */
public data class PaidMediaPhoto(
    /**
     * Type of the paid media, always “photo”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>,
) : PaidMedia
