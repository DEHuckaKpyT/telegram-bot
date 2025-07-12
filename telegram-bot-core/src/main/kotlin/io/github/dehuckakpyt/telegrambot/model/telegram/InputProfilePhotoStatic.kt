package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A static profile photo in the .JPG format.
 *
 * @see [InputProfilePhotoStatic] (https://core.telegram.org/bots/api/#inputprofilephotostatic)
 *
 * @author KScript
 *
 * @param photo The static profile photo. Profile photos can't be reused and can only be uploaded as
 * a new file, so you can pass “attach://\<file_attach_name\>” if the photo was uploaded using
 * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 */
public data class InputProfilePhotoStatic(
    /**
     * The static profile photo. Profile photos can't be reused and can only be uploaded as a new
     * file, so you can pass “attach://\<file_attach_name\>” if the photo was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: String,
) : InputProfilePhoto {
    @get:JsonProperty("type")
    override val type: String = "static"
}
