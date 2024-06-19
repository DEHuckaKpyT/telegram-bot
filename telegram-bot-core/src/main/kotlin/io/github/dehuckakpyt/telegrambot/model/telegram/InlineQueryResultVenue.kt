package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.Int
import kotlin.String

/**
 * Represents a venue. By default, the venue will be sent by the user. Alternatively, you can use
 * *input_message_content* to send a message with the specified content instead of the venue.
 *
 * @see [InlineQueryResultVenue] (https://core.telegram.org/bots/api/#inlinequeryresultvenue)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param latitude Latitude of the venue location in degrees
 * @param longitude Longitude of the venue location in degrees
 * @param title Title of the venue
 * @param address Address of the venue
 * @param foursquareId *Optional*. Foursquare identifier of the venue if known
 * @param foursquareType *Optional*. Foursquare type of the venue, if known. (For example,
 * “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
 * @param googlePlaceId *Optional*. Google Places identifier of the venue
 * @param googlePlaceType *Optional*. Google Places type of the venue. (See [supported
 * types](https://developers.google.com/places/web-service/supported_types).)
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the venue
 * @param thumbnailUrl *Optional*. Url of the thumbnail for the result
 * @param thumbnailWidth *Optional*. Thumbnail width
 * @param thumbnailHeight *Optional*. Thumbnail height
 */
public data class InlineQueryResultVenue(
    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * Latitude of the venue location in degrees
     */
    @get:JsonProperty("latitude")
    @param:JsonProperty("latitude")
    public val latitude: Double,
    /**
     * Longitude of the venue location in degrees
     */
    @get:JsonProperty("longitude")
    @param:JsonProperty("longitude")
    public val longitude: Double,
    /**
     * Title of the venue
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Address of the venue
     */
    @get:JsonProperty("address")
    @param:JsonProperty("address")
    public val address: String,
    /**
     * *Optional*. Foursquare identifier of the venue if known
     */
    @get:JsonProperty("foursquare_id")
    @param:JsonProperty("foursquare_id")
    public val foursquareId: String? = null,
    /**
     * *Optional*. Foursquare type of the venue, if known. (For example,
     * “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     */
    @get:JsonProperty("foursquare_type")
    @param:JsonProperty("foursquare_type")
    public val foursquareType: String? = null,
    /**
     * *Optional*. Google Places identifier of the venue
     */
    @get:JsonProperty("google_place_id")
    @param:JsonProperty("google_place_id")
    public val googlePlaceId: String? = null,
    /**
     * *Optional*. Google Places type of the venue. (See [supported
     * types](https://developers.google.com/places/web-service/supported_types).)
     */
    @get:JsonProperty("google_place_type")
    @param:JsonProperty("google_place_type")
    public val googlePlaceType: String? = null,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the venue
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
    override val type: String = "venue"
}
