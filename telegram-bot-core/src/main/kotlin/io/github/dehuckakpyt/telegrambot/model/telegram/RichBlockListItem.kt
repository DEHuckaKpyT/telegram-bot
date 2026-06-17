package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * An item of a list.
 *
 * @see [RichBlockListItem] (https://core.telegram.org/bots/api/#richblocklistitem)
 *
 * @author KScript
 *
 * @param label Label of the item
 * @param blocks The content of the item
 * @param hasCheckbox *Optional*. *True*, if the item has a checkbox
 * @param isChecked *Optional*. *True*, if the item has a checked checkbox
 * @param value *Optional*. For ordered lists, the numeric value of the item label
 * @param type *Optional*. For ordered lists, the type of the item label; must be one of “a” for
 * lowercase letters, “A” for uppercase letters, “i” for lowercase Roman numerals, “I” for uppercase
 * Roman numerals, or “1” for decimal numbers
 */
public data class RichBlockListItem(
    /**
     * Label of the item
     */
    @get:JsonProperty("label")
    @param:JsonProperty("label")
    public val label: String,
    /**
     * The content of the item
     */
    @get:JsonProperty("blocks")
    @param:JsonProperty("blocks")
    public val blocks: List<RichBlock>,
    /**
     * *Optional*. *True*, if the item has a checkbox
     */
    @get:JsonProperty("has_checkbox")
    @param:JsonProperty("has_checkbox")
    public val hasCheckbox: Boolean? = null,
    /**
     * *Optional*. *True*, if the item has a checked checkbox
     */
    @get:JsonProperty("is_checked")
    @param:JsonProperty("is_checked")
    public val isChecked: Boolean? = null,
    /**
     * *Optional*. For ordered lists, the numeric value of the item label
     */
    @get:JsonProperty("value")
    @param:JsonProperty("value")
    public val `value`: Int? = null,
    /**
     * *Optional*. For ordered lists, the type of the item label; must be one of “a” for lowercase
     * letters, “A” for uppercase letters, “i” for lowercase Roman numerals, “I” for uppercase Roman
     * numerals, or “1” for decimal numbers
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String? = null,
)
