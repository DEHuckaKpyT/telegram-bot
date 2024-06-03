package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * Created on 03.06.2024.
 *
 * Describes an interval of time during which a business is open.
 *
 * @see [BusinessOpeningHoursInterval]
 * (https://core.telegram.org/bots/api/#businessopeninghoursinterval)
 *
 * @author KScript
 *
 * @param openingMinute The minute's sequence number in a week, starting on Monday, marking the
 * start of the time interval during which the business is open; 0 - 7 \* 24 \* 60
 * @param closingMinute The minute's sequence number in a week, starting on Monday, marking the end
 * of the time interval during which the business is open; 0 - 8 \* 24 \* 60
 */
public data class BusinessOpeningHoursInterval(
    /**
     * The minute's sequence number in a week, starting on Monday, marking the start of the time
     * interval during which the business is open; 0 - 7 \* 24 \* 60
     */
    @get:JsonProperty("opening_minute")
    @param:JsonProperty("opening_minute")
    public val openingMinute: Int,
    /**
     * The minute's sequence number in a week, starting on Monday, marking the end of the time
     * interval during which the business is open; 0 - 8 \* 24 \* 60
     */
    @get:JsonProperty("closing_minute")
    @param:JsonProperty("closing_minute")
    public val closingMinute: Int,
)
