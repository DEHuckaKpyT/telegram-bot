package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * A table, corresponding to the HTML tag `<table>`.
 *
 * @see [RichBlockTable] (https://core.telegram.org/bots/api/#richblocktable)
 *
 * @author KScript
 *
 * @param type Type of the block, always “table”
 * @param cells Cells of the table
 * @param isBordered *Optional*. *True*, if the table has borders
 * @param isStriped *Optional*. *True*, if the table is striped
 * @param caption *Optional*. Caption of the table
 */
public data class RichBlockTable(
    /**
     * Type of the block, always “table”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Cells of the table
     */
    @get:JsonProperty("cells")
    @param:JsonProperty("cells")
    public val cells: List<List<RichBlockTableCell>>,
    /**
     * *Optional*. *True*, if the table has borders
     */
    @get:JsonProperty("is_bordered")
    @param:JsonProperty("is_bordered")
    public val isBordered: Boolean? = null,
    /**
     * *Optional*. *True*, if the table is striped
     */
    @get:JsonProperty("is_striped")
    @param:JsonProperty("is_striped")
    public val isStriped: Boolean? = null,
    /**
     * *Optional*. Caption of the table
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichText? = null,
) : RichBlock
