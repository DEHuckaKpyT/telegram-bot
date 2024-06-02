package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResult
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class AnswerWebAppQuery(
    @get:JsonProperty("web_app_query_id")
    public val webAppQueryId: String,
    @get:JsonProperty("result")
    public val result: InlineQueryResult,
)
