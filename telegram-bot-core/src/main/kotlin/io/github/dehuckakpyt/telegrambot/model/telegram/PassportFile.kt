package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a file uploaded to Telegram Passport. Currently all Telegram Passport
 * files are in JPEG format when decrypted and don't exceed 10MB.
 *
 * @see [PassportFile] (https://core.telegram.org/bots/api/#passportfile)
 *
 * @author KScript
 *
 * @param fileId Identifier for this file, which can be used to download or reuse the file
 * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time
 * and for different bots. Can't be used to download or reuse the file.
 * @param fileSize File size in bytes
 * @param fileDate Unix time when the file was uploaded
 */
public data class PassportFile(
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
     * File size in bytes
     */
    @get:JsonProperty("file_size")
    @param:JsonProperty("file_size")
    public val fileSize: Int,
    /**
     * Unix time when the file was uploaded
     */
    @get:JsonProperty("file_date")
    @param:JsonProperty("file_date")
    public val fileDate: Long,
)
