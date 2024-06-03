package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object describes the position on faces where a mask should be placed by default.
 *
 * @see [MaskPosition] (https://core.telegram.org/bots/api/#maskposition)
 *
 * @author KScript
 *
 * @param point The part of the face relative to which the mask should be placed. One of “forehead”,
 * “eyes”, “mouth”, or “chin”.
 * @param xShift Shift by X-axis measured in widths of the mask scaled to the face size, from left
 * to right. For example, choosing -1.0 will place mask just to the left of the default mask position.
 * @param yShift Shift by Y-axis measured in heights of the mask scaled to the face size, from top
 * to bottom. For example, 1.0 will place the mask just below the default mask position.
 * @param scale Mask scaling coefficient. For example, 2.0 means double size.
 */
public data class MaskPosition(
    /**
     * The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”,
     * “mouth”, or “chin”.
     */
    @get:JsonProperty("point")
    @param:JsonProperty("point")
    public val point: String,
    /**
     * Shift by X-axis measured in widths of the mask scaled to the face size, from left to right.
     * For example, choosing -1.0 will place mask just to the left of the default mask position.
     */
    @get:JsonProperty("x_shift")
    @param:JsonProperty("x_shift")
    public val xShift: Double,
    /**
     * Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom.
     * For example, 1.0 will place the mask just below the default mask position.
     */
    @get:JsonProperty("y_shift")
    @param:JsonProperty("y_shift")
    public val yShift: Double,
    /**
     * Mask scaling coefficient. For example, 2.0 means double size.
     */
    @get:JsonProperty("scale")
    @param:JsonProperty("scale")
    public val scale: Double,
)
