package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * @author KScript
 */
internal data class GetChatMenuButton(
    @get:JsonProperty("chat_id")
    public val chatId: Long? = null,
)
