package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class EditChatInviteLink(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("invite_link")
    public val inviteLink: String,
    @get:JsonProperty("name")
    public val name: String? = null,
    @get:JsonProperty("expire_date")
    public val expireDate: Long? = null,
    @get:JsonProperty("member_limit")
    public val memberLimit: Int? = null,
    @get:JsonProperty("creates_join_request")
    public val createsJoinRequest: Boolean? = null,
)
