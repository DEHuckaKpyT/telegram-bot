package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatPermissions
import kotlin.Boolean
import kotlin.String

/**
 * @author KScript
 */
internal data class SetChatPermissions(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("permissions")
    public val permissions: ChatPermissions,
    @get:JsonProperty("use_independent_chat_permissions")
    public val useIndependentChatPermissions: Boolean? = null,
)
