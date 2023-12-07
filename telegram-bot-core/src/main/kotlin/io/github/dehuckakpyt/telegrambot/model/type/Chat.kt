package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class Chat(
    @param:JsonProperty("id") val id: Long,
    @param:JsonProperty("type") val type: String,
    @param:JsonProperty("title") val title: String? = null,
    @param:JsonProperty("username") val username: String? = null,
    @param:JsonProperty("first_name") val firstName: String? = null,
    @param:JsonProperty("last_name") val lastName: String? = null,
    @param:JsonProperty("is_forum") val isForum: Boolean? = null,
    @param:JsonProperty("photo") val photo: ChatPhoto? = null,
    @param:JsonProperty("active_usernames") val activeUsernames: List<String>? = null,
    @param:JsonProperty("emoji_status_custom_emoji_id") val emojiStatusCustomEmojiId: String? = null,
    @param:JsonProperty("bio") val bio: String? = null,
    @param:JsonProperty("has_private_forwards") val hasPrivateForwards: Boolean? = null,
    @param:JsonProperty("has_restricted_voice_and_video_messages") val hasRestrictedVoiceAndVideoMessages: Boolean? = null,
    @param:JsonProperty("join_to_send_messages") val joinToSendMessages: Boolean? = null,
    @param:JsonProperty("join_by_request") val joinByRequest: Boolean? = null,
    @param:JsonProperty("description") val description: String? = null,
    @param:JsonProperty("invite_link") val inviteLink: String? = null,
    @param:JsonProperty("pinned_message") val pinnedMessage: Message? = null,
    @param:JsonProperty("permissions") val permissions: ChatPermissions? = null,
    @param:JsonProperty("slow_mode_delay") val slowModeDelay: Boolean? = null,
    @param:JsonProperty("message_auto_delete_time") val messageAutoDeleteTime: Int? = null,
    @param:JsonProperty("has_aggressive_anti_spam_enabled") val hasAggressiveAntiSpamEnabled: Boolean? = null,
    @param:JsonProperty("has_hidden_members") val hasHiddenMembers: Boolean? = null,
    @param:JsonProperty("has_protected_content") val hasProtectedContent: Boolean? = null,
    @param:JsonProperty("sticker_set_name") val stickerSetName: String? = null,
    @param:JsonProperty("can_set_sticker_set") val canSetStickerSet: Boolean? = null,
    @param:JsonProperty("linked_chat_id") val linkedString: Int? = null,
    @param:JsonProperty("location") val location: ChatLocation? = null,
)

public data class ChatLocation(
    @param:JsonProperty("location") val location: Location,
    @param:JsonProperty("address") val address: String
)

public data class ChatPermissions(
    @param:JsonProperty("can_send_messages") val canSendMessages: Boolean? = null,
    @param:JsonProperty("can_send_audios") val canSendAudios: Boolean? = null,
    @param:JsonProperty("can_send_documents") val canSendDocuments: Boolean? = null,
    @param:JsonProperty("can_send_photos") val canSendPhotos: Boolean? = null,
    @param:JsonProperty("can_send_videos") val canSendVideos: Boolean? = null,
    @param:JsonProperty("can_send_video_notes") val canSendVideoNotes: Boolean? = null,
    @param:JsonProperty("can_send_voice_notes") val canSendVoiceNotes: Boolean? = null,
    @param:JsonProperty("can_send_polls") val canSendPolls: Boolean? = null,
    @param:JsonProperty("can_send_other_messages") val canSendOtherMessages: Boolean? = null,
    @param:JsonProperty("can_add_web_page_previews") val canAddWebPagePreviews: Boolean? = null,
    @param:JsonProperty("can_change_info") val canChangeInfo: Boolean? = null,
    @param:JsonProperty("can_invite_users") val canInviteUsers: Boolean? = null,
    @param:JsonProperty("can_pin_messages") val canPinMessages: Boolean? = null,
    @param:JsonProperty("can_manage_topics") val canManageTopics: Boolean? = null,
)


public data class ChatPhoto(
    @param:JsonProperty("small_file_id") val smallFileId: String,
    @param:JsonProperty("small_file_unique_id") val smallFileUniqueId: String,
    @param:JsonProperty("big_file_id") val bigFileId: String,
    @param:JsonProperty("big_file_unique_id") val bigFileUniqueId: String
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "status")
@JsonSubTypes(
    JsonSubTypes.Type(value = ChatMember.Owner::class, name = "creator"),
    JsonSubTypes.Type(value = ChatMember.Administrator::class, name = "administrator"),
    JsonSubTypes.Type(value = ChatMember.Member::class, name = "member"),
    JsonSubTypes.Type(value = ChatMember.Restricted::class, name = "restricted"),
    JsonSubTypes.Type(value = ChatMember.Left::class, name = "left"),
    JsonSubTypes.Type(value = ChatMember.Banned::class, name = "kicked"),
)
public sealed class ChatMember {

    public abstract val status: String
    public abstract val user: User

    @JsonTypeName("creator")
    public data class Owner(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
        @param:JsonProperty("is_anonymous") val isAnonymous: Boolean? = null,
        @param:JsonProperty("custom_title") val customTitle: String? = null
    ) : ChatMember()

    @JsonTypeName("administrator")
    public data class Administrator(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
        @param:JsonProperty("can_be_edited") val canBeEdited: Boolean,
        @param:JsonProperty("is_anonymous") val isAnonymous: Boolean,
        @param:JsonProperty("can_manage_chat") val canManageChat: Boolean,
        @param:JsonProperty("can_delete_messages") val canDeleteMessages: Boolean,
        @param:JsonProperty("can_manage_video_chats") val canManageVideoChats: Boolean,
        @param:JsonProperty("can_restrict_members") val canRestrictMembers: Boolean,
        @param:JsonProperty("can_promote_members") val canPromoteMembers: Boolean,
        @param:JsonProperty("can_change_info") val canChangeInfo: Boolean,
        @param:JsonProperty("can_invite_users") val canInviteUsers: Boolean,
        @param:JsonProperty("can_post_messages") val canPostMessages: Boolean? = null,
        @param:JsonProperty("can_edit_messages") val canEditMessages: Boolean? = null,
        @param:JsonProperty("can_pin_messages") val canPinMessages: Boolean? = null,
        @param:JsonProperty("can_manage_topics") val canManageTopics: Boolean? = null,
        @param:JsonProperty("custom_title") val customTitle: String? = null
    ) : ChatMember()

    @JsonTypeName("member")
    public data class Member(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
    ) : ChatMember()

    @JsonTypeName("restricted")
    public data class Restricted(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
        @param:JsonProperty("is_member") val isMember: Boolean,
        @param:JsonProperty("can_send_messages") val canSendMessages: Boolean,
        @param:JsonProperty("can_send_audios") val canSendAudios: Boolean,
        @param:JsonProperty("can_send_documents") val canSendDocuments: Boolean,
        @param:JsonProperty("can_send_photos") val canSendPhotos: Boolean,
        @param:JsonProperty("can_send_videos") val canSendVideos: Boolean,
        @param:JsonProperty("can_send_video_notes") val canSendVideoNotes: Boolean,
        @param:JsonProperty("can_send_voice_notes") val canSendVoiceNotes: Boolean,
        @param:JsonProperty("can_send_polls") val canSendPolls: Boolean,
        @param:JsonProperty("can_send_other_messages") val canSendOtherMessages: Boolean,
        @param:JsonProperty("can_add_web_page_previews") val canAddWebPagePreviews: Boolean,
        @param:JsonProperty("can_change_info") val canChangeInfo: Boolean,
        @param:JsonProperty("can_invite_users") val canInviteUsers: Boolean,
        @param:JsonProperty("can_pin_messages") val canPinMessages: Boolean,
        @param:JsonProperty("can_manage_topics") val canManageTopics: Boolean,
        @param:JsonProperty("until_date") val untilDate: Int
    ) : ChatMember()

    @JsonTypeName("left")
    public data class Left(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
    ) : ChatMember()

    @JsonTypeName("kicked")
    public data class Banned(
        @param:JsonProperty("status") override val status: String,
        @param:JsonProperty("user") override val user: User,
        @param:JsonProperty("until_date") val untilDate: Int
    ) : ChatMember()
}

public data class ChatMemberUpdated(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("from") val from: User,
    @param:JsonProperty("date") val date: Int,
    @param:JsonProperty("old_chat_member") val oldChatMember: ChatMember,
    @param:JsonProperty("new_chat_member") val newChatMember: ChatMember,
    @param:JsonProperty("invite_link") val inviteLink: ChatInviteLink? = null
)

public data class ChatInviteLink(
    @param:JsonProperty("invite_link") val inviteLink: String,
    @param:JsonProperty("creator") val creator: User,
    @param:JsonProperty("creates_join_request") val createsJoinRequest: Boolean,
    @param:JsonProperty("is_primary") val isPrimary: Boolean,
    @param:JsonProperty("is_revoked") val isRevoked: Boolean,
    @param:JsonProperty("name") val name: String? = null,
    @param:JsonProperty("expire_date") val expireDate: Int? = null,
    @param:JsonProperty("member_limit") val memberLimit: Int? = null,
    @param:JsonProperty("pending_join_request_count") val pendingJoinRequestCount: Int? = null,
)

public data class ChatJoinRequest(
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("from") val from: Chat,
    @param:JsonProperty("user_chat_id") val userString: Long,
    @param:JsonProperty("date") val date: Long,
    @param:JsonProperty("bio") val bio: String? = null,
    @param:JsonProperty("invite_link") val inviteLink: ChatInviteLink? = null,
)

public data class ChatAdministratorRights(
    @param:JsonProperty("is_anonymous") val isAnonymous: Boolean,
    @param:JsonProperty("can_manage_chat") val canManageChat: Boolean,
    @param:JsonProperty("can_delete_messages") val canDeleteMessages: Boolean,
    @param:JsonProperty("can_manage_video_chats") val canManageVideoChats: Boolean,
    @param:JsonProperty("can_restrict_members") val canRestrictMembers: Boolean,
    @param:JsonProperty("can_promote_members") val canPromoteMembers: Boolean,
    @param:JsonProperty("can_change_info") val canChangeInfo: Boolean,
    @param:JsonProperty("can_invite_users") val canInviteUsers: Boolean,
    @param:JsonProperty("can_post_messages") val canPostMessages: Boolean? = null,
    @param:JsonProperty("can_edit_messages") val canEditMessages: Boolean? = null,
    @param:JsonProperty("can_pin_messages") val canPinMessages: Boolean? = null,
    @param:JsonProperty("can_manage_topics") val canManageTopics: Boolean? = null,
)

public data class ForumTopic(
    @param:JsonProperty("message_thread_id") val messageThreadId: Long,
    @param:JsonProperty("name") val name: String,
    @param:JsonProperty("icon_color") val iconColor: Int,
    @param:JsonProperty("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
)

public data class ForumTopicEdited(
    @param:JsonProperty("name") val name: String,
    @param:JsonProperty("icon_custom_emoji_id") val iconCustomEmojiId: String? = null,
)

public class GeneralForumTopicHidden

public class GeneralForumTopicUnhidden

public class WriteAccessAllowed