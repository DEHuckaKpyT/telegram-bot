package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonIgnore
import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.String

/**
 * Represents a sticker file to be sent.
 *
 * @see [InputMediaSticker] (https://core.telegram.org/bots/api/#inputmediasticker)
 *
 * @author KScript
 *
 * @param media File to send. Pass a file_id to send a file that exists on the Telegram servers
 * (recommended), pass an HTTP URL for Telegram to get a .WEBP sticker from the Internet, or pass
 * “attach://\<file_attach_name\>” to upload a new .WEBP, .TGS, or .WEBM sticker using
 * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param emoji *Optional*. Emoji associated with the sticker; only for just uploaded stickers
 */
public data class InputMediaSticker(
    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers
     * (recommended), pass an HTTP URL for Telegram to get a .WEBP sticker from the Internet, or pass
     * “attach://\<file_attach_name\>” to upload a new .WEBP, .TGS, or .WEBM sticker using
     * multipart/form-data under \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("media")
    @param:JsonProperty("media")
    override val media: Input,
    /**
     * *Optional*. Emoji associated with the sticker; only for just uploaded stickers
     */
    @get:JsonProperty("emoji")
    @param:JsonProperty("emoji")
    public val emoji: String? = null,
) : InputPollOptionMedia {
    @get:JsonProperty("type")
    override val type: String = "sticker"

    @get:JsonIgnore
    override val thumbnail: Input? = null

    @get:JsonIgnore
    override val photo: Input? = null

    @get:JsonIgnore
    override val cover: Input? = null

    public constructor(media: String, emoji: String? = null) : this(StringInput(media), emoji)
}
