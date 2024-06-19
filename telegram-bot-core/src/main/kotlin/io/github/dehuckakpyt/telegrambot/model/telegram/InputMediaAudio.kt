package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * Represents an audio file to be treated as music to be sent.
 *
 * @see [InputMediaAudio] (https://core.telegram.org/bots/api/#inputmediaaudio)
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
 * @param caption *Optional*. Caption of the audio to be sent, 0-1024 characters after entities
 * parsing
 * @param parseMode *Optional*. Mode for parsing entities in the audio caption. See [formatting
 * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
 * @param captionEntities *Optional*. List of special entities that appear in the caption, which can
 * be specified instead of *parse_mode*
 * @param duration *Optional*. Duration of the audio in seconds
 * @param performer *Optional*. Performer of the audio
 * @param title *Optional*. Title of the audio
 */
public data class InputMediaAudio(
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
     * *Optional*. Caption of the audio to be sent, 0-1024 characters after entities parsing
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    override val caption: String? = null,
    /**
     * *Optional*. Mode for parsing entities in the audio caption. See [formatting
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
     * *Optional*. Duration of the audio in seconds
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int? = null,
    /**
     * *Optional*. Performer of the audio
     */
    @get:JsonProperty("performer")
    @param:JsonProperty("performer")
    public val performer: String? = null,
    /**
     * *Optional*. Title of the audio
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
) : InputMedia {
    @get:JsonProperty("type")
    override val type: String = "audio"

    public constructor(
        media: String,
        thumbnail: Input? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
    ) : this(StringInput(media), thumbnail, caption, parseMode, captionEntities, duration,
            performer, title)
}
