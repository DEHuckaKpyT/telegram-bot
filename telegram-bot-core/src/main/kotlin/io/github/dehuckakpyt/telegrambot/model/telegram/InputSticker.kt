package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.String
import kotlin.collections.List

/**
 * This object describes a sticker to be added to a sticker set.
 *
 * @see [InputSticker] (https://core.telegram.org/bots/api/#inputsticker)
 *
 * @author KScript
 *
 * @param sticker The added sticker. Pass a *file_id* as a String to send a file that already exists
 * on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet,
 * upload a new one using multipart/form-data, or pass “attach://\<file_attach_name\>” to upload a new
 * one using multipart/form-data under \<file_attach_name\> name. Animated and video stickers can't be
 * uploaded via HTTP URL. [More information on Sending Files
 * ](https://core.telegram.org/bots/api/#sending-files)
 * @param format Format of the added sticker, must be one of “static” for a **.WEBP** or **.PNG**
 * image, “animated” for a **.TGS** animation, “video” for a **.WEBM** video
 * @param emojiList List of 1-20 emoji associated with the sticker
 * @param maskPosition *Optional*. Position where the mask should be placed on faces. For “mask”
 * stickers only.
 * @param keywords *Optional*. List of 0-20 search keywords for the sticker with total length of up
 * to 64 characters. For “regular” and “custom_emoji” stickers only.
 */
public data class InputSticker(
    /**
     * The added sticker. Pass a *file_id* as a String to send a file that already exists on the
     * Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet,
     * upload a new one using multipart/form-data, or pass “attach://\<file_attach_name\>” to upload a
     * new one using multipart/form-data under \<file_attach_name\> name. Animated and video stickers
     * can't be uploaded via HTTP URL. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Input,
    /**
     * Format of the added sticker, must be one of “static” for a **.WEBP** or **.PNG** image,
     * “animated” for a **.TGS** animation, “video” for a **.WEBM** video
     */
    @get:JsonProperty("format")
    @param:JsonProperty("format")
    public val format: String,
    /**
     * List of 1-20 emoji associated with the sticker
     */
    @get:JsonProperty("emoji_list")
    @param:JsonProperty("emoji_list")
    public val emojiList: List<String>,
    /**
     * *Optional*. Position where the mask should be placed on faces. For “mask” stickers only.
     */
    @get:JsonProperty("mask_position")
    @param:JsonProperty("mask_position")
    public val maskPosition: MaskPosition? = null,
    /**
     * *Optional*. List of 0-20 search keywords for the sticker with total length of up to 64
     * characters. For “regular” and “custom_emoji” stickers only.
     */
    @get:JsonProperty("keywords")
    @param:JsonProperty("keywords")
    public val keywords: List<String>? = null,
) {
    public constructor(
        sticker: String,
        format: String,
        emojiList: List<String>,
        maskPosition: MaskPosition? = null,
        keywords: List<String>? = null,
    ) : this(StringInput(sticker), format, emojiList, maskPosition, keywords)
}
