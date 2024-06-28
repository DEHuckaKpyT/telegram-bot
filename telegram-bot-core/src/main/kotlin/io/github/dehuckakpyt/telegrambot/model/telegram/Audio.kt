package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * This object represents an audio file to be treated as music by the Telegram clients.
 *
 * @see [Audio] (https://core.telegram.org/bots/api/#audio)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param duration Duration of the audio in seconds as defined by sender
 * @param performer *Optional*. Performer of the audio as defined by sender or by audio tags
 * @param title *Optional*. Title of the audio as defined by sender or by audio tags
 * @param fileName *Optional*. Original filename as defined by sender
 * @param mimeType *Optional*. MIME type of the file as defined by sender
 * @param fileSize *Optional*. File size in bytes. It can be bigger than 2^31 and some programming
 * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant
 * bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
 * @param thumbnail *Optional*. Thumbnail of the album cover to which the music file belongs
 */
public data class Audio(
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
     * Duration of the audio in seconds as defined by sender
     */
    @get:JsonProperty("duration")
    @param:JsonProperty("duration")
    public val duration: Int,
    /**
     * *Optional*. Performer of the audio as defined by sender or by audio tags
     */
    @get:JsonProperty("performer")
    @param:JsonProperty("performer")
    public val performer: String? = null,
    /**
     * *Optional*. Title of the audio as defined by sender or by audio tags
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Original filename as defined by sender
     */
    @get:JsonProperty("file_name")
    @param:JsonProperty("file_name")
    public val fileName: String? = null,
    /**
     * *Optional*. MIME type of the file as defined by sender
     */
    @get:JsonProperty("mime_type")
    @param:JsonProperty("mime_type")
    public val mimeType: String? = null,
    /**
     * *Optional*. File size in bytes. It can be bigger than 2^31 and some programming languages may
     * have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this value.
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Long? = null,
    /**
     * *Optional*. Thumbnail of the album cover to which the music file belongs
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    public val thumbnail: PhotoSize? = null,
)
