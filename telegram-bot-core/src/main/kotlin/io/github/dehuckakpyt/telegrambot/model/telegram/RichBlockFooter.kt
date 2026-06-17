package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A footer, corresponding to the HTML tag `<footer>`.
 *
 * @see [RichBlockFooter] (https://core.telegram.org/bots/api/#richblockfooter)
 *
 * @author KScript
 *
 * @param type Type of the block, always “footer”
 * @param text Text of the block
 */
public data class RichBlockFooter(
    /**
     * Type of the block, always “footer”
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
