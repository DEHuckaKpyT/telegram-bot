package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean

/**
 * @author KScript
 */
internal data class DeleteWebhook(
    @get:JsonProperty("drop_pending_updates")
    public val dropPendingUpdates: Boolean? = null,
)
