package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class RemoveBusinessAccountProfilePhoto(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("is_public")
    public val isPublic: Boolean? = null,
)
