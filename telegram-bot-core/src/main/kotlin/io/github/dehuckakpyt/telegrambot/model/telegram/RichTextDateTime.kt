package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Formatted date and time.
 *
 * @see [RichTextDateTime] (https://core.telegram.org/bots/api/#richtextdatetime)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “date_time”
 * @param text The text
 * @param unixTime The Unix time associated with the entity
 * @param dateTimeFormat The string that defines the formatting of the date and time. See [date-time
 * entity formatting](https://core.telegram.org/bots/api/#date-time-entity-formatting) for more
 * details.
 */
public data class RichTextDateTime(
    /**
     * Type of the rich text, always “date_time”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The Unix time associated with the entity
     */
    @get:JsonProperty("unix_time")
    @param:JsonProperty("unix_time")
    public val unixTime: Int,
    /**
     * The string that defines the formatting of the date and time. See [date-time entity
     * formatting](https://core.telegram.org/bots/api/#date-time-entity-formatting) for more details.
     */
    @get:JsonProperty("date_time_format")
    @param:JsonProperty("date_time_format")
    public val dateTimeFormat: String,
) : RichText
