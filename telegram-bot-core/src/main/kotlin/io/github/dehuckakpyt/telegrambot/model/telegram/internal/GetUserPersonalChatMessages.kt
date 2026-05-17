package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.Long

/**
 * @author KScript
 */
internal data class GetUserPersonalChatMessages(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("limit")
    public val limit: Int,
)
