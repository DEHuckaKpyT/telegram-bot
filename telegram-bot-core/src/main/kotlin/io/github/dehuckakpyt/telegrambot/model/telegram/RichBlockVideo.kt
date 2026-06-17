package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * A block with a video, corresponding to the HTML tag `<video>`.
 *
 * @see [RichBlockVideo] (https://core.telegram.org/bots/api/#richblockvideo)
 *
 * @author KScript
 *
 * @param type Type of the block, always “video”
 * @param video The video
 * @param hasSpoiler *Optional*. *True*, if the media preview is covered by a spoiler animation
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockVideo(
    /**
     * Type of the block, always “video”
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
    /**
     * *Optional*. *True*, if the media preview is covered by a spoiler animation
     */
    @get:JsonProperty("has_spoiler")
    @param:JsonProperty("has_spoiler")
    public val hasSpoiler: Boolean? = null,
    /**
     * *Optional*. Caption of the block
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichBlockCaption? = null,
) : RichBlock
