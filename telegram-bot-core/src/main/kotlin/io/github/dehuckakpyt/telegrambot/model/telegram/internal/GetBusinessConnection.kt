package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class GetBusinessConnection(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
)
