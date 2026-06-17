package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text with a bank card number.
 *
 * @see [RichTextBankCardNumber] (https://core.telegram.org/bots/api/#richtextbankcardnumber)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “bank_card_number”
 * @param text The text
 * @param bankCardNumber The bank card number
 */
public data class RichTextBankCardNumber(
    /**
     * Type of the rich text, always “bank_card_number”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The text
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
    /**
     * The bank card number
     */
    @get:JsonProperty("bank_card_number")
    @param:JsonProperty("bank_card_number")
    public val bankCardNumber: String,
) : RichText
