package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.MaskPosition


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class GetCustomEmojiStickers(
    @get:JsonProperty("custom_emoji_ids") val customEmojiIds: List<String>,
)

internal class SetStickerPositionInSet(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("position") val position: Int,
)

internal class DeleteStickerFromSet(
    @get:JsonProperty("sticker") val sticker: String,
)

internal class SetStickerEmojiList(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("emoji_list") val emojiList: Iterable<String>,
)

internal class SetStickerKeywords(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("keywords") val keywords: Iterable<String>? = null,
)

internal class SetStickerMaskPosition(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("mask_position") val maskPosition: MaskPosition? = null,
)

internal class SetStickerSetTitle(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("title") val title: String,
)

internal class SetCustomEmojiStickerSetThumbnail(
    @get:JsonProperty("name") val name: String,
    @get:JsonProperty("custom_emoji_id") val customEmojiId: String? = null,
)

internal class DeleteStickerSet(
    @get:JsonProperty("name") val name: String,
)