package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class SendChatJoinRequestWebApp(
    @get:JsonProperty("chat_join_request_query_id")
    public val chatJoinRequestQueryId: String,
    @get:JsonProperty("web_app_url")
    public val webAppUrl: String,
)
