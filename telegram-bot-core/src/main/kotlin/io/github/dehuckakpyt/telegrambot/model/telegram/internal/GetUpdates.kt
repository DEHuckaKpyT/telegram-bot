package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal data class GetUpdates(
    @get:JsonProperty("offset")
    public val offset: Long? = null,
    @get:JsonProperty("limit")
    public val limit: Int? = null,
    @get:JsonProperty("timeout")
    public val timeout: Int? = null,
    @get:JsonProperty("allowed_updates")
    public val allowedUpdates: Iterable<String>? = null,
)
