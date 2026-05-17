package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class GetChatAdministrators(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("return_bots")
    public val returnBots: Boolean? = null,
)
