package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents a link to a file. By default, this file will be sent by the user with an optional
 * caption. Alternatively, you can use *input_message_content* to send a message with the specified
 * content instead of the file. Currently, only **.PDF** and **.ZIP** files can be sent using this
 * method.
 *
 * @see [InlineQueryResultDocument] (https://core.telegram.org/bots/api/#inlinequeryresultdocument)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param title Title for the result
 * @param caption *Optional*. Caption of the document to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the document caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param documentUrl A valid URL for the file
 * @param mimeType MIME type of the content of the file, either “application/pdf” or
 * “application/zip”
 * @param description *Optional*. Short description of the result
 * @param replyMarkup *Optional*. Inline keyboard attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the file
 * @param thumbnailUrl *Optional*. URL of the thumbnail (JPEG only) for the file
 * @param thumbnailWidth *Optional*. Thumbnail width
 * @param thumbnailHeight *Optional*. Thumbnail height
 */
public data class InlineQueryResultDocument(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * Title for the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * *Optional*. Caption of the document to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the document caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in the caption, which can be specified
     * instead of *parse_mode*
     */
    @get:JsonProperty("caption_entities")
    @param:JsonProperty("caption_entities")
    public val captionEntities: List<MessageEntity>? = null,
    /**
     * A valid URL for the file
     */
    @get:JsonProperty("document_url")
    @param:JsonProperty("document_url")
    public val documentUrl: String,
    /**
     * MIME type of the content of the file, either “application/pdf” or “application/zip”
     */
    @get:JsonProperty("mime_type")
    @param:JsonProperty("mime_type")
    public val mimeType: String,
    /**
     * *Optional*. Short description of the result
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String? = null,
    /**
     * *Optional*. Inline keyboard attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the file
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
    /**
     * *Optional*. URL of the thumbnail (JPEG only) for the file
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
    override val type: String = "document"
}
