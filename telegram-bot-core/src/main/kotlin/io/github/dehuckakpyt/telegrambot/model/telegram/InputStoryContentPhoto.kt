package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Describes a photo to post as a story.
 *
 * @see [InputStoryContentPhoto] (https://core.telegram.org/bots/api/#inputstorycontentphoto)
 *
 * @author KScript
 *
 * @param photo The photo to post as a story. The photo must be of the size 1080x1920 and must not
 * exceed 10 MB. The photo can't be reused and can only be uploaded as a new file, so you can pass
 * “attach://\<file_attach_name\>” if the photo was uploaded using multipart/form-data under
 * \<file_attach_name\>. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 */
public data class InputStoryContentPhoto(
    /**
     * The photo to post as a story. The photo must be of the size 1080x1920 and must not exceed 10
     * MB. The photo can't be reused and can only be uploaded as a new file, so you can pass
     * “attach://\<file_attach_name\>” if the photo was uploaded using multipart/form-data under
     * \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: String,
) : InputStoryContent {
    @get:JsonProperty("type")
    override val type: String = "photo"
}
