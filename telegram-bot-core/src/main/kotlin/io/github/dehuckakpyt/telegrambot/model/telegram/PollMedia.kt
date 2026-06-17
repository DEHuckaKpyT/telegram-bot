package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * At most **one** of the optional fields can be present in any given object.
 *
 * @see [PollMedia] (https://core.telegram.org/bots/api/#pollmedia)
 *
 * @author KScript
 *
 * @param animation *Optional*. Media is an animation, information about the animation
 * @param audio *Optional*. Media is an audio file, information about the file; currently, can't be
 * received in a poll option
 * @param document *Optional*. Media is a general file, information about the file; currently, can't
 * be received in a poll option
 * @param link *Optional*. The HTTP link attached to the poll option
 * @param livePhoto *Optional*. Media is a live photo, information about the live photo
 * @param location *Optional*. Media is a shared location, information about the location
 * @param photo *Optional*. Media is a photo, available sizes of the photo
 * @param sticker *Optional*. Media is a sticker, information about the sticker; currently, for poll
 * options only
 * @param venue *Optional*. Media is a venue, information about the venue
 * @param video *Optional*. Media is a video, information about the video
 */
public data class PollMedia(
    /**
     * *Optional*. Media is an animation, information about the animation
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: Animation? = null,
    /**
     * *Optional*. Media is an audio file, information about the file; currently, can't be received
     * in a poll option
     */
    @get:JsonProperty("audio")
    @param:JsonProperty("audio")
    public val audio: Audio? = null,
    /**
     * *Optional*. Media is a general file, information about the file; currently, can't be received
     * in a poll option
     */
    @get:JsonProperty("document")
    @param:JsonProperty("document")
    public val document: Document? = null,
    /**
     * *Optional*. The HTTP link attached to the poll option
     */
    @get:JsonProperty("link")
    @param:JsonProperty("link")
    public val link: Link? = null,
    /**
     * *Optional*. Media is a live photo, information about the live photo
     */
    @get:JsonProperty("live_photo")
    @param:JsonProperty("live_photo")
    public val livePhoto: LivePhoto? = null,
    /**
     * *Optional*. Media is a shared location, information about the location
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location? = null,
    /**
     * *Optional*. Media is a photo, available sizes of the photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>? = null,
    /**
     * *Optional*. Media is a sticker, information about the sticker; currently, for poll options
     * only
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker? = null,
    /**
     * *Optional*. Media is a venue, information about the venue
     */
    @get:JsonProperty("venue")
    @param:JsonProperty("venue")
    public val venue: Venue? = null,
    /**
     * *Optional*. Media is a video, information about the video
     */
    @get:JsonProperty("video")
    @param:JsonProperty("video")
    public val video: Video? = null,
)
