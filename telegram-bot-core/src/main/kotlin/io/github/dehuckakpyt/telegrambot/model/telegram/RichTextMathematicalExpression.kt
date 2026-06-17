package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A mathematical expression.
 *
 * @see [RichTextMathematicalExpression]
 * (https://core.telegram.org/bots/api/#richtextmathematicalexpression)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “mathematical_expression”
 * @param expression The expression in LaTeX format
 */
public data class RichTextMathematicalExpression(
    /**
     * Type of the rich text, always “mathematical_expression”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The expression in LaTeX format
     */
    @get:JsonProperty("expression")
    @param:JsonProperty("expression")
    public val expression: String,
) : RichText
