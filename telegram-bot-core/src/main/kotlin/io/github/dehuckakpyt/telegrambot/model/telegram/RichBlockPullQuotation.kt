package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A quotation with centered text, loosely corresponding to the HTML tag `<aside>`.
 *
 * @see [RichBlockPullQuotation] (https://core.telegram.org/bots/api/#richblockpullquotation)
 *
 * @author KScript
 *
 * @param type Type of the block, always “pullquote”
 * @param text Text of the block
 * @param credit *Optional*. Credit of the block
 */
public data class RichBlockPullQuotation(
    /**
     * Type of the block, always “pullquote”
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
     * *Optional*. Credit of the block
     */
    @get:JsonProperty("credit")
    @param:JsonProperty("credit")
    public val credit: RichText? = null,
) : RichBlock
