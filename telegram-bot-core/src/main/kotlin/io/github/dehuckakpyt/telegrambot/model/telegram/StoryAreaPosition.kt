package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double

/**
 * Describes the position of a clickable area within a story.
 *
 * @see [StoryAreaPosition] (https://core.telegram.org/bots/api/#storyareaposition)
 *
 * @author KScript
 *
 * @param xPercentage The abscissa of the area's center, as a percentage of the media width
 * @param yPercentage The ordinate of the area's center, as a percentage of the media height
 * @param widthPercentage The width of the area's rectangle, as a percentage of the media width
 * @param heightPercentage The height of the area's rectangle, as a percentage of the media height
 * @param rotationAngle The clockwise rotation angle of the rectangle, in degrees; 0-360
 * @param cornerRadiusPercentage The radius of the rectangle corner rounding, as a percentage of the
 * media width
 */
public data class StoryAreaPosition(
    /**
     * The abscissa of the area's center, as a percentage of the media width
     */
    @get:JsonProperty("x_percentage")
    @param:JsonProperty("x_percentage")
    public val xPercentage: Double,
    /**
     * The ordinate of the area's center, as a percentage of the media height
     */
    @get:JsonProperty("y_percentage")
    @param:JsonProperty("y_percentage")
    public val yPercentage: Double,
    /**
     * The width of the area's rectangle, as a percentage of the media width
     */
    @get:JsonProperty("width_percentage")
    @param:JsonProperty("width_percentage")
    public val widthPercentage: Double,
    /**
     * The height of the area's rectangle, as a percentage of the media height
     */
    @get:JsonProperty("height_percentage")
    @param:JsonProperty("height_percentage")
    public val heightPercentage: Double,
    /**
     * The clockwise rotation angle of the rectangle, in degrees; 0-360
     */
    @get:JsonProperty("rotation_angle")
    @param:JsonProperty("rotation_angle")
    public val rotationAngle: Double,
    /**
     * The radius of the rectangle corner rounding, as a percentage of the media width
     */
    @get:JsonProperty("corner_radius_percentage")
    @param:JsonProperty("corner_radius_percentage")
    public val cornerRadiusPercentage: Double,
)
