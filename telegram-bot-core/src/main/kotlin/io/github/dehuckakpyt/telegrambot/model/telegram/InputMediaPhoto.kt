package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonIgnore
import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * Created on 02.06.2024.
 *
 * Represents a photo to be sent.
 *
 * @see [InputMediaPhoto] (https://core.telegram.org/bots/api/#inputmediaphoto)
 *
 * @author KScript
 *
 * @param media File to send. Pass a file_id to send a file that exists on the Telegram servers
 * (recommended), pass an HTTP URL for Telegram to get a file from the Internet, or pass
 * “attach://\<file_attach_name\>” to upload a new one using multipart/form-data under
 * \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param caption *Optional*. Caption of the photo to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the photo caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param showCaptionAboveMedia *Optional*. Pass *True*, if the caption must be shown above the
 * message media
 * @param hasSpoiler *Optional*. Pass *True* if the photo needs to be covered with a spoiler
 * animation
 */
public data class InputMediaPhoto(
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
     * *Optional*. Caption of the photo to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    override val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the photo caption. See [formatting
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
     * *Optional*. Pass *True* if the photo needs to be covered with a spoiler animation
     */
    @get:JsonProperty("has_spoiler")
    @param:JsonProperty("has_spoiler")
    public val hasSpoiler: Boolean? = null,
) : InputMedia {
    @get:JsonProperty("type")
    override val type: String = "photo"

    @get:JsonIgnore
    override val thumbnail: Input? = null

    public constructor(
        media: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
    ) : this(StringInput(media), caption, parseMode, captionEntities, showCaptionAboveMedia,
            hasSpoiler)
}
