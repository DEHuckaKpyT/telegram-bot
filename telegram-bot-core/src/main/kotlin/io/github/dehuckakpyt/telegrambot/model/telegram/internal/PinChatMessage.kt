package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class PinChatMessage(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("message_id")
    public val messageId: Long,
    @get:JsonProperty("disable_notification")
    public val disableNotification: Boolean? = null,
)
