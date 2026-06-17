package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * A section heading, corresponding to the HTML tags `<h1>`, `<h2>`, `<h3>`, `<h4>`, `<h5>`, or
 * `<h6>`.
 *
 * @see [RichBlockSectionHeading] (https://core.telegram.org/bots/api/#richblocksectionheading)
 *
 * @author KScript
 *
 * @param type Type of the block, always “heading”
 * @param text Text of the block
 * @param size Relative size of the text font; 1-6, 1 is the largest, 6 is the smallest
 */
public data class RichBlockSectionHeading(
    /**
     * Type of the block, always “heading”
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
    /**
     * Relative size of the text font; 1-6, 1 is the largest, 6 is the smallest
     */
    @get:JsonProperty("size")
    @param:JsonProperty("size")
    public val size: Int,
) : RichBlock
