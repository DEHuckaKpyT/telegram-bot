package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Contains information about the location of a Telegram Business account.
 *
 * @see [BusinessLocation] (https://core.telegram.org/bots/api/#businesslocation)
 *
 * @author KScript
 *
 * @param address Address of the business
 * @param location *Optional*. Location of the business
 */
public data class BusinessLocation(
    /**
     * Address of the business
     */
    @get:JsonProperty("address")
    @param:JsonProperty("address")
    public val address: String,
    /**
     * *Optional*. Location of the business
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location? = null,
)
