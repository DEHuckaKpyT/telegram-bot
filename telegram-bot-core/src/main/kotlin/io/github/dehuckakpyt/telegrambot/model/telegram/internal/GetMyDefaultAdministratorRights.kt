package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean

/**
 * @author KScript
 */
internal data class GetMyDefaultAdministratorRights(
    @get:JsonProperty("for_channels")
    public val forChannels: Boolean? = null,
)
