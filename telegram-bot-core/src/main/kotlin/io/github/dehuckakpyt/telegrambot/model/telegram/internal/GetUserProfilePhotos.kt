package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class GetUserProfilePhotos(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("offset")
    public val offset: Int? = null,
    @get:JsonProperty("limit")
    public val limit: Int? = null,
)
