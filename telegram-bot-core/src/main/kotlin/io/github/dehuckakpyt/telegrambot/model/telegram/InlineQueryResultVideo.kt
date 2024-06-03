package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * Represents a link to a page containing an embedded video player or a video file. By default, this
 * video file will be sent by the user with an optional caption. Alternatively, you can use
 * *input_message_content* to send a message with the specified content instead of the video.
 *
 * If an InlineQueryResultVideo message contains an embedded video (e.g., YouTube), you **must**
 * replace its content using *input_message_content*.
 *
 * @see [InlineQueryResultVideo] (https://core.telegram.org/bots/api/#inlinequeryresultvideo)
 *
 * @author KScript
 *
 * @param id Unique identifier for this result, 1-64 bytes
 * @param videoUrl A valid URL for the embedded video player or video file
 * @param mimeType MIME type of the content of the video URL, “text/html” or “video/mp4”
 * @param thumbnailUrl URL of the thumbnail (JPEG only) for the video
 * @param title Title for the result
 * @param caption *Optional*. Caption of the video to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the video caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param videoWidth *Optional*. Video width
 * @param videoHeight *Optional*. Video height
 * @param videoDuration *Optional*. Video duration in seconds
 * @param description *Optional*. Short description of the result
 * @param replyMarkup *Optional*. [Inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) attached to the message
 * @param inputMessageContent *Optional*. Content of the message to be sent instead of the video.
 * This field is **required** if InlineQueryResultVideo is used to send an HTML-page as a result (e.g.,
 * a YouTube video).
 */
public data class InlineQueryResultVideo(
    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    override val id: String,
    /**
     * A valid URL for the embedded video player or video file
     */
    @get:JsonProperty("video_url")
    @param:JsonProperty("video_url")
    public val videoUrl: String,
    /**
     * MIME type of the content of the video URL, “text/html” or “video/mp4”
     */
    @get:JsonProperty("mime_type")
    @param:JsonProperty("mime_type")
    public val mimeType: String,
    /**
     * URL of the thumbnail (JPEG only) for the video
     */
    @get:JsonProperty("thumbnail_url")
    @param:JsonProperty("thumbnail_url")
    public val thumbnailUrl: String,
    /**
     * Title for the result
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * *Optional*. Caption of the video to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the video caption. See [formatting
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
     * *Optional*. Video width
     */
    @get:JsonProperty("video_width")
    @param:JsonProperty("video_width")
    public val videoWidth: Int? = null,
    /**
     * *Optional*. Video height
     */
    @get:JsonProperty("video_height")
    @param:JsonProperty("video_height")
    public val videoHeight: Int? = null,
    /**
     * *Optional*. Video duration in seconds
     */
    @get:JsonProperty("video_duration")
    @param:JsonProperty("video_duration")
    public val videoDuration: Int? = null,
    /**
     * *Optional*. Short description of the result
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String? = null,
    /**
     * *Optional*. [Inline keyboard](https://core.telegram.org/bots/features#inline-keyboards)
     * attached to the message
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    override val replyMarkup: InlineKeyboardMarkup? = null,
    /**
     * *Optional*. Content of the message to be sent instead of the video. This field is
     * **required** if InlineQueryResultVideo is used to send an HTML-page as a result (e.g., a YouTube
     * video).
     */
    @get:JsonProperty("input_message_content")
    @param:JsonProperty("input_message_content")
    public val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult {
    @get:JsonProperty("type")
    override val type: String = "video"
}
