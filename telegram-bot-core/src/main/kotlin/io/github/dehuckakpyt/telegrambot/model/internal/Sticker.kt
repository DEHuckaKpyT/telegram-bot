package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class GetCustomEmojiStickers(
    @get:JsonProperty("custom_emoji_ids") val customEmojiIds: List<String>
)

internal class SetStickerPositionInSet(
    @get:JsonProperty("sticker") val sticker: String,
    @get:JsonProperty("position") val position: Int
)

internal class DeleteStickerFromSet(
    @get:JsonProperty("sticker") val sticker: String
)