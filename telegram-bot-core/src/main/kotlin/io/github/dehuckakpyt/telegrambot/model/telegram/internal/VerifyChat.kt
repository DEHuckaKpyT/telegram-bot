package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class VerifyChat(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("custom_description")
    public val customDescription: String? = null,
)
