package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text paragraph, corresponding to the HTML tag `<p>`.
 *
 * @see [RichBlockParagraph] (https://core.telegram.org/bots/api/#richblockparagraph)
 *
 * @author KScript
 *
 * @param type Type of the block, always “paragraph”
 * @param text Text of the block
 */
public data class RichBlockParagraph(
    /**
     * Type of the block, always “paragraph”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Text of the block
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
) : RichBlock
