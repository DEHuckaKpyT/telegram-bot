package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * This object represents changes in the status of a chat member.
 *
 * @see [ChatMemberUpdated] (https://core.telegram.org/bots/api/#chatmemberupdated)
 *
 * @author KScript
 *
 * @param chat Chat the user belongs to
 * @param from Performer of the action, which resulted in the change
 * @param date Date the change was done in Unix time
 * @param oldChatMember Previous information about the chat member
 * @param newChatMember New information about the chat member
 * @param inviteLink *Optional*. Chat invite link, which was used by the user to join the chat; for
 * joining by invite link events only.
 * @param viaJoinRequest *Optional*. True, if the user joined the chat after sending a direct join
 * request without using an invite link and being approved by an administrator
 * @param viaChatFolderInviteLink *Optional*. True, if the user joined the chat via a chat folder
 * invite link
 */
public data class ChatMemberUpdated(
    /**
     * Chat the user belongs to
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat,
    /**
     * Performer of the action, which resulted in the change
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * Date the change was done in Unix time
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    public val date: Long,
    /**
     * Previous information about the chat member
     */
    @get:JsonProperty("old_chat_member")
    @param:JsonProperty("old_chat_member")
    public val oldChatMember: ChatMember,
    /**
     * New information about the chat member
     */
    @get:JsonProperty("new_chat_member")
    @param:JsonProperty("new_chat_member")
    public val newChatMember: ChatMember,
    /**
     * *Optional*. Chat invite link, which was used by the user to join the chat; for joining by
     * invite link events only.
     */
    @get:JsonProperty("invite_link")
    @param:JsonProperty("invite_link")
    public val inviteLink: ChatInviteLink? = null,
    /**
     * *Optional*. True, if the user joined the chat after sending a direct join request without
     * using an invite link and being approved by an administrator
     */
    @get:JsonProperty("via_join_request")
    @param:JsonProperty("via_join_request")
    public val viaJoinRequest: Boolean? = null,
    /**
     * *Optional*. True, if the user joined the chat via a chat folder invite link
     */
    @get:JsonProperty("via_chat_folder_invite_link")
    @param:JsonProperty("via_chat_folder_invite_link")
    public val viaChatFolderInviteLink: Boolean? = null,
)
