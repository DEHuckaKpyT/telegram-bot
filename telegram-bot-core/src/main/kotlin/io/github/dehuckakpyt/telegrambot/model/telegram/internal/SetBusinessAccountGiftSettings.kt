package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.AcceptedGiftTypes
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class SetBusinessAccountGiftSettings(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("show_gift_button")
    public val showGiftButton: Boolean,
    @get:JsonProperty("accepted_gift_types")
    public val acceptedGiftTypes: AcceptedGiftTypes,
)
