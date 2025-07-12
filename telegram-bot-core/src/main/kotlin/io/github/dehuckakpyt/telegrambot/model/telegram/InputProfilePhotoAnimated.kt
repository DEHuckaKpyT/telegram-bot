package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Double
import kotlin.String

/**
 * An animated profile photo in the MPEG4 format.
 *
 * @see [InputProfilePhotoAnimated] (https://core.telegram.org/bots/api/#inputprofilephotoanimated)
 *
 * @author KScript
 *
 * @param animation The animated profile photo. Profile photos can't be reused and can only be
 * uploaded as a new file, so you can pass “attach://\<file_attach_name\>” if the photo was uploaded
 * using multipart/form-data under \<file_attach_name\>. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param mainFrameTimestamp *Optional*. Timestamp in seconds of the frame that will be used as the
 * static profile photo. Defaults to 0.0.
 */
public data class InputProfilePhotoAnimated(
    /**
     * The animated profile photo. Profile photos can't be reused and can only be uploaded as a new
     * file, so you can pass “attach://\<file_attach_name\>” if the photo was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: String,
    /**
     * *Optional*. Timestamp in seconds of the frame that will be used as the static profile photo.
     * Defaults to 0.0.
     */
    @get:JsonProperty("main_frame_timestamp")
    @param:JsonProperty("main_frame_timestamp")
    public val mainFrameTimestamp: Double? = null,
) : InputProfilePhoto {
    @get:JsonProperty("type")
    override val type: String = "animated"
}
