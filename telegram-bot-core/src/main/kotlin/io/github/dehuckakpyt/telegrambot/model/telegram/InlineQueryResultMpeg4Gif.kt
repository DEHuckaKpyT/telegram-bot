package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this
 * animated MPEG-4 file will be sent by the user with optional caption. Alternatively, you can use
 * *input_message_content* to send a message with the specified content instead of the animation.
 *
 * @see [InlineQueryResultMpeg4Gif] (https://core.telegram.org/bots/api/#inlinequeryresultmpeg4gif)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param mpeg4Url A valid URL for the MPEG4 file. File size must not exceed 1MB
 * @param mpeg4Width *Optional*. Video width
 * @param mpeg4Height *Optional*. Video height
 * @param mpeg4Duration *Optional*. Video duration in seconds
 * @param thumbnailUrl URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
 * @param thumbnailMimeType *Optional*. MIME type of the thumbnail, must be one of “image/jpeg”,
 * “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
 * @param title *Optional*. Title for the result
 * @param caption *Optional*. Caption of the MPEG-4 file to be sent, 0-1024 characters after
 * entities parsing
 * @param parseMode *Optional*. Mode for parsing entities in the caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the video
 * animation
 */
public data class InlineQueryResultMpeg4Gif(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * A valid URL for the MPEG4 file. File size must not exceed 1MB
     */
    @get:JsonProperty("mpeg4_url")
    @param:JsonProperty("mpeg4_url")
    public val mpeg4Url: String,
    /**
     * *Optional*. Video width
     */
    @get:JsonProperty("mpeg4_width")
    @param:JsonProperty("mpeg4_width")
    public val mpeg4Width: Int? = null,
    /**
     * *Optional*. Video height
     */
    @get:JsonProperty("mpeg4_height")
    @param:JsonProperty("mpeg4_height")
    public val mpeg4Height: Int? = null,
    /**
     * *Optional*. Video duration in seconds
     */
    @get:JsonProperty("mpeg4_duration")
    @param:JsonProperty("mpeg4_duration")
    public val mpeg4Duration: Int? = null,
    /**
     * URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String,
    /**
     * *Optional*. MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or
     * “video/mp4”. Defaults to “image/jpeg”
     */
    @get:JsonProperty("thumbnail_mime_type")
    @param:JsonProperty("thumbnail_mime_type")
    public val thumbnailMimeType: String? = null,
    /**
     * *Optional*. Title for the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the caption. See [formatting
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
     * *Optional*. Content of the message to be sent instead of the video animation
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "mpeg4_gif"
}
