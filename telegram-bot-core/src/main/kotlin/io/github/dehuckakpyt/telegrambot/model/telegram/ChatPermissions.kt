package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean

/**
 * Created on 03.06.2024.
 *
 * Describes actions that a non-administrator user is allowed to take in a chat.
 *
 * @see [ChatPermissions] (https://core.telegram.org/bots/api/#chatpermissions)
 *
 * @author KScript
 *
 * @param canSendMessages *Optional*. *True*, if the user is allowed to send text messages,
 * contacts, giveaways, giveaway winners, invoices, locations and venues
 * @param canSendAudios *Optional*. *True*, if the user is allowed to send audios
 * @param canSendDocuments *Optional*. *True*, if the user is allowed to send documents
 * @param canSendPhotos *Optional*. *True*, if the user is allowed to send photos
 * @param canSendVideos *Optional*. *True*, if the user is allowed to send videos
 * @param canSendVideoNotes *Optional*. *True*, if the user is allowed to send video notes
 * @param canSendVoiceNotes *Optional*. *True*, if the user is allowed to send voice notes
 * @param canSendPolls *Optional*. *True*, if the user is allowed to send polls
 * @param canSendOtherMessages *Optional*. *True*, if the user is allowed to send animations, games,
 * stickers and use inline bots
 * @param canAddWebPagePreviews *Optional*. *True*, if the user is allowed to add web page previews
 * to their messages
 * @param canChangeInfo *Optional*. *True*, if the user is allowed to change the chat title, photo
 * and other settings. Ignored in public supergroups
 * @param canInviteUsers *Optional*. *True*, if the user is allowed to invite new users to the chat
 * @param canPinMessages *Optional*. *True*, if the user is allowed to pin messages. Ignored in
 * public supergroups
 * @param canManageTopics *Optional*. *True*, if the user is allowed to create forum topics. If
 * omitted defaults to the value of can_pin_messages
 */
public data class ChatPermissions(
    /**
     * *Optional*. *True*, if the user is allowed to send text messages, contacts, giveaways,
     * giveaway winners, invoices, locations and venues
     */
    @get:JsonProperty("can_send_messages")
    @param:JsonProperty("can_send_messages")
    public val canSendMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send audios
     */
    @get:JsonProperty("can_send_audios")
    @param:JsonProperty("can_send_audios")
    public val canSendAudios: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send documents
     */
    @get:JsonProperty("can_send_documents")
    @param:JsonProperty("can_send_documents")
    public val canSendDocuments: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send photos
     */
    @get:JsonProperty("can_send_photos")
    @param:JsonProperty("can_send_photos")
    public val canSendPhotos: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send videos
     */
    @get:JsonProperty("can_send_videos")
    @param:JsonProperty("can_send_videos")
    public val canSendVideos: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send video notes
     */
    @get:JsonProperty("can_send_video_notes")
    @param:JsonProperty("can_send_video_notes")
    public val canSendVideoNotes: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send voice notes
     */
    @get:JsonProperty("can_send_voice_notes")
    @param:JsonProperty("can_send_voice_notes")
    public val canSendVoiceNotes: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send polls
     */
    @get:JsonProperty("can_send_polls")
    @param:JsonProperty("can_send_polls")
    public val canSendPolls: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to send animations, games, stickers and use inline
     * bots
     */
    @get:JsonProperty("can_send_other_messages")
    @param:JsonProperty("can_send_other_messages")
    public val canSendOtherMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to add web page previews to their messages
     */
    @get:JsonProperty("can_add_web_page_previews")
    @param:JsonProperty("can_add_web_page_previews")
    public val canAddWebPagePreviews: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to change the chat title, photo and other
     * settings. Ignored in public supergroups
     */
    @get:JsonProperty("can_change_info")
    @param:JsonProperty("can_change_info")
    public val canChangeInfo: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to invite new users to the chat
     */
    @get:JsonProperty("can_invite_users")
    @param:JsonProperty("can_invite_users")
    public val canInviteUsers: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to pin messages. Ignored in public supergroups
     */
    @get:JsonProperty("can_pin_messages")
    @param:JsonProperty("can_pin_messages")
    public val canPinMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the user is allowed to create forum topics. If omitted defaults to the
     * value of can_pin_messages
     */
    @get:JsonProperty("can_manage_topics")
    @param:JsonProperty("can_manage_topics")
    public val canManageTopics: Boolean? = null,
)
