package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class SetBusinessAccountUsername(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("username")
    public val username: String? = null,
)
