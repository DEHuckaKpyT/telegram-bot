package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * A block with a photo, corresponding to the HTML tag `<photo>`.
 *
 * @see [RichBlockPhoto] (https://core.telegram.org/bots/api/#richblockphoto)
 *
 * @author KScript
 *
 * @param type Type of the block, always “photo”
 * @param photo Available sizes of the photo
 * @param hasSpoiler *Optional*. *True*, if the media preview is covered by a spoiler animation
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockPhoto(
    /**
     * Type of the block, always “photo”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Available sizes of the photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>,
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
