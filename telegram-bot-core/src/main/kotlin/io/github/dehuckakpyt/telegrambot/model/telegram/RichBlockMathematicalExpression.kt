package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A block with a mathematical expression in LaTeX format, corresponding to the custom HTML tag
 * `<tg-math-block>`.
 *
 * @see [RichBlockMathematicalExpression]
 * (https://core.telegram.org/bots/api/#richblockmathematicalexpression)
 *
 * @author KScript
 *
 * @param type Type of the block, always “mathematical_expression”
 * @param expression The mathematical expression in LaTeX format
 */
public data class RichBlockMathematicalExpression(
    /**
     * Type of the block, always “mathematical_expression”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The mathematical expression in LaTeX format
     */
    @get:JsonProperty("expression")
    @param:JsonProperty("expression")
    public val expression: String,
) : RichBlock
