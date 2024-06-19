package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class PromoteChatMember(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("is_anonymous")
    public val isAnonymous: Boolean? = null,
    @get:JsonProperty("can_manage_chat")
    public val canManageChat: Boolean? = null,
    @get:JsonProperty("can_delete_messages")
    public val canDeleteMessages: Boolean? = null,
    @get:JsonProperty("can_manage_video_chats")
    public val canManageVideoChats: Boolean? = null,
    @get:JsonProperty("can_restrict_members")
    public val canRestrictMembers: Boolean? = null,
    @get:JsonProperty("can_promote_members")
    public val canPromoteMembers: Boolean? = null,
    @get:JsonProperty("can_change_info")
    public val canChangeInfo: Boolean? = null,
    @get:JsonProperty("can_invite_users")
    public val canInviteUsers: Boolean? = null,
    @get:JsonProperty("can_post_stories")
    public val canPostStories: Boolean? = null,
    @get:JsonProperty("can_edit_stories")
    public val canEditStories: Boolean? = null,
    @get:JsonProperty("can_delete_stories")
    public val canDeleteStories: Boolean? = null,
    @get:JsonProperty("can_post_messages")
    public val canPostMessages: Boolean? = null,
    @get:JsonProperty("can_edit_messages")
    public val canEditMessages: Boolean? = null,
    @get:JsonProperty("can_pin_messages")
    public val canPinMessages: Boolean? = null,
    @get:JsonProperty("can_manage_topics")
    public val canManageTopics: Boolean? = null,
)
