package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class UnbanChatMember(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("only_if_banned")
    public val onlyIfBanned: Boolean? = null,
)
