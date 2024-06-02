package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * The background is a wallpaper in the JPEG format.
 *
 * @see [BackgroundTypeWallpaper] (https://core.telegram.org/bots/api/#backgroundtypewallpaper)
 *
 * @author KScript
 *
 * @param type Type of the background, always “wallpaper”
 * @param document Document with the wallpaper
 * @param darkThemeDimming Dimming of the background in dark themes, as a percentage; 0-100
 * @param isBlurred *Optional*. *True*, if the wallpaper is downscaled to fit in a 450x450 square
 * and then box-blurred with radius 12
 * @param isMoving *Optional*. *True*, if the background moves slightly when the device is tilted
 */
public data class BackgroundTypeWallpaper(
    /**
     * Type of the background, always “wallpaper”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Document with the wallpaper
     */
    @get:JsonProperty("document")
    @param:JsonProperty("document")
    public val document: Document,
    /**
     * Dimming of the background in dark themes, as a percentage; 0-100
     */
    @get:JsonProperty("dark_theme_dimming")
    @param:JsonProperty("dark_theme_dimming")
    public val darkThemeDimming: Int,
    /**
     * *Optional*. *True*, if the wallpaper is downscaled to fit in a 450x450 square and then
     * box-blurred with radius 12
     */
    @get:JsonProperty("is_blurred")
    @param:JsonProperty("is_blurred")
    public val isBlurred: Boolean? = null,
    /**
     * *Optional*. *True*, if the background moves slightly when the device is tilted
     */
    @get:JsonProperty("is_moving")
    @param:JsonProperty("is_moving")
    public val isMoving: Boolean? = null,
) : BackgroundType
