package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonIgnore
import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.String

/**
 * The paid media to send is a live photo.
 *
 * @see [InputPaidMediaLivePhoto] (https://core.telegram.org/bots/api/#inputpaidmedialivephoto)
 *
 * @author KScript
 *
 * @param media Video of the live photo to send. Pass a file_id to send a file that exists on the
 * Telegram servers (recommended) or pass “attach://\<file_attach_name\>” to upload a new one using
 * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files). Sending live photos by a URL is currently
 * unsupported.
 * @param photo The static photo to send. Pass a file_id to send a file that exists on the Telegram
 * servers (recommended) or pass “attach://\<file_attach_name\>” to upload a new one using
 * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files). Sending live photos by a URL is currently
 * unsupported.
 */
public data class InputPaidMediaLivePhoto(
    /**
     * Video of the live photo to send. Pass a file_id to send a file that exists on the Telegram
     * servers (recommended) or pass “attach://\<file_attach_name\>” to upload a new one using
     * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files). Sending live photos by a URL is currently
     * unsupported.
     */
    @get:JsonProperty("media")
    @param:JsonProperty("media")
    override val media: Input,
    /**
     * The static photo to send. Pass a file_id to send a file that exists on the Telegram servers
     * (recommended) or pass “attach://\<file_attach_name\>” to upload a new one using
     * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files). Sending live photos by a URL is currently
     * unsupported.
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    override val photo: Input,
) : InputPaidMedia {
    @get:JsonProperty("type")
    override val type: String = "live_photo"

    @get:JsonIgnore
    override val thumbnail: Input? = null

    @get:JsonIgnore
    override val cover: Input? = null

    public constructor(media: String, photo: String) : this(StringInput(media), StringInput(photo))
}
