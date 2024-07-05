package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.collections.List

/**
 * Describes the paid media added to a message.
 *
 * @see [PaidMediaInfo] (https://core.telegram.org/bots/api/#paidmediainfo)
 *
 * @author KScript
 *
 * @param starCount The number of Telegram Stars that must be paid to buy access to the media
 * @param paidMedia Information about the paid media
 */
public data class PaidMediaInfo(
    /**
     * The number of Telegram Stars that must be paid to buy access to the media
     */
    @get:JsonProperty("star_count")
    @param:JsonProperty("star_count")
    public val starCount: Int,
    /**
     * Information about the paid media
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: List<PaidMedia>,
)
