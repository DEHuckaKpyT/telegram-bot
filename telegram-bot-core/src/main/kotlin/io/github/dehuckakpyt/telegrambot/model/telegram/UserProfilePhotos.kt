package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.collections.List

/**
 * This object represent a user's profile pictures.
 *
 * @see [UserProfilePhotos] (https://core.telegram.org/bots/api/#userprofilephotos)
 *
 * @author KScript
 *
 * @param totalCount Total number of profile pictures the target user has
 * @param photos Requested profile pictures (in up to 4 sizes each)
 */
public data class UserProfilePhotos(
    /**
     * Total number of profile pictures the target user has
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int,
    /**
     * Requested profile pictures (in up to 4 sizes each)
     */
    @get:JsonProperty("photos")
    @param:JsonProperty("photos")
    public val photos: List<List<PhotoSize>>,
)
