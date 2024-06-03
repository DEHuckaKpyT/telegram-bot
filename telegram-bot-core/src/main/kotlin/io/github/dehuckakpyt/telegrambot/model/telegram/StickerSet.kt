package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * This object represents a sticker set.
 *
 * @see [StickerSet] (https://core.telegram.org/bots/api/#stickerset)
 *
 * @author KScript
 *
 * @param name Sticker set name
 * @param title Sticker set title
 * @param stickerType Type of stickers in the set, currently one of “regular”, “mask”,
 * “custom_emoji”
 * @param stickers List of all set stickers
 * @param thumbnail *Optional*. Sticker set thumbnail in the .WEBP, .TGS, or .WEBM format
 */
public data class StickerSet(
    /**
     * Sticker set name
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String,
    /**
     * Sticker set title
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String,
    /**
     * Type of stickers in the set, currently one of “regular”, “mask”, “custom_emoji”
     */
    @get:JsonProperty("sticker_type")
    @param:JsonProperty("sticker_type")
    public val stickerType: String,
    /**
     * List of all set stickers
     */
    @get:JsonProperty("stickers")
    @param:JsonProperty("stickers")
    public val stickers: List<Sticker>,
    /**
     * *Optional*. Sticker set thumbnail in the .WEBP, .TGS, or .WEBM format
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    public val thumbnail: PhotoSize? = null,
)
