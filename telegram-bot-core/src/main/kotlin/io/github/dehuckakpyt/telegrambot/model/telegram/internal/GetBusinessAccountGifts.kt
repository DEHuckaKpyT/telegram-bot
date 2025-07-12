package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * @author KScript
 */
internal data class GetBusinessAccountGifts(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("exclude_unsaved")
    public val excludeUnsaved: Boolean? = null,
    @get:JsonProperty("exclude_saved")
    public val excludeSaved: Boolean? = null,
    @get:JsonProperty("exclude_unlimited")
    public val excludeUnlimited: Boolean? = null,
    @get:JsonProperty("exclude_limited")
    public val excludeLimited: Boolean? = null,
    @get:JsonProperty("exclude_unique")
    public val excludeUnique: Boolean? = null,
    @get:JsonProperty("sort_by_price")
    public val sortByPrice: Boolean? = null,
    @get:JsonProperty("offset")
    public val offset: String? = null,
    @get:JsonProperty("limit")
    public val limit: Int? = null,
)
