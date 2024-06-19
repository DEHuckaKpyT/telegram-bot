package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.Int

/**
 * This object represents a point on the map.
 *
 * @see [Location] (https://core.telegram.org/bots/api/#location)
 *
 * @author KScript
 *
 * @param latitude Latitude as defined by sender
 * @param longitude Longitude as defined by sender
 * @param horizontalAccuracy *Optional*. The radius of uncertainty for the location, measured in
 * meters; 0-1500
 * @param livePeriod *Optional*. Time relative to the message sending date, during which the
 * location can be updated; in seconds. For active live locations only.
 * @param heading *Optional*. The direction in which user is moving, in degrees; 1-360. For active
 * live locations only.
 * @param proximityAlertRadius *Optional*. The maximum distance for proximity alerts about
 * approaching another chat member, in meters. For sent live locations only.
 */
public data class Location(
    /**
     * Latitude as defined by sender
     */
    @get:JsonProperty("latitude")
    @param:JsonProperty("latitude")
    public val latitude: Double,
    /**
     * Longitude as defined by sender
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
    /**
     * *Optional*. Time relative to the message sending date, during which the location can be
     * updated; in seconds. For active live locations only.
     */
    @get:JsonProperty("live_period")
    @param:JsonProperty("live_period")
    public val livePeriod: Int? = null,
    /**
     * *Optional*. The direction in which user is moving, in degrees; 1-360. For active live
     * locations only.
     */
    @get:JsonProperty("heading")
    @param:JsonProperty("heading")
    public val heading: Int? = null,
    /**
     * *Optional*. The maximum distance for proximity alerts about approaching another chat member,
     * in meters. For sent live locations only.
     */
    @get:JsonProperty("proximity_alert_radius")
    @param:JsonProperty("proximity_alert_radius")
    public val proximityAlertRadius: Int? = null,
)
