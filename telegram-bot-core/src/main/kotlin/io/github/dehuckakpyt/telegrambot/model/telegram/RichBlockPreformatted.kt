package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A preformatted text block, corresponding to the nested HTML tags `<pre>` and `<code>`.
 *
 * @see [RichBlockPreformatted] (https://core.telegram.org/bots/api/#richblockpreformatted)
 *
 * @author KScript
 *
 * @param type Type of the block, always “pre”
 * @param text Text of the block
 * @param language *Optional*. The programming language of the text
 */
public data class RichBlockPreformatted(
    /**
     * Type of the block, always “pre”
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
     * *Optional*. The programming language of the text
     */
    @get:JsonProperty("language")
    @param:JsonProperty("language")
    public val language: String? = null,
) : RichBlock
