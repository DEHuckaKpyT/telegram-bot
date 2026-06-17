package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * A slideshow, corresponding to the custom HTML tag `<tg-slideshow>`.
 *
 * @see [RichBlockSlideshow] (https://core.telegram.org/bots/api/#richblockslideshow)
 *
 * @author KScript
 *
 * @param type Type of the block, always “slideshow”
 * @param blocks Elements of the slideshow
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockSlideshow(
    /**
     * Type of the block, always “slideshow”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Elements of the slideshow
     */
    @get:JsonProperty("blocks")
    @param:JsonProperty("blocks")
    public val blocks: List<RichBlock>,
    /**
     * *Optional*. Caption of the block
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichBlockCaption? = null,
) : RichBlock
