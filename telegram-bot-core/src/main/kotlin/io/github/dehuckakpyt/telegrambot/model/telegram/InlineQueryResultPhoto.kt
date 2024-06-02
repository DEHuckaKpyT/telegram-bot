package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents a link to a photo. By default, this photo will be sent by the user with optional
 * caption. Alternatively, you can use *input_message_content* to send a message with the specified
 * content instead of the photo.
 *
 * @see [InlineQueryResultPhoto] (https://core.telegram.org/bots/api/#inlinequeryresultphoto)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param photoUrl A valid URL of the photo. Photo must be in **JPEG** format. Photo size must not
 * exceed 5MB
 * @param thumbnailUrl URL of the thumbnail for the photo
 * @param photoWidth *Optional*. Width of the photo
 * @param photoHeight *Optional*. Height of the photo
 * @param title *Optional*. Title for the result
 * @param description *Optional*. Short description of the result
 * @param caption *Optional*. Caption of the photo to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the photo caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the photo
 */
public data class InlineQueryResultPhoto(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * A valid URL of the photo. Photo must be in **JPEG** format. Photo size must not exceed 5MB
     */
    @get:JsonProperty("photo_url")
    @param:JsonProperty("photo_url")
    public val photoUrl: String,
    /**
     * URL of the thumbnail for the photo
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String,
    /**
     * *Optional*. Width of the photo
     */
    @get:JsonProperty("photo_width")
    @param:JsonProperty("photo_width")
    public val photoWidth: Int? = null,
    /**
     * *Optional*. Height of the photo
     */
    @get:JsonProperty("photo_height")
    @param:JsonProperty("photo_height")
    public val photoHeight: Int? = null,
    /**
     * *Optional*. Title for the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Short description of the result
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String? = null,
    /**
     * *Optional*. Caption of the photo to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the photo caption. See [formatting
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
     * *Optional*. Pass *True*, if the caption must be shown above the message media
     */
    @get:JsonProperty("show_caption_above_media")
    @param:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the photo
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "photo"
}
