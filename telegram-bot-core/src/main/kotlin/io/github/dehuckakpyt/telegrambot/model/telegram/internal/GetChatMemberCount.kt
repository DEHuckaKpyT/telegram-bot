package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class GetChatMemberCount(
    @get:JsonProperty("chat_id")
    public val chatId: String,
)
