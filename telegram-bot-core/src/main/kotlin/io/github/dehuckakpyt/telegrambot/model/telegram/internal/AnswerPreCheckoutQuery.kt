package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class AnswerPreCheckoutQuery(
    @get:JsonProperty("pre_checkout_query_id")
    public val preCheckoutQueryId: String,
    @get:JsonProperty("ok")
    public val ok: Boolean,
    @get:JsonProperty("error_message")
    public val errorMessage: String? = null,
)
