package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A block with a music file, corresponding to the HTML tag `<audio>`.
 *
 * @see [RichBlockAudio] (https://core.telegram.org/bots/api/#richblockaudio)
 *
 * @author KScript
 *
 * @param type Type of the block, always “audio”
 * @param audio The audio
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockAudio(
    /**
     * Type of the block, always “audio”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The audio
     */
    @get:JsonProperty("audio")
    @param:JsonProperty("audio")
    public val audio: Audio,
    /**
     * *Optional*. Caption of the block
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichBlockCaption? = null,
) : RichBlock
