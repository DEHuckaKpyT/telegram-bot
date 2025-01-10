package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * The background is a .PNG or .TGV (gzipped subset of SVG with MIME type
 * “application/x-tgwallpattern”) pattern to be combined with the background fill chosen by the user.
 *
 * @see [BackgroundTypePattern] (https://core.telegram.org/bots/api/#backgroundtypepattern)
 *
 * @author KScript
 *
 * @param type Type of the background, always “pattern”
 * @param document Document with the pattern
 * @param fill The background fill that is combined with the pattern
 * @param intensity Intensity of the pattern when it is shown above the filled background; 0-100
 * @param isInverted *Optional*. *True*, if the background fill must be applied only to the pattern
 * itself. All other pixels are black in this case. For dark themes only
 * @param isMoving *Optional*. *True*, if the background moves slightly when the device is tilted
 */
public data class BackgroundTypePattern(
    /**
     * Type of the background, always “pattern”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Document with the pattern
     */
    @get:JsonProperty("document")
    @param:JsonProperty("document")
    public val document: Document,
    /**
     * The background fill that is combined with the pattern
     */
    @get:JsonProperty("fill")
    @param:JsonProperty("fill")
    public val fill: BackgroundFill,
    /**
     * Intensity of the pattern when it is shown above the filled background; 0-100
     */
    @get:JsonProperty("intensity")
    @param:JsonProperty("intensity")
    public val intensity: Int,
    /**
     * *Optional*. *True*, if the background fill must be applied only to the pattern itself. All
     * other pixels are black in this case. For dark themes only
     */
    @get:JsonProperty("is_inverted")
    @param:JsonProperty("is_inverted")
    public val isInverted: Boolean? = null,
    /**
     * *Optional*. *True*, if the background moves slightly when the device is tilted
     */
    @get:JsonProperty("is_moving")
    @param:JsonProperty("is_moving")
    public val isMoving: Boolean? = null,
) : BackgroundType
