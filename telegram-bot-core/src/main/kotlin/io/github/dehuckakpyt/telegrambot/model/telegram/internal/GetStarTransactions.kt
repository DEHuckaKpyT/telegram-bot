package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * @author KScript
 */
internal data class GetStarTransactions(
    @get:JsonProperty("offset")
    public val offset: Int? = null,
    @get:JsonProperty("limit")
    public val limit: Int? = null,
)
