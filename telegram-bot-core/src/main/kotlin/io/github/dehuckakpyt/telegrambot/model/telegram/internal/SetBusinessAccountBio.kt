package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class SetBusinessAccountBio(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("bio")
    public val bio: String? = null,
)
