package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A divider, corresponding to the HTML tag `<hr/>`.
 *
 * @see [RichBlockDivider] (https://core.telegram.org/bots/api/#richblockdivider)
 *
 * @author KScript
 *
 * @param type Type of the block, always “divider”
 */
public data class RichBlockDivider(
    /**
     * Type of the block, always “divider”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
) : RichBlock
