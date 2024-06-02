package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Represents a contact with a phone number. By default, this contact will be sent by the user.
 * Alternatively, you can use *input_message_content* to send a message with the specified content
 * instead of the contact.
 *
 * @see [InlineQueryResultContact] (https://core.telegram.org/bots/api/#inlinequeryresultcontact)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 Bytes
 * @param phoneNumber Contact's phone number
 * @param firstName Contact's first name
 * @param lastName *Optional*. Contact's last name
 * @param vcard *Optional*. Additional data about the contact in the form of a
 * [vCard](https://en.wikipedia.org/wiki/VCard), 0-2048 bytes
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the contact
 * @param thumbnailUrl *Optional*. Url of the thumbnail for the result
 * @param thumbnailWidth *Optional*. Thumbnail width
 * @param thumbnailHeight *Optional*. Thumbnail height
 */
public data class InlineQueryResultContact(
    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
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
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the contact
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
    /**
     * *Optional*. Url of the thumbnail for the result
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String? = null,
    /**
     * *Optional*. Thumbnail width
     */
    @get:JsonProperty("thumbnail_width")
    @param:JsonProperty("thumbnail_width")
    public val thumbnailWidth: Int? = null,
    /**
     * *Optional*. Thumbnail height
     */
    @get:JsonProperty("thumbnail_height")
    @param:JsonProperty("thumbnail_height")
    public val thumbnailHeight: Int? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "contact"
}
