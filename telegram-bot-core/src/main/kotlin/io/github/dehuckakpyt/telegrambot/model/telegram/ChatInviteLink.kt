package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Represents an invite link for a chat.
 *
 * @see [ChatInviteLink] (https://core.telegram.org/bots/api/#chatinvitelink)
 *
 * @author KScript
 *
 * @param inviteLink The invite link. If the link was created by another chat administrator, then
 * the second part of the link will be replaced with “…”.
 * @param creator Creator of the link
 * @param createsJoinRequest *True*, if users joining the chat via the link need to be approved by
 * chat administrators
 * @param isPrimary *True*, if the link is primary
 * @param isRevoked *True*, if the link is revoked
 * @param name *Optional*. Invite link name
 * @param expireDate *Optional*. Point in time (Unix timestamp) when the link will expire or has
 * been expired
 * @param memberLimit *Optional*. The maximum number of users that can be members of the chat
 * simultaneously after joining the chat via this invite link; 1-99999
 * @param pendingJoinRequestCount *Optional*. Number of pending join requests created using this
 * link
 */
public data class ChatInviteLink(
    /**
     * The invite link. If the link was created by another chat administrator, then the second part
     * of the link will be replaced with “…”.
     */
    @get:JsonProperty("invite_link")
    @param:JsonProperty("invite_link")
    public val inviteLink: String,
    /**
     * Creator of the link
     */
    @get:JsonProperty("creator")
    @param:JsonProperty("creator")
    public val creator: User,
    /**
     * *True*, if users joining the chat via the link need to be approved by chat administrators
     */
    @get:JsonProperty("creates_join_request")
    @param:JsonProperty("creates_join_request")
    public val createsJoinRequest: Boolean,
    /**
     * *True*, if the link is primary
     */
    @get:JsonProperty("is_primary")
    @param:JsonProperty("is_primary")
    public val isPrimary: Boolean,
    /**
     * *True*, if the link is revoked
     */
    @get:JsonProperty("is_revoked")
    @param:JsonProperty("is_revoked")
    public val isRevoked: Boolean,
    /**
     * *Optional*. Invite link name
     */
    @get:JsonProperty("name")
    @param:JsonProperty("name")
    public val name: String? = null,
    /**
     * *Optional*. Point in time (Unix timestamp) when the link will expire or has been expired
     */
    @get:JsonProperty("expire_date")
    @param:JsonProperty("expire_date")
    public val expireDate: Long? = null,
    /**
     * *Optional*. The maximum number of users that can be members of the chat simultaneously after
     * joining the chat via this invite link; 1-99999
     */
    @get:JsonProperty("member_limit")
    @param:JsonProperty("member_limit")
    public val memberLimit: Int? = null,
    /**
     * *Optional*. Number of pending join requests created using this link
     */
    @get:JsonProperty("pending_join_request_count")
    @param:JsonProperty("pending_join_request_count")
    public val pendingJoinRequestCount: Int? = null,
)
