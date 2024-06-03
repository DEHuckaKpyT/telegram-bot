package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object represents a chat photo.
 *
 * @see [ChatPhoto] (https://core.telegram.org/bots/api/#chatphoto)
 *
 * @author KScript
 *
 * @param smallFileId File identifier of small (160x160) chat photo. This file_id can be used only
 * for photo download and only for as long as the photo is not changed.
 * @param smallFileUniqueId Unique file identifier of small (160x160) chat photo, which is supposed
 * to be the same over time and for different bots. Can't be used to download or reuse the file.
 * @param bigFileId File identifier of big (640x640) chat photo. This file_id can be used only for
 * photo download and only for as long as the photo is not changed.
 * @param bigFileUniqueId Unique file identifier of big (640x640) chat photo, which is supposed to
 * be the same over time and for different bots. Can't be used to download or reuse the file.
 */
public data class ChatPhoto(
    /**
     * File identifier of small (160x160) chat photo. This file_id can be used only for photo
     * download and only for as long as the photo is not changed.
     */
    @get:JsonProperty("small_file_id")
    @param:JsonProperty("small_file_id")
    public val smallFileId: String,
    /**
     * Unique file identifier of small (160x160) chat photo, which is supposed to be the same over
     * time and for different bots. Can't be used to download or reuse the file.
     */
    @get:JsonProperty("small_file_unique_id")
    @param:JsonProperty("small_file_unique_id")
    public val smallFileUniqueId: String,
    /**
     * File identifier of big (640x640) chat photo. This file_id can be used only for photo download
     * and only for as long as the photo is not changed.
     */
    @get:JsonProperty("big_file_id")
    @param:JsonProperty("big_file_id")
    public val bigFileId: String,
    /**
     * Unique file identifier of big (640x640) chat photo, which is supposed to be the same over
     * time and for different bots. Can't be used to download or reuse the file.
     */
    @get:JsonProperty("big_file_unique_id")
    @param:JsonProperty("big_file_unique_id")
    public val bigFileUniqueId: String,
)
