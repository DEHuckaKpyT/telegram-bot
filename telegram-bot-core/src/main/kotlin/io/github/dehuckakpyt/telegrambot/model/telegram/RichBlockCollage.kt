package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * A collage, corresponding to the custom HTML tag `<tg-collage>`.
 *
 * @see [RichBlockCollage] (https://core.telegram.org/bots/api/#richblockcollage)
 *
 * @author KScript
 *
 * @param type Type of the block, always “collage”
 * @param blocks Elements of the collage
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockCollage(
    /**
     * Type of the block, always “collage”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Elements of the collage
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
