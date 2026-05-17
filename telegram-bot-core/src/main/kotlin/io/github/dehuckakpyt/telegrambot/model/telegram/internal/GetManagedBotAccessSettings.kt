package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * @author KScript
 */
internal data class GetManagedBotAccessSettings(
    @get:JsonProperty("user_id")
    public val userId: Long,
)
