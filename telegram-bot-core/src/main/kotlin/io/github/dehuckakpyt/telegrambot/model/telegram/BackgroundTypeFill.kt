package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * The background is automatically filled based on the selected colors.
 *
 * @see [BackgroundTypeFill] (https://core.telegram.org/bots/api/#backgroundtypefill)
 *
 * @author KScript
 *
 * @param type Type of the background, always “fill”
 * @param fill The background fill
 * @param darkThemeDimming Dimming of the background in dark themes, as a percentage; 0-100
 */
public data class BackgroundTypeFill(
    /**
     * Type of the background, always “fill”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The background fill
     */
    @get:JsonProperty("fill")
    @param:JsonProperty("fill")
    public val fill: BackgroundFill,
    /**
     * Dimming of the background in dark themes, as a percentage; 0-100
     */
    @get:JsonProperty("dark_theme_dimming")
    @param:JsonProperty("dark_theme_dimming")
    public val darkThemeDimming: Int,
) : BackgroundType
