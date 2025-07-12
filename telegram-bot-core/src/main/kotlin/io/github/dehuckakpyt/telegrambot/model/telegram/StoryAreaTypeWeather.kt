package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.Int
import kotlin.String

/**
 * Describes a story area containing weather information. Currently, a story can have up to 3
 * weather areas.
 *
 * @see [StoryAreaTypeWeather] (https://core.telegram.org/bots/api/#storyareatypeweather)
 *
 * @author KScript
 *
 * @param type Type of the area, always “weather”
 * @param temperature Temperature, in degree Celsius
 * @param emoji Emoji representing the weather
 * @param backgroundColor A color of the area background in the ARGB format
 */
public data class StoryAreaTypeWeather(
    /**
     * Type of the area, always “weather”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Temperature, in degree Celsius
     */
    @get:JsonProperty("temperature")
    @param:JsonProperty("temperature")
    public val temperature: Double,
    /**
     * Emoji representing the weather
     */
    @get:JsonProperty("emoji")
    @param:JsonProperty("emoji")
    public val emoji: String,
    /**
     * A color of the area background in the ARGB format
     */
    @get:JsonProperty("background_color")
    @param:JsonProperty("background_color")
    public val backgroundColor: Int,
) : StoryAreaType
