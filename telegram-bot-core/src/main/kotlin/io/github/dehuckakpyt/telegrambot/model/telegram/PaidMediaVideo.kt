package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * The paid media is a video.
 *
 * @see [PaidMediaVideo] (https://core.telegram.org/bots/api/#paidmediavideo)
 *
 * @author KScript
 *
 * @param type Type of the paid media, always “video”
 * @param video The video
 */
public data class PaidMediaVideo(
    /**
     * Type of the paid media, always “video”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The video
     */
    @get:JsonProperty("video")
    @param:JsonProperty("video")
    public val video: Video,
) : PaidMedia
