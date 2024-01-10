package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class Sticker(
    @get:JsonProperty("file_id") @param:JsonProperty("file_id") val fileId: String,
    @get:JsonProperty("file_unique_id") @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @get:JsonProperty("width") @param:JsonProperty("width") val width: Int,
    @get:JsonProperty("height") @param:JsonProperty("height") val height: Int,
    @get:JsonProperty("is_animated") @param:JsonProperty("is_animated") val isAnimated: Boolean,
    @get:JsonProperty("is_video") @param:JsonProperty("is_video") val isVideo: Boolean,
    @get:JsonProperty("thumb") @param:JsonProperty("thumb") val thumb: PhotoSize? = null,
    @get:JsonProperty("emoji") @param:JsonProperty("emoji") val emoji: String? = null,
    @get:JsonProperty("set_name") @param:JsonProperty("set_name") val setName: String? = null,
    @get:JsonProperty("premium_animation") @param:JsonProperty("premium_animation") val premiumAnimation: File? = null,
    @get:JsonProperty("mask_position") @param:JsonProperty("mask_position") val maskPosition: MaskPosition? = null,
    @get:JsonProperty("custom_emoji_id") @param:JsonProperty("custom_emoji_id") val customEmojiId: String? = null,
    @get:JsonProperty("file_size") @param:JsonProperty("file_size") val fileSize: Int? = null,
)

public data class StickerSet(
    @get:JsonProperty("name") @param:JsonProperty("name") val name: String,
    @get:JsonProperty("title") @param:JsonProperty("title") val title: String,
    @get:JsonProperty("sticker_type") @param:JsonProperty("sticker_type") val stickerType: String,
    @get:JsonProperty("is_animated") @param:JsonProperty("is_animated") val isAnimated: Boolean,
    @get:JsonProperty("is_video") @param:JsonProperty("is_video") val isVideo: Boolean,
    @get:JsonProperty("stickers") @param:JsonProperty("stickers") val stickers: List<Sticker>,
    @get:JsonProperty("thumb") @param:JsonProperty("thumb") val thumb: PhotoSize? = null,
)

public data class MaskPosition(
    @get:JsonProperty("point") @param:JsonProperty("point") val point: String,
    @get:JsonProperty("x_shift") @param:JsonProperty("x_shift") val xShift: Float,
    @get:JsonProperty("y_shift") @param:JsonProperty("y_shift") val yShift: Float,
    @get:JsonProperty("scale") @param:JsonProperty("scale") val scale: Float,
)

public data class InputSticker private constructor(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonIgnore val stickerContent: NamedContent?,
    @get:JsonProperty("emoji_list") val emojiList: Iterable<String>,
    @get:JsonProperty("mask_position") val maskPosition: MaskPosition?,
    @get:JsonProperty("keywords") val keywords: Iterable<String>?,
) {

    public constructor(
        sticker: String,
        emojiList: Iterable<String>,
        maskPosition: MaskPosition? = null,
        keywords: Iterable<String>? = null,
    ) : this(sticker, null, emojiList, maskPosition, keywords)

    public constructor(
        sticker: NamedContent,
        emojiList: Iterable<String>,
        maskPosition: MaskPosition? = null,
        keywords: Iterable<String>? = null,
    ) : this("attach://${sticker.fileName}", sticker, emojiList, maskPosition, keywords)
}