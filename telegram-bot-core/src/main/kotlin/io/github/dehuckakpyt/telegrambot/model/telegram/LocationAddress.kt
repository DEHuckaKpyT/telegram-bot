package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes the physical address of a location.
 *
 * @see [LocationAddress] (https://core.telegram.org/bots/api/#locationaddress)
 *
 * @author KScript
 *
 * @param countryCode The two-letter ISO 3166-1 alpha-2 country code of the country where the
 * location is located
 * @param state *Optional*. State of the location
 * @param city *Optional*. City of the location
 * @param street *Optional*. Street address of the location
 */
public data class LocationAddress(
    /**
     * The two-letter ISO 3166-1 alpha-2 country code of the country where the location is located
     */
    @get:JsonProperty("country_code")
    @param:JsonProperty("country_code")
    public val countryCode: String,
    /**
     * *Optional*. State of the location
     */
    @get:JsonProperty("state")
    @param:JsonProperty("state")
    public val state: String? = null,
    /**
     * *Optional*. City of the location
     */
    @get:JsonProperty("city")
    @param:JsonProperty("city")
    public val city: String? = null,
    /**
     * *Optional*. Street address of the location
     */
    @get:JsonProperty("street")
    @param:JsonProperty("street")
    public val street: String? = null,
)
