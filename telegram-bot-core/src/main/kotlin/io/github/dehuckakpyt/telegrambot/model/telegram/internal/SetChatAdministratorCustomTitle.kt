package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class SetChatAdministratorCustomTitle(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("custom_title")
    public val customTitle: String,
)
