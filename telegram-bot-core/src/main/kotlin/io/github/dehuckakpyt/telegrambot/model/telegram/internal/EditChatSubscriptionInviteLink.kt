package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class EditChatSubscriptionInviteLink(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("invite_link")
    public val inviteLink: String,
    @get:JsonProperty("name")
    public val name: String? = null,
)
