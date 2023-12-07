package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.InlineQueryResult


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class AnswerInlineQuery(
    @get:JsonProperty("inline_query_id") val inlineQueryId: String,
    @get:JsonProperty("results") val results: List<InlineQueryResult>,
    @get:JsonProperty("cache_time") val cacheTime: Int? = null,
    @get:JsonProperty("is_personal") val isPersonal: Boolean? = null,
    @get:JsonProperty("next_offset") val nextOffset: String? = null,
    @get:JsonProperty("switch_pm_text") val switchPmText: String? = null,
    @get:JsonProperty("switch_pm_parameter") val switchPmParameter: String? = null,
)


internal class AnswerWebAppQuery(
    @get:JsonProperty("web_app_query_id") val webAppQueryId: String,
    @get:JsonProperty("result") val result: InlineQueryResult,
)