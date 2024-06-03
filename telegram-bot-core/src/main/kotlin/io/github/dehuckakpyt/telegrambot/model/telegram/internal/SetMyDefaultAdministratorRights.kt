package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatAdministratorRights
import kotlin.Boolean

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class SetMyDefaultAdministratorRights(
    @get:JsonProperty("rights")
    public val rights: ChatAdministratorRights? = null,
    @get:JsonProperty("for_channels")
    public val forChannels: Boolean? = null,
)
