package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Describes the opening hours of a business.
 *
 * @see [BusinessOpeningHours] (https://core.telegram.org/bots/api/#businessopeninghours)
 *
 * @author KScript
 *
 * @param timeZoneName Unique name of the time zone for which the opening hours are defined
 * @param openingHours List of time intervals describing business opening hours
 */
public data class BusinessOpeningHours(
    /**
     * Unique name of the time zone for which the opening hours are defined
     */
    @get:JsonProperty("time_zone_name")
    @param:JsonProperty("time_zone_name")
    public val timeZoneName: String,
    /**
     * List of time intervals describing business opening hours
     */
    @get:JsonProperty("opening_hours")
    @param:JsonProperty("opening_hours")
    public val openingHours: List<BusinessOpeningHoursInterval>,
)
