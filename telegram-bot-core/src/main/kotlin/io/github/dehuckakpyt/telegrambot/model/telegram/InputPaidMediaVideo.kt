package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * The paid media to send is a video.
 *
 * @see [InputPaidMediaVideo] (https://core.telegram.org/bots/api/#inputpaidmediavideo)
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
 * @param cover *Optional*. Cover for the video in the message. Pass a file_id to send a file that
 * exists on the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the
 * Internet, or pass “attach://\<file_attach_name\>” to upload a new one using multipart/form-data
 * under \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param startTimestamp *Optional*. Start timestamp for the video in the message
 * @param width *Optional*. Video width
 * @param height *Optional*. Video height
 * @param duration *Optional*. Video duration in seconds
 * @param supportsStreaming *Optional*. Pass *True* if the uploaded video is suitable for streaming
 */
public data class InputPaidMediaVideo(
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
     * *Optional*. Cover for the video in the message. Pass a file_id to send a file that exists on
     * the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the
     * Internet, or pass “attach://\<file_attach_name\>” to upload a new one using multipart/form-data
     * under \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("cover")
    @param:JsonProperty("cover")
    override val cover: Input? = null,
    /**
     * *Optional*. Start timestamp for the video in the message
     */
    @get:JsonProperty("start_timestamp")
    @param:JsonProperty("start_timestamp")
    public val startTimestamp: Int? = null,
    /**
     * *Optional*. Video width
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int? = null,
    /**
     * *Optional*. Video height
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int? = null,
    /**
     * *Optional*. Video duration in seconds
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int? = null,
    /**
     * *Optional*. Pass *True* if the uploaded video is suitable for streaming
     */
    @get:JsonProperty("supports_streaming")
    @param:JsonProperty("supports_streaming")
    public val supportsStreaming: Boolean? = null,
) : InputPaidMedia {
    @get:JsonProperty("type")
    override val type: String = "video"

    public constructor(
        media: String,
        thumbnail: Input? = null,
        cover: Input? = null,
        startTimestamp: Int? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        supportsStreaming: Boolean? = null,
    ) : this(StringInput(media), thumbnail, cover, startTimestamp, width, height, duration,
            supportsStreaming)
}
