package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A block with an anchor, corresponding to the HTML tag `<a>` with the attribute `name`.
 *
 * @see [RichBlockAnchor] (https://core.telegram.org/bots/api/#richblockanchor)
 *
 * @author KScript
 *
 * @param type Type of the block, always “anchor”
 * @param name The name of the anchor
 */
public data class RichBlockAnchor(
    /**
     * Type of the block, always “anchor”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The name of the anchor
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
) : RichBlock
