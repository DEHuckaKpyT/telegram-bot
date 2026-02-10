package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object represents the audios displayed on a user's profile.
 *
 * @see [UserProfileAudios] (https://core.telegram.org/bots/api/#userprofileaudios)
 *
 * @author KScript
 *
 * @param totalCount Total number of profile audios for the target user
 * @param audios Requested profile audios
 */
public data class UserProfileAudios(
    /**
     * Total number of profile audios for the target user
     */
    @get:JsonProperty("total_count")
    @param:JsonProperty("total_count")
    public val totalCount: Int,
    /**
     * Requested profile audios
     */
    @get:JsonProperty("audios")
    @param:JsonProperty("audios")
    public val audios: List<Audio>,
)
