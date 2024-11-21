package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResult
import kotlin.Boolean
import kotlin.Long

/**
 * @author KScript
 */
internal data class SavePreparedInlineMessage(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("result")
    public val result: InlineQueryResult,
    @get:JsonProperty("allow_user_chats")
    public val allowUserChats: Boolean? = null,
    @get:JsonProperty("allow_bot_chats")
    public val allowBotChats: Boolean? = null,
    @get:JsonProperty("allow_group_chats")
    public val allowGroupChats: Boolean? = null,
    @get:JsonProperty("allow_channel_chats")
    public val allowChannelChats: Boolean? = null,
)
