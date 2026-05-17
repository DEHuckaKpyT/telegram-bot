package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonIgnore
import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.Double
import kotlin.String

/**
 * Represents a location to be sent.
 *
 * @see [InputMediaLocation] (https://core.telegram.org/bots/api/#inputmedialocation)
 *
 * @author KScript
 *
 * @param latitude Latitude of the location
 * @param longitude Longitude of the location
 * @param horizontalAccuracy *Optional*. The radius of uncertainty for the location, measured in
 * meters; 0-1500
 */
public data class InputMediaLocation(
    /**
     * Latitude of the location
     */
    @get:JsonProperty("latitude")
    @param:JsonProperty("latitude")
    public val latitude: Double,
    /**
     * Longitude of the location
     */
    @get:JsonProperty("longitude")
    @param:JsonProperty("longitude")
    public val longitude: Double,
    /**
     * *Optional*. The radius of uncertainty for the location, measured in meters; 0-1500
     */
    @get:JsonProperty("horizontal_accuracy")
    @param:JsonProperty("horizontal_accuracy")
    public val horizontalAccuracy: Double? = null,
) : InputPollOptionMedia {
    @get:JsonProperty("type")
    override val type: String = "location"

    @get:JsonIgnore
    override val media: Input? = null

    @get:JsonIgnore
    override val thumbnail: Input? = null

    @get:JsonIgnore
    override val photo: Input? = null

    @get:JsonIgnore
    override val cover: Input? = null
}
