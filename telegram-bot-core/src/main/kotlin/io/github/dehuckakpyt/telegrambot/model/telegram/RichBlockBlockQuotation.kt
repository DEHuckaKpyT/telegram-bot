package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * A block quotation, corresponding to the HTML tag `<blockquote>`.
 *
 * @see [RichBlockBlockQuotation] (https://core.telegram.org/bots/api/#richblockblockquotation)
 *
 * @author KScript
 *
 * @param type Type of the block, always “blockquote”
 * @param blocks Content of the block
 * @param credit *Optional*. Credit of the block
 */
public data class RichBlockBlockQuotation(
    /**
     * Type of the block, always “blockquote”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Content of the block
     */
    @get:JsonProperty("blocks")
    @param:JsonProperty("blocks")
    public val blocks: List<RichBlock>,
    /**
     * *Optional*. Credit of the block
     */
    @get:JsonProperty("credit")
    @param:JsonProperty("credit")
    public val credit: RichText? = null,
) : RichBlock
