package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class BanChatMember(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("until_date")
    public val untilDate: Long? = null,
    @get:JsonProperty("revoke_messages")
    public val revokeMessages: Boolean? = null,
)
