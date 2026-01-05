package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object describes the background of a gift.
 *
 * @see [GiftBackground] (https://core.telegram.org/bots/api/#giftbackground)
 *
 * @author KScript
 *
 * @param centerColor Center color of the background in RGB format
 * @param edgeColor Edge color of the background in RGB format
 * @param textColor Text color of the background in RGB format
 */
public data class GiftBackground(
    /**
     * Center color of the background in RGB format
     */
    @get:JsonProperty("center_color")
    @param:JsonProperty("center_color")
    public val centerColor: Int,
    /**
     * Edge color of the background in RGB format
     */
    @get:JsonProperty("edge_color")
    @param:JsonProperty("edge_color")
    public val edgeColor: Int,
    /**
     * Text color of the background in RGB format
     */
    @get:JsonProperty("text_color")
    @param:JsonProperty("text_color")
    public val textColor: Int,
)
