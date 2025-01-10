package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * @author KScript
 */
internal data class RemoveUserVerification(
    @get:JsonProperty("user_id")
    public val userId: Long,
)
