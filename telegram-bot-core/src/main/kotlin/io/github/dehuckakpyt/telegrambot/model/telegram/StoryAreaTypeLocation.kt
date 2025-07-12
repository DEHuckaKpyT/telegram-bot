package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.String

/**
 * Describes a story area pointing to a location. Currently, a story can have up to 10 location
 * areas.
 *
 * @see [StoryAreaTypeLocation] (https://core.telegram.org/bots/api/#storyareatypelocation)
 *
 * @author KScript
 *
 * @param type Type of the area, always “location”
 * @param latitude Location latitude in degrees
 * @param longitude Location longitude in degrees
 * @param address *Optional*. Address of the location
 */
public data class StoryAreaTypeLocation(
    /**
     * Type of the area, always “location”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Location latitude in degrees
     */
    @get:JsonProperty("latitude")
    @param:JsonProperty("latitude")
    public val latitude: Double,
    /**
     * Location longitude in degrees
     */
    @get:JsonProperty("longitude")
    @param:JsonProperty("longitude")
    public val longitude: Double,
    /**
     * *Optional*. Address of the location
     */
    @get:JsonProperty("address")
    @param:JsonProperty("address")
    public val address: LocationAddress? = null,
) : StoryAreaType
