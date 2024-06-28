package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents a venue.
 *
 * @see [Venue] (https://core.telegram.org/bots/api/#venue)
 *
 * @author KScript
 *
 * @param location Venue location. Can't be a live location
 * @param title Name of the venue
 * @param address Address of the venue
 * @param foursquareId *Optional*. Foursquare identifier of the venue
 * @param foursquareType *Optional*. Foursquare type of the venue. (For example,
 * “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
 * @param googlePlaceId *Optional*. Google Places identifier of the venue
 * @param googlePlaceType *Optional*. Google Places type of the venue. (See [supported
 * types](https://developers.google.com/places/web-service/supported_types).)
 */
public data class Venue(
    /**
     * Venue location. Can't be a live location
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location,
    /**
     * Name of the venue
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
     * *Optional*. Foursquare identifier of the venue
     */
    @get:JsonProperty("foursquare_id")
    @param:JsonProperty("foursquare_id")
    public val foursquareId: String? = null,
    /**
     * *Optional*. Foursquare type of the venue. (For example, “arts_entertainment/default”,
     * “arts_entertainment/aquarium” or “food/icecream”.)
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
)
