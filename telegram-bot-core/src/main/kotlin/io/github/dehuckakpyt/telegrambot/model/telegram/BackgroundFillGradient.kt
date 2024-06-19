package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * The background is a gradient fill.
 *
 * @see [BackgroundFillGradient] (https://core.telegram.org/bots/api/#backgroundfillgradient)
 *
 * @author KScript
 *
 * @param type Type of the background fill, always “gradient”
 * @param topColor Top color of the gradient in the RGB24 format
 * @param bottomColor Bottom color of the gradient in the RGB24 format
 * @param rotationAngle Clockwise rotation angle of the background fill in degrees; 0-359
 */
public data class BackgroundFillGradient(
    /**
     * Type of the background fill, always “gradient”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Top color of the gradient in the RGB24 format
     */
    @get:JsonProperty("top_color")
    @param:JsonProperty("top_color")
    public val topColor: Int,
    /**
     * Bottom color of the gradient in the RGB24 format
     */
    @get:JsonProperty("bottom_color")
    @param:JsonProperty("bottom_color")
    public val bottomColor: Int,
    /**
     * Clockwise rotation angle of the background fill in degrees; 0-359
     */
    @get:JsonProperty("rotation_angle")
    @param:JsonProperty("rotation_angle")
    public val rotationAngle: Int,
) : BackgroundFill
