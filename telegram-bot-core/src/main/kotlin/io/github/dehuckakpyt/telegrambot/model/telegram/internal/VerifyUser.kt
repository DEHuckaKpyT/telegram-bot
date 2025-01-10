package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class VerifyUser(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("custom_description")
    public val customDescription: String? = null,
)
