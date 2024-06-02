package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a [video message](https://telegram.org/blog/video-messages-and-telescope)
 * (available in Telegram apps as of [v.4.0](https://telegram.org/blog/video-messages-and-telescope)).
 *
 * @see [VideoNote] (https://core.telegram.org/bots/api/#videonote)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param length Video width and height (diameter of the video message) as defined by sender
 * @param duration Duration of the video in seconds as defined by sender
 * @param thumbnail *Optional*. Video thumbnail
 * @param fileSize *Optional*. File size in bytes
 */
public data class VideoNote(
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
     * Video width and height (diameter of the video message) as defined by sender
     */
    @get:JsonProperty("length")
    @param:JsonProperty("length")
    public val length: Int,
    /**
     * Duration of the video in seconds as defined by sender
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int,
    /**
     * *Optional*. Video thumbnail
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    public val thumbnail: PhotoSize? = null,
    /**
     * *Optional*. File size in bytes
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Int? = null,
)
