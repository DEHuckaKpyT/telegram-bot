package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object represents a general file (as opposed to
 * [photos](https://core.telegram.org/bots/api/#photosize), [voice
 * messages](https://core.telegram.org/bots/api/#voice) and [audio
 * files](https://core.telegram.org/bots/api/#audio)).
 *
 * @see [Document] (https://core.telegram.org/bots/api/#document)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param thumbnail *Optional*. Document thumbnail as defined by sender
 * @param fileName *Optional*. Original filename as defined by sender
 * @param mimeType *Optional*. MIME type of the file as defined by sender
 * @param fileSize *Optional*. File size in bytes. It can be bigger than 2^31 and some programming
 * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant
 * bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
 */
public data class Document(
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
     * *Optional*. Document thumbnail as defined by sender
     */
    @get:JsonProperty("thumbnail")
    @param:JsonProperty("thumbnail")
    public val thumbnail: PhotoSize? = null,
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
)
