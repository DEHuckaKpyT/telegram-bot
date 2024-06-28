package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * This object represents a shipping address.
 *
 * @see [ShippingAddress] (https://core.telegram.org/bots/api/#shippingaddress)
 *
 * @author KScript
 *
 * @param countryCode Two-letter [ISO 3166-1
 * alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) country code
 * @param state State, if applicable
 * @param city City
 * @param streetLine1 First line for the address
 * @param streetLine2 Second line for the address
 * @param postCode Address post code
 */
public data class ShippingAddress(
    /**
     * Two-letter [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) country
     * code
     */
    @get:JsonProperty("country_code")
    @param:JsonProperty("country_code")
    public val countryCode: String,
    /**
     * State, if applicable
     */
    @get:JsonProperty("state")
    @param:JsonProperty("state")
    public val state: String,
    /**
     * City
     */
    @get:JsonProperty("city")
    @param:JsonProperty("city")
    public val city: String,
    /**
     * First line for the address
     */
    @get:JsonProperty("street_line1")
    @param:JsonProperty("street_line1")
    public val streetLine1: String,
    /**
     * Second line for the address
     */
    @get:JsonProperty("street_line2")
    @param:JsonProperty("street_line2")
    public val streetLine2: String,
    /**
     * Address post code
     */
    @get:JsonProperty("post_code")
    @param:JsonProperty("post_code")
    public val postCode: String,
)
