package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author KScript
 */
internal data class GetUserProfileAudios(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("offset")
    public val offset: Int? = null,
    @get:JsonProperty("limit")
    public val limit: Int? = null,
)
