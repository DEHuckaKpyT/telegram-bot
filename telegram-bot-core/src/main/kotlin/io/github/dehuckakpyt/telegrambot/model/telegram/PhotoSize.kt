package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object represents one size of a photo or a
 * [file](https://core.telegram.org/bots/api/#document) /
 * [sticker](https://core.telegram.org/bots/api/#sticker) thumbnail.
 *
 * @see [PhotoSize] (https://core.telegram.org/bots/api/#photosize)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param width Photo width
 * @param height Photo height
 * @param fileSize *Optional*. File size in bytes
 */
public data class PhotoSize(
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
     * Photo width
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int,
    /**
     * Photo height
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int,
    /**
     * *Optional*. File size in bytes
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Int? = null,
)
