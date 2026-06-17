package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * A block with a map, corresponding to the custom HTML tag `<tg-map>`.
 *
 * @see [RichBlockMap] (https://core.telegram.org/bots/api/#richblockmap)
 *
 * @author KScript
 *
 * @param type Type of the block, always “map”
 * @param location Location of the center of the map
 * @param zoom Map zoom level; 13-20
 * @param width Expected width of the map
 * @param height Expected height of the map
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockMap(
    /**
     * Type of the block, always “map”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Location of the center of the map
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location,
    /**
     * Map zoom level; 13-20
     */
    @get:JsonProperty("zoom")
    @param:JsonProperty("zoom")
    public val zoom: Int,
    /**
     * Expected width of the map
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int,
    /**
     * Expected height of the map
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int,
    /**
     * *Optional*. Caption of the block
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichBlockCaption? = null,
) : RichBlock
