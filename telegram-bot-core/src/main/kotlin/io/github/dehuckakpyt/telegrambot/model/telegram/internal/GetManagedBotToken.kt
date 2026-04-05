package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * @author KScript
 */
internal data class GetManagedBotToken(
    @get:JsonProperty("user_id")
    public val userId: Long,
)
