package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text with a phone number.
 *
 * @see [RichTextPhoneNumber] (https://core.telegram.org/bots/api/#richtextphonenumber)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “phone_number”
 * @param text The text
 * @param phoneNumber The phone number
 */
public data class RichTextPhoneNumber(
    /**
     * Type of the rich text, always “phone_number”
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
     * The phone number
     */
    @get:JsonProperty("phone_number")
    @param:JsonProperty("phone_number")
    public val phoneNumber: String,
) : RichText
