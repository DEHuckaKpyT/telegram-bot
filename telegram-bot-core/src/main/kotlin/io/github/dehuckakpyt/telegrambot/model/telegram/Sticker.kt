package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a sticker.
 *
 * @see [Sticker] (https://core.telegram.org/bots/api/#sticker)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param type Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. The type of
 * the sticker is independent from its format, which is determined by the fields *is_animated* and
 * *is_video*.
 * @param width Sticker width
 * @param height Sticker height
 * @param isAnimated *True*, if the sticker is
 * [animated](https://telegram.org/blog/animated-stickers)
 * @param isVideo *True*, if the sticker is a [video
 * sticker](https://telegram.org/blog/video-stickers-better-reactions)
 * @param thumbnail *Optional*. Sticker thumbnail in the .WEBP or .JPG format
 * @param emoji *Optional*. Emoji associated with the sticker
 * @param setName *Optional*. Name of the sticker set to which the sticker belongs
 * @param premiumAnimation *Optional*. For premium regular stickers, premium animation for the
 * sticker
 * @param maskPosition *Optional*. For mask stickers, the position where the mask should be placed
 * @param customEmojiId *Optional*. For custom emoji stickers, unique identifier of the custom emoji
 * @param needsRepainting *Optional*. *True*, if the sticker must be repainted to a text color in
 * messages, the color of the Telegram Premium badge in emoji status, white color on chat photos, or
 * another appropriate color in other places
 * @param fileSize *Optional*. File size in bytes
 */
public data class Sticker(
    /**
     * Identifier for this file, which can be used to download or reuse the file
     */
    @get:JsonProperty("file_id")
    @param:JsonProperty("file_id")
    public val fileId: String,
    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different
     * bots. Can't be used to download or reuse the file.
     */
    @get:JsonProperty("file_unique_id")
    @param:JsonProperty("file_unique_id")
    public val fileUniqueId: String,
    /**
     * Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. The type of the
     * sticker is independent from its format, which is determined by the fields *is_animated* and
     * *is_video*.
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * Sticker width
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int,
    /**
     * Sticker height
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int,
    /**
     * *True*, if the sticker is [animated](https://telegram.org/blog/animated-stickers)
     */
    @get:JsonProperty("is_animated")
    @param:JsonProperty("is_animated")
    public val isAnimated: Boolean,
    /**
     * *True*, if the sticker is a [video
     * sticker](https://telegram.org/blog/video-stickers-better-reactions)
     */
    @get:JsonProperty("is_video")
    @param:JsonProperty("is_video")
    public val isVideo: Boolean,
    /**
     * *Optional*. Sticker thumbnail in the .WEBP or .JPG format
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    public val thumbnail: PhotoSize? = null,
    /**
     * *Optional*. Emoji associated with the sticker
     */
    @get:JsonProperty("emoji")
    @param:JsonProperty("emoji")
    public val emoji: String? = null,
    /**
     * *Optional*. Name of the sticker set to which the sticker belongs
     */
    @get:JsonProperty("set_name")
    @param:JsonProperty("set_name")
    public val setName: String? = null,
    /**
     * *Optional*. For premium regular stickers, premium animation for the sticker
     */
    @get:JsonProperty("premium_animation")
    @param:JsonProperty("premium_animation")
    public val premiumAnimation: File? = null,
    /**
     * *Optional*. For mask stickers, the position where the mask should be placed
     */
    @get:JsonProperty("mask_position")
    @param:JsonProperty("mask_position")
    public val maskPosition: MaskPosition? = null,
    /**
     * *Optional*. For custom emoji stickers, unique identifier of the custom emoji
     */
    @get:JsonProperty("custom_emoji_id")
    @param:JsonProperty("custom_emoji_id")
    public val customEmojiId: String? = null,
    /**
     * *Optional*. *True*, if the sticker must be repainted to a text color in messages, the color
     * of the Telegram Premium badge in emoji status, white color on chat photos, or another
     * appropriate color in other places
     */
    @get:JsonProperty("needs_repainting")
    @param:JsonProperty("needs_repainting")
    public val needsRepainting: Boolean? = null,
    /**
     * *Optional*. File size in bytes
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Int? = null,
)
