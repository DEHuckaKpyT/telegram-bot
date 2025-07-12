package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object describes the colors of the backdrop of a unique gift.
 *
 * @see [UniqueGiftBackdropColors] (https://core.telegram.org/bots/api/#uniquegiftbackdropcolors)
 *
 * @author KScript
 *
 * @param centerColor The color in the center of the backdrop in RGB format
 * @param edgeColor The color on the edges of the backdrop in RGB format
 * @param symbolColor The color to be applied to the symbol in RGB format
 * @param textColor The color for the text on the backdrop in RGB format
 */
public data class UniqueGiftBackdropColors(
    /**
     * The color in the center of the backdrop in RGB format
     */
    @get:JsonProperty("center_color")
    @param:JsonProperty("center_color")
    public val centerColor: Int,
    /**
     * The color on the edges of the backdrop in RGB format
     */
    @get:JsonProperty("edge_color")
    @param:JsonProperty("edge_color")
    public val edgeColor: Int,
    /**
     * The color to be applied to the symbol in RGB format
     */
    @get:JsonProperty("symbol_color")
    @param:JsonProperty("symbol_color")
    public val symbolColor: Int,
    /**
     * The color for the text on the backdrop in RGB format
     */
    @get:JsonProperty("text_color")
    @param:JsonProperty("text_color")
    public val textColor: Int,
)
