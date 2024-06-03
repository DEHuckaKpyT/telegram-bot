package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.Int
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents a location on a map. By default, the location will be sent by the user. Alternatively,
 * you can use *input_message_content* to send a message with the specified content instead of the
 * location.
 *
 * @see [InlineQueryResultLocation] (https://core.telegram.org/bots/api/#inlinequeryresultlocation)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param latitude Location latitude in degrees
 * @param longitude Location longitude in degrees
 * @param title Location title
 * @param horizontalAccuracy *Optional*. The radius of uncertainty for the location, measured in
 * meters; 0-1500
 * @param livePeriod *Optional*. Period in seconds during which the location can be updated, should
 * be between 60 and 86400, or 0x7FFFFFFF for live locations that can be edited indefinitely.
 * @param heading *Optional*. For live locations, a direction in which the user is moving, in
 * degrees. Must be between 1 and 360 if specified.
 * @param proximityAlertRadius *Optional*. For live locations, a maximum distance for proximity
 * alerts about approaching another chat member, in meters. Must be between 1 and 100000 if specified.
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the location
 * @param thumbnailUrl *Optional*. Url of the thumbnail for the result
 * @param thumbnailWidth *Optional*. Thumbnail width
 * @param thumbnailHeight *Optional*. Thumbnail height
 */
public data class InlineQueryResultLocation(
    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
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
     * Location title
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
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
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the location
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
    /**
     * *Optional*. Url of the thumbnail for the result
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String? = null,
    /**
     * *Optional*. Thumbnail width
     */
    @get:JsonProperty("thumbnail_width")
    @param:JsonProperty("thumbnail_width")
    public val thumbnailWidth: Int? = null,
    /**
     * *Optional*. Thumbnail height
     */
    @get:JsonProperty("thumbnail_height")
    @param:JsonProperty("thumbnail_height")
    public val thumbnailHeight: Int? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "location"
}
