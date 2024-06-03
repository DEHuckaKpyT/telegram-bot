package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.Int

/**
 * Created on 03.06.2024.
 *
 * Represents the [content](https://core.telegram.org/bots/api/#inputmessagecontent) of a location
 * message to be sent as the result of an inline query.
 *
 * @see [InputLocationMessageContent]
 * (https://core.telegram.org/bots/api/#inputlocationmessagecontent)
 *
 * @author KScript
 *
 * @param latitude Latitude of the location in degrees
 * @param longitude Longitude of the location in degrees
 * @param horizontalAccuracy *Optional*. The radius of uncertainty for the location, measured in
 * meters; 0-1500
 * @param livePeriod *Optional*. Period in seconds during which the location can be updated, should
 * be between 60 and 86400, or 0x7FFFFFFF for live locations that can be edited indefinitely.
 * @param heading *Optional*. For live locations, a direction in which the user is moving, in
 * degrees. Must be between 1 and 360 if specified.
 * @param proximityAlertRadius *Optional*. For live locations, a maximum distance for proximity
 * alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
 */
public data class InputLocationMessageContent(
    /**
     * Latitude of the location in degrees
     */
    @get:JsonProperty("latitude")
    @param:JsonProperty("latitude")
    public val latitude: Double,
    /**
     * Longitude of the location in degrees
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
     * *Optional*. Period in seconds during which the location can be updated, should be between 60
     * and 86400, or 0x7FFFFFFF for live locations that can be edited indefinitely.
     */
    @get:JsonProperty("live_period")
    @param:JsonProperty("live_period")
    public val livePeriod: Int? = null,
    /**
     * *Optional*. For live locations, a direction in which the user is moving, in degrees. Must be
     * between 1 and 360 if specified.
     */
    @get:JsonProperty("heading")
    @param:JsonProperty("heading")
    public val heading: Int? = null,
    /**
     * *Optional*. For live locations, a maximum distance for proximity alerts about approaching
     * another chat member, in meters. Must be between 1 and 100000 if specified.
     */
    @get:JsonProperty("proximity_alert_radius")
    @param:JsonProperty("proximity_alert_radius")
    public val proximityAlertRadius: Int? = null,
) : InputMessageContent
