package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object represents a video file of a specific quality.
 *
 * @see [VideoQuality] (https://core.telegram.org/bots/api/#videoquality)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param width Video width
 * @param height Video height
 * @param codec Codec that was used to encode the video, for example, “h264”, “h265”, or “av01”
 * @param fileSize *Optional*. File size in bytes. It can be bigger than 2^31 and some programming
 * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant
 * bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
 */
public data class VideoQuality(
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
     * Video width
     */
    @get:JsonProperty("width")
    @param:JsonProperty("width")
    public val width: Int,
    /**
     * Video height
     */
    @get:JsonProperty("height")
    @param:JsonProperty("height")
    public val height: Int,
    /**
     * Codec that was used to encode the video, for example, “h264”, “h265”, or “av01”
     */
    @get:JsonProperty("codec")
    @param:JsonProperty("codec")
    public val codec: String,
    /**
     * *Optional*. File size in bytes. It can be bigger than 2^31 and some programming languages may
     * have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this value.
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Long? = null,
)
