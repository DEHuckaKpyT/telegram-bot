package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that has some
 * additional privileges.
 *
 * @see [ChatMemberAdministrator] (https://core.telegram.org/bots/api/#chatmemberadministrator)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “administrator”
 * @param user Information about the user
 * @param canBeEdited *True*, if the bot is allowed to edit administrator privileges of that user
 * @param isAnonymous *True*, if the user's presence in the chat is hidden
 * @param canManageChat *True*, if the administrator can access the chat event log, get boost list,
 * see hidden supergroup and channel members, report spam messages and ignore slow mode. Implied by any
 * other administrator privilege.
 * @param canDeleteMessages *True*, if the administrator can delete messages of other users
 * @param canManageVideoChats *True*, if the administrator can manage video chats
 * @param canRestrictMembers *True*, if the administrator can restrict, ban or unban chat members,
 * or access supergroup statistics
 * @param canPromoteMembers *True*, if the administrator can add new administrators with a subset of
 * their own privileges or demote administrators that they have promoted, directly or indirectly
 * (promoted by administrators that were appointed by the user)
 * @param canChangeInfo *True*, if the user is allowed to change the chat title, photo and other
 * settings
 * @param canInviteUsers *True*, if the user is allowed to invite new users to the chat
 * @param canPostStories *True*, if the administrator can post stories to the chat
 * @param canEditStories *True*, if the administrator can edit stories posted by other users, post
 * stories to the chat page, pin chat stories, and access the chat's story archive
 * @param canDeleteStories *True*, if the administrator can delete stories posted by other users
 * @param canPostMessages *Optional*. *True*, if the administrator can post messages in the channel,
 * or access channel statistics; for channels only
 * @param canEditMessages *Optional*. *True*, if the administrator can edit messages of other users
 * and can pin messages; for channels only
 * @param canPinMessages *Optional*. *True*, if the user is allowed to pin messages; for groups and
 * supergroups only
 * @param canManageTopics *Optional*. *True*, if the user is allowed to create, rename, close, and
 * reopen forum topics; for supergroups only
 * @param customTitle *Optional*. Custom title for this user
 */
public data class ChatMemberAdministrator(
    /**
     * The member's status in the chat, always “administrator”
     */
    @get:JsonProperty("status")
    @param:JsonProperty("status")
    override val status: String,
    /**
     * Information about the user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    override val user: User,
    /**
     * *True*, if the bot is allowed to edit administrator privileges of that user
     */
    @get:JsonProperty("can_be_edited")
    @param:JsonProperty("can_be_edited")
    public val canBeEdited: Boolean,
    /**
     * *True*, if the user's presence in the chat is hidden
     */
    @get:JsonProperty("is_anonymous")
    @param:JsonProperty("is_anonymous")
    public val isAnonymous: Boolean,
    /**
     * *True*, if the administrator can access the chat event log, get boost list, see hidden
     * supergroup and channel members, report spam messages and ignore slow mode. Implied by any other
     * administrator privilege.
     */
    @get:JsonProperty("can_manage_chat")
    @param:JsonProperty("can_manage_chat")
    public val canManageChat: Boolean,
    /**
     * *True*, if the administrator can delete messages of other users
     */
    @get:JsonProperty("can_delete_messages")
    @param:JsonProperty("can_delete_messages")
    public val canDeleteMessages: Boolean,
    /**
     * *True*, if the administrator can manage video chats
     */
    @get:JsonProperty("can_manage_video_chats")
    @param:JsonProperty("can_manage_video_chats")
    public val canManageVideoChats: Boolean,
    /**
     * *True*, if the administrator can restrict, ban or unban chat members, or access supergroup
     * statistics
     */
    @get:JsonProperty("can_restrict_members")
    @param:JsonProperty("can_restrict_members")
    public val canRestrictMembers: Boolean,
    /**
     * *True*, if the administrator can add new administrators with a subset of their own privileges
     * or demote administrators that they have promoted, directly or indirectly (promoted by
     * administrators that were appointed by the user)
     */
    @get:JsonProperty("can_promote_members")
    @param:JsonProperty("can_promote_members")
    public val canPromoteMembers: Boolean,
    /**
     * *True*, if the user is allowed to change the chat title, photo and other settings
     */
    @get:JsonProperty("can_change_info")
    @param:JsonProperty("can_change_info")
    public val canChangeInfo: Boolean,
    /**
     * *True*, if the user is allowed to invite new users to the chat
     */
    @get:JsonProperty("can_invite_users")
    @param:JsonProperty("can_invite_users")
    public val canInviteUsers: Boolean,
    /**
     * *True*, if the administrator can post stories to the chat
     */
    @get:JsonProperty("can_post_stories")
    @param:JsonProperty("can_post_stories")
    public val canPostStories: Boolean,
    /**
     * *True*, if the administrator can edit stories posted by other users, post stories to the chat
     * page, pin chat stories, and access the chat's story archive
     */
    @get:JsonProperty("can_edit_stories")
    @param:JsonProperty("can_edit_stories")
    public val canEditStories: Boolean,
    /**
     * *True*, if the administrator can delete stories posted by other users
     */
    @get:JsonProperty("can_delete_stories")
    @param:JsonProperty("can_delete_stories")
    public val canDeleteStories: Boolean,
    /**
     * *Optional*. *True*, if the administrator can post messages in the channel, or access channel
     * statistics; for channels only
     */
    @get:JsonProperty("can_post_messages")
    @param:JsonProperty("can_post_messages")
    public val canPostMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the administrator can edit messages of other users and can pin
     * messages; for channels only
     */
    @get:JsonProperty("can_edit_messages")
    @param:JsonProperty("can_edit_messages")
    public val canEditMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to pin messages; for groups and supergroups only
     */
    @get:JsonProperty("can_pin_messages")
    @param:JsonProperty("can_pin_messages")
    public val canPinMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to create, rename, close, and reopen forum topics;
     * for supergroups only
     */
    @get:JsonProperty("can_manage_topics")
    @param:JsonProperty("can_manage_topics")
    public val canManageTopics: Boolean? = null,
    /**
     * *Optional*. Custom title for this user
     */
    @get:JsonProperty("custom_title")
    @param:JsonProperty("custom_title")
    public val customTitle: String? = null,
) : ChatMember
