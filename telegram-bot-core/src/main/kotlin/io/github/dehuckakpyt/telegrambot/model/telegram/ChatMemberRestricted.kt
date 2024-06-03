package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Represents a [chat member](https://core.telegram.org/bots/api/#chatmember) that is under certain
 * restrictions in the chat. Supergroups only.
 *
 * @see [ChatMemberRestricted] (https://core.telegram.org/bots/api/#chatmemberrestricted)
 *
 * @author KScript
 *
 * @param status The member's status in the chat, always “restricted”
 * @param user Information about the user
 * @param isMember *True*, if the user is a member of the chat at the moment of the request
 * @param canSendMessages *True*, if the user is allowed to send text messages, contacts, giveaways,
 * giveaway winners, invoices, locations and venues
 * @param canSendAudios *True*, if the user is allowed to send audios
 * @param canSendDocuments *True*, if the user is allowed to send documents
 * @param canSendPhotos *True*, if the user is allowed to send photos
 * @param canSendVideos *True*, if the user is allowed to send videos
 * @param canSendVideoNotes *True*, if the user is allowed to send video notes
 * @param canSendVoiceNotes *True*, if the user is allowed to send voice notes
 * @param canSendPolls *True*, if the user is allowed to send polls
 * @param canSendOtherMessages *True*, if the user is allowed to send animations, games, stickers
 * and use inline bots
 * @param canAddWebPagePreviews *True*, if the user is allowed to add web page previews to their
 * messages
 * @param canChangeInfo *True*, if the user is allowed to change the chat title, photo and other
 * settings
 * @param canInviteUsers *True*, if the user is allowed to invite new users to the chat
 * @param canPinMessages *True*, if the user is allowed to pin messages
 * @param canManageTopics *True*, if the user is allowed to create forum topics
 * @param untilDate Date when restrictions will be lifted for this user; Unix time. If 0, then the
 * user is restricted forever
 */
public data class ChatMemberRestricted(
    /**
     * The member's status in the chat, always “restricted”
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
     * *True*, if the user is a member of the chat at the moment of the request
     */
    @get:JsonProperty("is_member")
    @param:JsonProperty("is_member")
    public val isMember: Boolean,
    /**
     * *True*, if the user is allowed to send text messages, contacts, giveaways, giveaway winners,
     * invoices, locations and venues
     */
    @get:JsonProperty("can_send_messages")
    @param:JsonProperty("can_send_messages")
    public val canSendMessages: Boolean,
    /**
     * *True*, if the user is allowed to send audios
     */
    @get:JsonProperty("can_send_audios")
    @param:JsonProperty("can_send_audios")
    public val canSendAudios: Boolean,
    /**
     * *True*, if the user is allowed to send documents
     */
    @get:JsonProperty("can_send_documents")
    @param:JsonProperty("can_send_documents")
    public val canSendDocuments: Boolean,
    /**
     * *True*, if the user is allowed to send photos
     */
    @get:JsonProperty("can_send_photos")
    @param:JsonProperty("can_send_photos")
    public val canSendPhotos: Boolean,
    /**
     * *True*, if the user is allowed to send videos
     */
    @get:JsonProperty("can_send_videos")
    @param:JsonProperty("can_send_videos")
    public val canSendVideos: Boolean,
    /**
     * *True*, if the user is allowed to send video notes
     */
    @get:JsonProperty("can_send_video_notes")
    @param:JsonProperty("can_send_video_notes")
    public val canSendVideoNotes: Boolean,
    /**
     * *True*, if the user is allowed to send voice notes
     */
    @get:JsonProperty("can_send_voice_notes")
    @param:JsonProperty("can_send_voice_notes")
    public val canSendVoiceNotes: Boolean,
    /**
     * *True*, if the user is allowed to send polls
     */
    @get:JsonProperty("can_send_polls")
    @param:JsonProperty("can_send_polls")
    public val canSendPolls: Boolean,
    /**
     * *True*, if the user is allowed to send animations, games, stickers and use inline bots
     */
    @get:JsonProperty("can_send_other_messages")
    @param:JsonProperty("can_send_other_messages")
    public val canSendOtherMessages: Boolean,
    /**
     * *True*, if the user is allowed to add web page previews to their messages
     */
    @get:JsonProperty("can_add_web_page_previews")
    @param:JsonProperty("can_add_web_page_previews")
    public val canAddWebPagePreviews: Boolean,
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
     * *True*, if the user is allowed to pin messages
     */
    @get:JsonProperty("can_pin_messages")
    @param:JsonProperty("can_pin_messages")
    public val canPinMessages: Boolean,
    /**
     * *True*, if the user is allowed to create forum topics
     */
    @get:JsonProperty("can_manage_topics")
    @param:JsonProperty("can_manage_topics")
    public val canManageTopics: Boolean,
    /**
     * Date when restrictions will be lifted for this user; Unix time. If 0, then the user is
     * restricted forever
     */
    @get:JsonProperty("until_date")
    @param:JsonProperty("until_date")
    public val untilDate: Long,
) : ChatMember
