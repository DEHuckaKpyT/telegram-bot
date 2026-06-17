package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A text with an email address.
 *
 * @see [RichTextEmailAddress] (https://core.telegram.org/bots/api/#richtextemailaddress)
 *
 * @author KScript
 *
 * @param type Type of the rich text, always “email_address”
 * @param text The text
 * @param emailAddress The email address
 */
public data class RichTextEmailAddress(
    /**
     * Type of the rich text, always “email_address”
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
     * The email address
     */
    @get:JsonProperty("email_address")
    @param:JsonProperty("email_address")
    public val emailAddress: String,
) : RichText
