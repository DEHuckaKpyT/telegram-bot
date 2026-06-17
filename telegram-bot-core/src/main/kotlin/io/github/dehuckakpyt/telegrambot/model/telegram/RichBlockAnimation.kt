package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * A block with an animation, corresponding to the HTML tag `<video>`.
 *
 * @see [RichBlockAnimation] (https://core.telegram.org/bots/api/#richblockanimation)
 *
 * @author KScript
 *
 * @param type Type of the block, always “animation”
 * @param animation The animation
 * @param hasSpoiler *Optional*. *True*, if the media preview is covered by a spoiler animation
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockAnimation(
    /**
     * Type of the block, always “animation”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The animation
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: Animation,
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
