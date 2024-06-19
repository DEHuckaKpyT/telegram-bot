package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object represents a phone contact.
 *
 * @see [Contact] (https://core.telegram.org/bots/api/#contact)
 *
 * @author KScript
 *
 * @param phoneNumber Contact's phone number
 * @param firstName Contact's first name
 * @param lastName *Optional*. Contact's last name
 * @param userId *Optional*. Contact's user identifier in Telegram. This number may have more than
 * 32 significant bits and some programming languages may have difficulty/silent defects in
 * interpreting it. But it has at most 52 significant bits, so a 64-bit integer or double-precision
 * float type are safe for storing this identifier.
 * @param vcard *Optional*. Additional data about the contact in the form of a
 * [vCard](https://en.wikipedia.org/wiki/VCard)
 */
public data class Contact(
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
     * *Optional*. Contact's user identifier in Telegram. This number may have more than 32
     * significant bits and some programming languages may have difficulty/silent defects in
     * interpreting it. But it has at most 52 significant bits, so a 64-bit integer or double-precision
     * float type are safe for storing this identifier.
     */
    @get:JsonProperty("user_id")
    @param:JsonProperty("user_id")
    public val userId: Long? = null,
    /**
     * *Optional*. Additional data about the contact in the form of a
     * [vCard](https://en.wikipedia.org/wiki/VCard)
     */
    @get:JsonProperty("vcard")
    @param:JsonProperty("vcard")
    public val vcard: String? = null,
)
