package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * This object represents a file ready to be downloaded. The file can be downloaded via the link
 * `https://api.telegram.org/file/bot<token>/<file_path>`. It is guaranteed that the link will be valid
 * for at least 1 hour. When the link expires, a new one can be requested by calling
 * [getFile](https://core.telegram.org/bots/api/#getfile).
 *
 * The maximum file size to download is 20 MB
 *
 * @see [File] (https://core.telegram.org/bots/api/#file)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param fileSize *Optional*. File size in bytes. It can be bigger than 2^31 and some programming
 * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant
 * bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
 * @param filePath *Optional*. File path. Use `https://api.telegram.org/file/bot<token>/<file_path>`
 * to get the file.
 */
public data class File(
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
     * *Optional*. File size in bytes. It can be bigger than 2^31 and some programming languages may
     * have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this value.
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Long? = null,
    /**
     * *Optional*. File path. Use `https://api.telegram.org/file/bot<token>/<file_path>` to get the
     * file.
     */
    @get:JsonProperty("file_path")
    @param:JsonProperty("file_path")
    public val filePath: String? = null,
)
