package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * A list of blocks, corresponding to the HTML tag `<ul>` or `<ol>` with multiple nested tags
 * `<li>`.
 *
 * @see [RichBlockList] (https://core.telegram.org/bots/api/#richblocklist)
 *
 * @author KScript
 *
 * @param type Type of the block, always “list”
 * @param items Items of the list
 */
public data class RichBlockList(
    /**
     * Type of the block, always “list”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Items of the list
     */
    @get:JsonProperty("items")
    @param:JsonProperty("items")
    public val items: List<RichBlockListItem>,
) : RichBlock
