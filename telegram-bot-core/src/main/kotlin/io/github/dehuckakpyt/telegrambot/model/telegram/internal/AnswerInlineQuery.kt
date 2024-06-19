package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResult
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResultsButton
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class AnswerInlineQuery(
    @get:JsonProperty("inline_query_id")
    public val inlineQueryId: String,
    @get:JsonProperty("results")
    public val results: Iterable<InlineQueryResult>,
    @get:JsonProperty("cache_time")
    public val cacheTime: Int? = null,
    @get:JsonProperty("is_personal")
    public val isPersonal: Boolean? = null,
    @get:JsonProperty("next_offset")
    public val nextOffset: String? = null,
    @get:JsonProperty("button")
    public val button: InlineQueryResultsButton? = null,
)
