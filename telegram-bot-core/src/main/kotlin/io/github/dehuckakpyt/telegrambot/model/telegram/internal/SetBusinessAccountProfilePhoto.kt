package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputProfilePhoto
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class SetBusinessAccountProfilePhoto(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("photo")
    public val photo: InputProfilePhoto,
    @get:JsonProperty("is_public")
    public val isPublic: Boolean? = null,
)
