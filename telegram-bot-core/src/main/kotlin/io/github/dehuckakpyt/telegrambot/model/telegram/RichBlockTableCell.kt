package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * Cell in a table.
 *
 * @see [RichBlockTableCell] (https://core.telegram.org/bots/api/#richblocktablecell)
 *
 * @author KScript
 *
 * @param text *Optional*. Text in the cell. If omitted, then the cell is invisible.
 * @param isHeader *Optional*. *True*, if the cell is a header cell
 * @param colspan *Optional*. The number of columns the cell spans if it is bigger than 1
 * @param rowspan *Optional*. The number of rows the cell spans if it is bigger than 1
 * @param align Horizontal cell content alignment. Currently, must be one of “left”, “center”, or
 * “right”.
 * @param valign Vertical cell content alignment. Currently, must be one of “top”, “middle”, or
 * “bottom”.
 */
public data class RichBlockTableCell(
    /**
     * *Optional*. Text in the cell. If omitted, then the cell is invisible.
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText? = null,
    /**
     * *Optional*. *True*, if the cell is a header cell
     */
    @get:JsonProperty("is_header")
    @param:JsonProperty("is_header")
    public val isHeader: Boolean? = null,
    /**
     * *Optional*. The number of columns the cell spans if it is bigger than 1
     */
    @get:JsonProperty("colspan")
    @param:JsonProperty("colspan")
    public val colspan: Int? = null,
    /**
     * *Optional*. The number of rows the cell spans if it is bigger than 1
     */
    @get:JsonProperty("rowspan")
    @param:JsonProperty("rowspan")
    public val rowspan: Int? = null,
    /**
     * Horizontal cell content alignment. Currently, must be one of “left”, “center”, or “right”.
     */
    @get:JsonProperty("align")
    @param:JsonProperty("align")
    public val align: String,
    /**
     * Vertical cell content alignment. Currently, must be one of “top”, “middle”, or “bottom”.
     */
    @get:JsonProperty("valign")
    @param:JsonProperty("valign")
    public val valign: String,
)
