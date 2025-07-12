package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Double
import kotlin.String

/**
 * Describes a video to post as a story.
 *
 * @see [InputStoryContentVideo] (https://core.telegram.org/bots/api/#inputstorycontentvideo)
 *
 * @author KScript
 *
 * @param video The video to post as a story. The video must be of the size 720x1280, streamable,
 * encoded with H.265 codec, with key frames added each second in the MPEG4 format, and must not exceed
 * 30 MB. The video can't be reused and can only be uploaded as a new file, so you can pass
 * “attach://\<file_attach_name\>” if the video was uploaded using multipart/form-data under
 * \<file_attach_name\>. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param duration *Optional*. Precise duration of the video in seconds; 0-60
 * @param coverFrameTimestamp *Optional*. Timestamp in seconds of the frame that will be used as the
 * static cover for the story. Defaults to 0.0.
 * @param isAnimation *Optional*. Pass *True* if the video has no sound
 */
public data class InputStoryContentVideo(
    /**
     * The video to post as a story. The video must be of the size 720x1280, streamable, encoded
     * with H.265 codec, with key frames added each second in the MPEG4 format, and must not exceed 30
     * MB. The video can't be reused and can only be uploaded as a new file, so you can pass
     * “attach://\<file_attach_name\>” if the video was uploaded using multipart/form-data under
     * \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("video")
    @param:JsonProperty("video")
    public val video: String,
    /**
     * *Optional*. Precise duration of the video in seconds; 0-60
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Double? = null,
    /**
     * *Optional*. Timestamp in seconds of the frame that will be used as the static cover for the
     * story. Defaults to 0.0.
     */
    @get:JsonProperty("cover_frame_timestamp")
    @param:JsonProperty("cover_frame_timestamp")
    public val coverFrameTimestamp: Double? = null,
    /**
     * *Optional*. Pass *True* if the video has no sound
     */
    @get:JsonProperty("is_animation")
    @param:JsonProperty("is_animation")
    public val isAnimation: Boolean? = null,
) : InputStoryContent {
    @get:JsonProperty("type")
    override val type: String = "video"
}
