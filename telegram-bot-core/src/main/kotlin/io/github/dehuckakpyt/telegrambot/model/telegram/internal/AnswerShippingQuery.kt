package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.ShippingOption
import kotlin.Boolean
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class AnswerShippingQuery(
    @get:JsonProperty("shipping_query_id")
    public val shippingQueryId: String,
    @get:JsonProperty("ok")
    public val ok: Boolean,
    @get:JsonProperty("shipping_options")
    public val shippingOptions: Iterable<ShippingOption>? = null,
    @get:JsonProperty("error_message")
    public val errorMessage: String? = null,
)
