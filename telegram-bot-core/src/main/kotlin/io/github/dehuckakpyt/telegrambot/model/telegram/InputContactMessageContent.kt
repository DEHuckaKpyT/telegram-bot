package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents the [content](https://core.telegram.org/bots/api/#inputmessagecontent) of a contact
 * message to be sent as the result of an inline query.
 *
 * @see [InputContactMessageContent]
 * (https://core.telegram.org/bots/api/#inputcontactmessagecontent)
 *
 * @author KScript
 *
 * @param phoneNumber Contact's phone number
 * @param firstName Contact's first name
 * @param lastName *Optional*. Contact's last name
 * @param vcard *Optional*. Additional data about the contact in the form of a
 * [vCard](https://en.wikipedia.org/wiki/VCard), 0-2048 bytes
 */
public data class InputContactMessageContent(
    /**
     * Contact's phone number
     */
    @get:JsonProperty("phone_number")
    @param:JsonProperty("phone_number")
    public val phoneNumber: String,
    /**
     * Contact's first name
     */
    @get:JsonProperty("first_name")
    @param:JsonProperty("first_name")
    public val firstName: String,
    /**
     * *Optional*. Contact's last name
     */
    @get:JsonProperty("last_name")
    @param:JsonProperty("last_name")
    public val lastName: String? = null,
    /**
     * *Optional*. Additional data about the contact in the form of a
     * [vCard](https://en.wikipedia.org/wiki/VCard), 0-2048 bytes
     */
    @get:JsonProperty("vcard")
    @param:JsonProperty("vcard")
    public val vcard: String? = null,
) : InputMessageContent
