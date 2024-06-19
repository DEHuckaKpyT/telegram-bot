package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent.
 *
 * @see [InputMediaAnimation] (https://core.telegram.org/bots/api/#inputmediaanimation)
 *
 * @author KScript
 *
 * @param media File to send. Pass a file_id to send a file that exists on the Telegram servers
 * (recommended), pass an HTTP URL for Telegram to get a file from the Internet, or pass
 * “attach://\<file_attach_name\>” to upload a new one using multipart/form-data under
 * \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param thumbnail *Optional*. Thumbnail of the file sent; can be ignored if thumbnail generation
 * for the file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB
 * in size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
 * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so you
 * can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using multipart/form-data
 * under \<file_attach_name\>. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param caption *Optional*. Caption of the animation to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the animation caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param width *Optional*. Animation width
 * @param height *Optional*. Animation height
 * @param duration *Optional*. Animation duration in seconds
 * @param hasSpoiler *Optional*. Pass *True* if the animation needs to be covered with a spoiler
 * animation
 */
public data class InputMediaAnimation(
    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers
     * (recommended), pass an HTTP URL for Telegram to get a file from the Internet, or pass
     * “attach://\<file_attach_name\>” to upload a new one using multipart/form-data under
     * \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("media")
    @param:JsonProperty("media")
    override val media: Input,
    /**
     * *Optional*. Thumbnail of the file sent; can be ignored if thumbnail generation for the file
     * is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in size. A
     * thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded using
     * multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so you
     * can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using multipart/form-data
     * under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    override val thumbnail: Input? = null,
    /**
     * *Optional*. Caption of the animation to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    override val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the animation caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     */
    @get:JsonProperty("parse_mode")
    @param:JsonProperty("parse_mode")
    override val parseMode: String? = null,
    /**
     * *Optional*. List of special entities that appear in the caption, which can be specified
     * instead of *parse_mode*
     */
    @get:JsonProperty("caption_entities")
    @param:JsonProperty("caption_entities")
    override val captionEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. Pass *True*, if the caption must be shown above the message media
     */
    @get:JsonProperty("show_caption_above_media")
    @param:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    /**
     * *Optional*. Animation width
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int? = null,
    /**
     * *Optional*. Animation height
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int? = null,
    /**
     * *Optional*. Animation duration in seconds
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int? = null,
    /**
     * *Optional*. Pass *True* if the animation needs to be covered with a spoiler animation
     */
    @get:JsonProperty("has_spoiler")
    @param:JsonProperty("has_spoiler")
    public val hasSpoiler: Boolean? = null,
) : InputMedia {
    @get:JsonProperty("type")
    override val type: String = "animation"

    public constructor(
        media: String,
        thumbnail: Input? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        hasSpoiler: Boolean? = null,
    ) : this(StringInput(media), thumbnail, caption, parseMode, captionEntities,
            showCaptionAboveMedia, width, height, duration, hasSpoiler)
}
