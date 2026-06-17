package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * An expandable block for details disclosure, corresponding to the HTML tag `<details>`.
 *
 * @see [RichBlockDetails] (https://core.telegram.org/bots/api/#richblockdetails)
 *
 * @author KScript
 *
 * @param type Type of the block, always “details”
 * @param summary Always shown summary of the block
 * @param blocks Content of the block
 * @param isOpen *Optional*. *True*, if the content of the block is visible by default
 */
public data class RichBlockDetails(
    /**
     * Type of the block, always “details”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Always shown summary of the block
     */
    @get:JsonProperty("summary")
    @param:JsonProperty("summary")
    public val summary: RichText,
    /**
     * Content of the block
     */
    @get:JsonProperty("blocks")
    @param:JsonProperty("blocks")
    public val blocks: List<RichBlock>,
    /**
     * *Optional*. *True*, if the content of the block is visible by default
     */
    @get:JsonProperty("is_open")
    @param:JsonProperty("is_open")
    public val isOpen: Boolean? = null,
) : RichBlock
