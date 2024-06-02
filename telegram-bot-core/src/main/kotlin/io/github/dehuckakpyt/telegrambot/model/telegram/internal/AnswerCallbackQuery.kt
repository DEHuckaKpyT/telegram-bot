package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class AnswerCallbackQuery(
    @get:JsonProperty("callback_query_id")
    public val callbackQueryId: String,
    @get:JsonProperty("text")
    public val text: String? = null,
    @get:JsonProperty("show_alert")
    public val showAlert: Boolean? = null,
    @get:JsonProperty("url")
    public val url: String? = null,
    @get:JsonProperty("cache_time")
    public val cacheTime: Int? = null,
)
