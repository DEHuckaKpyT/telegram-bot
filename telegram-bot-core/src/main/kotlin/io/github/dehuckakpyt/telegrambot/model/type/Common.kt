package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class WebhookInfo(
    @param:JsonProperty("url") val url: String,
    @param:JsonProperty("has_custom_certificate") val hasCustomCertificate: Boolean,
    @param:JsonProperty("pending_update_count") val pendingUpdateCount: Int,
    @param:JsonProperty("ip_address") val ipAddress: String? = null,
    @param:JsonProperty("last_error_date") val lastErrorDate: Long? = null,
    @param:JsonProperty("last_error_message") val lastErrorMessage: String? = null,
    @param:JsonProperty("last_synchronization_error_date") val lastSynchronizationErrorDate: Long? = null,
    @param:JsonProperty("max_connections") val maxConnections: Int? = null,
    @param:JsonProperty("allowed_updates") val allowedUpdates: List<AllowedUpdate>? = null,
)

public data class User(
    @get:JsonProperty("id") @param:JsonProperty("id") val id: Long,
    @get:JsonProperty("is_bot") @param:JsonProperty("is_bot") val isBot: Boolean,
    @get:JsonProperty("first_name") @param:JsonProperty("first_name") val firstName: String,
    @get:JsonProperty("last_name") @param:JsonProperty("last_name") val lastName: String? = null,
    @get:JsonProperty("username") @param:JsonProperty("username") val username: String? = null,
    @get:JsonProperty("language_code") @param:JsonProperty("language_code") val languageCode: String? = null,
    @get:JsonProperty("is_premium") @param:JsonProperty("is_premium") val isPremium: Boolean? = null,
    @get:JsonProperty("added_to_attachment_menu") @param:JsonProperty("added_to_attachment_menu") val addedToAttachmentMenu: Boolean? = null,
    @get:JsonProperty("can_join_groups") @param:JsonProperty("can_join_groups") val canJoinGroups: Boolean? = null,
    @get:JsonProperty("can_read_all_group_messages") @param:JsonProperty("can_read_all_group_messages") val canReadAllGroupMessages: Boolean? = null,
    @get:JsonProperty("supports_inline_queries") @param:JsonProperty("supports_inline_queries") val supportsInlineQueries: Boolean? = null,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "date", visible = true, defaultImpl = Message::class)
@JsonSubTypes(
    JsonSubTypes.Type(value = InaccessibleMessage::class, name = "0"),
)
public sealed class MaybeInaccessibleMessage {
    abstract val date: Long

    abstract val messageId: Long
    abstract val chat: Chat
}

@JsonTypeName("0")
public data class InaccessibleMessage(
    @param:JsonProperty("chat") override val chat: Chat,
    @param:JsonProperty("message_id") override val messageId: Long,
    @param:JsonProperty("date") override val date: Long,
) : MaybeInaccessibleMessage()

public data class Message(
    @param:JsonProperty("message_id") override val messageId: Long,
    @param:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @param:JsonProperty("from") val from: User? = null,
    @param:JsonProperty("sender_chat") val senderChat: Chat? = null,
    @param:JsonProperty("date") override val date: Long,
    @param:JsonProperty("chat") override val chat: Chat,
    @param:JsonProperty("forward_origin") val forwardOrigin: MessageOrigin? = null,
    @param:JsonProperty("is_topic_message") val isTopicMessage: Boolean? = null,
    @param:JsonProperty("is_automatic_forward") val isAutomaticForward: Boolean? = null,
    @param:JsonProperty("reply_to_message") val replyToMessage: Message? = null,
    @param:JsonProperty("external_reply") val externalReply: ExternalReplyInfo? = null,
    @param:JsonProperty("quote") val quote: TextQuote? = null,
    @param:JsonProperty("via_bot") val viaBot: User? = null,
    @param:JsonProperty("edit_date") val editDate: Long? = null,
    @param:JsonProperty("has_protected_content") val hasProtectedContent: Boolean? = null,
    @param:JsonProperty("media_group_id") val mediaGroupId: String? = null,
    @param:JsonProperty("author_signature") val authorSignature: String? = null,
    @param:JsonProperty("text") val text: String? = null,
    @param:JsonProperty("entities") val entities: List<MessageEntity> = emptyList(),
    @param:JsonProperty("link_preview_options") val linkPreviewOptions: LinkPreviewOptions? = null,
    @param:JsonProperty("animation") val animation: Animation? = null,
    @param:JsonProperty("audio") val audio: Audio? = null,
    @param:JsonProperty("document") val document: Document? = null,
    @param:JsonProperty("photo") val photo: List<PhotoSize> = emptyList(),
    @param:JsonProperty("sticker") val sticker: Sticker? = null,
    @param:JsonProperty("story") val story: Story? = null,
    @param:JsonProperty("video") val video: Video? = null,
    @param:JsonProperty("video_note") val videoNote: VideoNote? = null,
    @param:JsonProperty("voice") val voice: Voice? = null,
    @param:JsonProperty("caption") val caption: String? = null,
    @param:JsonProperty("caption_entities") val captionEntities: List<MessageEntity> = emptyList(),
    @param:JsonProperty("has_media_spoiler") val hasMediaSpoiler: Boolean? = null,
    @param:JsonProperty("contact") val contact: Contact? = null,
    @param:JsonProperty("dice") val dice: Dice? = null,
    @param:JsonProperty("game") val game: Game? = null,
    @param:JsonProperty("poll") val poll: Poll? = null,
    @param:JsonProperty("venue") val venue: Venue? = null,
    @param:JsonProperty("location") val location: Location? = null,
    @param:JsonProperty("new_chat_members") val newChatMembers: List<User> = emptyList(),
    @param:JsonProperty("left_chat_member") val leftChatMember: User? = null,
    @param:JsonProperty("new_chat_title") val newChatTitle: String? = null,
    @param:JsonProperty("new_chat_photo") val newChatPhoto: List<PhotoSize> = emptyList(),
    @param:JsonProperty("delete_chat_photo") val deleteChatPhoto: Boolean? = null,
    @param:JsonProperty("group_chat_created") val groupChatCreated: Boolean? = null,
    @param:JsonProperty("supergroup_chat_created") val supergroupChatCreated: Boolean? = null,
    @param:JsonProperty("channel_chat_created") val channelChatCreated: Boolean? = null,
    @param:JsonProperty("message_auto_delete_timer_changed") val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged? = null,
    @param:JsonProperty("migrate_to_chat_id") val migrateToString: Long? = null,
    @param:JsonProperty("migrate_from_chat_id") val migrateFromString: Long? = null,
    @param:JsonProperty("pinned_message") val pinnedMessage: MaybeInaccessibleMessage? = null,
    @param:JsonProperty("invoice") val invoice: Invoice? = null,
    @param:JsonProperty("successful_payment") val successfulPayment: SuccessfulPayment? = null,
    @param:JsonProperty("users_shared") val usersShared: UsersShared? = null,
    @param:JsonProperty("chat_shared") val chatShared: ChatShared? = null,
    @param:JsonProperty("connected_website") val connectedWebsite: String? = null,
    @param:JsonProperty("write_access_allowed") val writeAccessAllowed: WriteAccessAllowed? = null,
    @param:JsonProperty("passport_data") val passportData: PassportData? = null,
    @param:JsonProperty("proximity_alert_triggered") val proximityAlertTriggered: ProximityAlertTriggered? = null,
    @param:JsonProperty("forum_topic_created") val forumTopicCreated: ForumTopicCreated? = null,
    @param:JsonProperty("forum_topic_edited") val forumTopicEdited: ForumTopicEdited? = null,
    @param:JsonProperty("forum_topic_closed") val forumTopicClosed: ForumTopicClosed? = null,
    @param:JsonProperty("forum_topic_reopened") val forumTopicReopened: ForumTopicReopened? = null,
    @param:JsonProperty("general_forum_topic_hidden") val generalForumTopicHidden: GeneralForumTopicHidden? = null,
    @param:JsonProperty("general_forum_topic_unhidden") val generalForumTopicUnhidden: GeneralForumTopicUnhidden? = null,
    @param:JsonProperty("giveaway_created") val giveawayCreated: GiveawayCreated? = null,
    @param:JsonProperty("giveaway") val giveaway: Giveaway? = null,
    @param:JsonProperty("giveaway_winners") val giveawayWinners: GiveawayWinners? = null,
    @param:JsonProperty("giveaway_completed") val giveawayCompleted: GiveawayCompleted? = null,
    @param:JsonProperty("video_chat_scheduled") val videoChatScheduled: VideoChatScheduled? = null,
    @param:JsonProperty("video_chat_started") val videoChatStarted: VideoChatStarted? = null,
    @param:JsonProperty("video_chat_ended") val videoChatEnded: VideoChatEnded? = null,
    @param:JsonProperty("video_chat_participants_invited") val voiceChatParticipantsInvited: VoiceChatParticipantsInvited? = null,
    @param:JsonProperty("web_app_data") val webAppData: WebAppData? = null,
    @param:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) : MaybeInaccessibleMessage()

public data class MessageId(
    @param:JsonProperty("message_id") val messageId: Long,
)

public data class CallbackQuery(
    @param:JsonProperty("id") val id: String,
    @param:JsonProperty("from") val from: User,
    @param:JsonProperty("message") val message: MaybeInaccessibleMessage? = null,
    @param:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @param:JsonProperty("chat_instance") val chatInstance: String,
    @param:JsonProperty("data") val data: String? = null,
    @param:JsonProperty("game_short_name") val gameShortName: String? = null,
)

public data class MessageEntity(
    @get:JsonProperty("type") @param:JsonProperty("type") val type: Type,
    @get:JsonProperty("offset") @param:JsonProperty("offset") val offset: Int,
    @get:JsonProperty("length") @param:JsonProperty("length") val length: Int,
    @get:JsonProperty("url") @param:JsonProperty("url") val url: String? = null,
    @get:JsonProperty("user") @param:JsonProperty("user") val user: User? = null,
    @get:JsonProperty("language") @param:JsonProperty("language") val language: String? = null,
    @get:JsonProperty("custom_emoji_id") @param:JsonProperty("custom_emoji_id") val customEmojiId: String? = null,
) {

    public enum class Type {
        @field:JsonProperty("mention")
        MENTION,

        @field:JsonProperty("hashtag")
        HASHTAG,

        @field:JsonProperty("cashtag")
        CASHTAG,

        @field:JsonProperty("bot_command")
        BOT_COMMAND,

        @field:JsonProperty("url")
        URL,

        @field:JsonProperty("email")
        EMAIL,

        @field:JsonProperty("phone_number")
        PHONE_NUMBER,

        @field:JsonProperty("bold")
        BOLD,

        @field:JsonProperty("italic")
        ITALIC,

        @field:JsonProperty("underline")
        UNDERLINE,

        @field:JsonProperty("strikethrough")
        STRIKE_THROUGH,

        @field:JsonProperty("spoiler")
        SPOILER,

        @field:JsonProperty("blockquote")
        BLOCKQUOTE,

        @field:JsonProperty("code")
        CODE,

        @field:JsonProperty("pre")
        PRE,

        @field:JsonProperty("text_link")
        TEXT_LINK,

        @field:JsonProperty("text_mention")
        TEXT_MENTION,

        @field:JsonProperty("custom_emoji")
        CUSTOM_EMOJI,
    }
}

public data class PhotoSize(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("width") val width: Int,
    @param:JsonProperty("height") val height: Int,
    @param:JsonProperty("file_size") val fileSize: Int? = null,
)

public data class Audio(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("duration") val duration: Int,
    @param:JsonProperty("performer") val performer: String? = null,
    @param:JsonProperty("title") val title: String? = null,
    @param:JsonProperty("file_name") val fileName: String? = null,
    @param:JsonProperty("mime_type") val mimeType: String? = null,
    @param:JsonProperty("file_size") val fileSize: Long? = null,
    @param:JsonProperty("thumbnail") val thumbnail: PhotoSize? = null,
)

public data class Document(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("thumbnail") val thumbnail: PhotoSize? = null,
    @param:JsonProperty("file_name") val fileName: String? = null,
    @param:JsonProperty("mime_type") val mimeType: String? = null,
    @param:JsonProperty("file_size") val fileSize: Long? = null,
)

public class Story

public data class Video(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("width") val width: Int,
    @param:JsonProperty("height") val height: Int,
    @param:JsonProperty("duration") val duration: Int,
    @param:JsonProperty("thumbnail") val thumbnail: PhotoSize? = null,
    @param:JsonProperty("file_name") val fileName: String? = null,
    @param:JsonProperty("mime_type") val mimeType: String? = null,
    @param:JsonProperty("file_size") val fileSize: Long? = null,
)

public data class Animation(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("width") val width: Int,
    @param:JsonProperty("height") val height: Int,
    @param:JsonProperty("duration") val duration: Int,
    @param:JsonProperty("thumbnail") val thumbnail: PhotoSize? = null,
    @param:JsonProperty("file_name") val fileName: String? = null,
    @param:JsonProperty("mime_type") val mimeType: String? = null,
    @param:JsonProperty("fil_size") val fileSize: Long? = null,
)

public data class Voice(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("duration") val duration: Int,
    @param:JsonProperty("mim_type") val mimeType: String? = null,
    @param:JsonProperty("file_size") val fileSize: Long? = null,
)

public data class VideoNote(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("length") val length: Int,
    @param:JsonProperty("duration") val duration: Int,
    @param:JsonProperty("thumbnail") val thumbnail: PhotoSize? = null,
    @param:JsonProperty("file_size") val fileSize: Int? = null,
)

public data class Contact(
    @param:JsonProperty("phone_number") val phoneNumber: String,
    @param:JsonProperty("first_name") val firstName: String,
    @param:JsonProperty("last_name") val lastName: String? = null,
    @param:JsonProperty("user_id") val userId: Int? = null,
    @param:JsonProperty("vcard") val vcard: String? = null,
)

public data class Location(
    @param:JsonProperty("longitude") val longitude: Float,
    @param:JsonProperty("latitude") val latitude: Float,
    @param:JsonProperty("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @param:JsonProperty("live_period") val livePeriod: Int? = null,
    @param:JsonProperty("heading") val heading: Int? = null,
    @param:JsonProperty("proximity_alert_radius") val proximityAlertRadius: Int? = null,
)

public data class Venue(
    @param:JsonProperty("location") val location: Location? = null,
    @param:JsonProperty("title") val title: String,
    @param:JsonProperty("address") val address: String,
    @param:JsonProperty("foursquare_id") val foursquareId: String? = null,
    @param:JsonProperty("foursquare_type") val foursquareType: String? = null,
    @param:JsonProperty("google_place_id") val googlePlaceId: String? = null,
    @param:JsonProperty("google_place_type") val googlePlaceType: String? = null,
)

public data class ProximityAlertTriggered(
    @param:JsonProperty("traveler") val traveler: User,
    @param:JsonProperty("watcher") val watcher: User,
    @param:JsonProperty("distance") val distance: Int,
)

public data class UserProfilePhotos(
    @param:JsonProperty("total_count") val totalCount: Int,
    @param:JsonProperty("photos") val photos: List<List<PhotoSize>>,
)

public data class File(
    @param:JsonProperty("file_id") val fileId: String,
    @param:JsonProperty("file_unique_id") val fileUniqueId: String,
    @param:JsonProperty("file_size") val fileSize: Long? = null,
    @param:JsonProperty("file_path") val filePath: String? = null,
)

public data class ResponseParameters(
    @param:JsonProperty("migrate_to_chat_id") val migrateToString: Long,
    @param:JsonProperty("retry_after") val retryAfter: Int,
)

public data class Dice(
    @param:JsonProperty("emoji") val emoji: String,
    @param:JsonProperty("value") val value: Int,
)

public data class BotCommand(
    @get:JsonProperty("command") @param:JsonProperty("command") val command: String,
    @get:JsonProperty("description") @param:JsonProperty("description") val description: String,
)

public data class MessageAutoDeleteTimerChanged(
    @param:JsonProperty("message_auto_delete_time") val messageAutoDeleteTime: Long,
)

public data class ForumTopicCreated(
    @param:JsonProperty("name") val name: String,
    @param:JsonProperty("icon_color") val iconColor: Int,
    @param:JsonProperty("icon_custom_emoji_id") val iconCustomEmojiId: Int?,
)

public class ForumTopicClosed

public class ForumTopicReopened

public data class VideoChatScheduled(
    @param:JsonProperty("start_date") val startDate: Long,
)

public class VideoChatStarted

public data class VideoChatEnded(
    @param:JsonProperty("duration") val duration: Long,
)

public data class VoiceChatParticipantsInvited(
    @param:JsonProperty("users") val users: List<User> = emptyList(),
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeDefault::class, name = "default"),
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeAllPrivateChats::class, name = "all_private_chats"),
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeAllGroupChats::class, name = "all_group_chats"),
    JsonSubTypes.Type(
        value = BotCommandScope.BotCommandScopeAllChatAdministrators::class, name = "all_chat_administrators"
    ),
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeChat::class, name = "chat"),
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeChatAdministrators::class, name = "chat_administrators"),
    JsonSubTypes.Type(value = BotCommandScope.BotCommandScopeChatMember::class, name = "chat_member"),
)
public sealed class BotCommandScope {

    public abstract val type: String

    @JsonTypeName("default")
    public data class BotCommandScopeDefault(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
    ) : BotCommandScope()

    @JsonTypeName("all_private_chats")
    public data class BotCommandScopeAllPrivateChats(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
    ) : BotCommandScope()

    @JsonTypeName("all_group_chats")
    public data class BotCommandScopeAllGroupChats(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
    ) : BotCommandScope()

    @JsonTypeName("all_chat_administrators")
    public data class BotCommandScopeAllChatAdministrators(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
    ) : BotCommandScope()

    @JsonTypeName("chat")
    public data class BotCommandScopeChat(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
        @get:JsonProperty("chat_id") @param:JsonProperty("chat_id") val chatId: String,
    ) : BotCommandScope()

    @JsonTypeName("chat_administrators")
    public data class BotCommandScopeChatAdministrators(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
        @get:JsonProperty("chat_id") @param:JsonProperty("chat_id") val chatId: String,
    ) : BotCommandScope()

    @JsonTypeName("chat_member")
    public data class BotCommandScopeChatMember(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String,
        @get:JsonProperty("chat_id") @param:JsonProperty("chat_id") val chatId: String,
    ) : BotCommandScope()
}

public data class LoginUrl(
    @get:JsonProperty("url") @param:JsonProperty("url") val url: String,
    @get:JsonProperty("forward_text") @param:JsonProperty("forward_text") val forwardText: String? = null,
    @get:JsonProperty("bot_username") @param:JsonProperty("bot_username") val botUsername: String? = null,
    @get:JsonProperty("request_write_access") @param:JsonProperty("request_write_access") val requestWriteAccess: Boolean? = null,
)

public enum class ParseMode {

    @field:JsonProperty("HTML")
    HTML,

    @field:JsonProperty("Markdown")
    Markdown,

    @field:JsonProperty("MarkdownV2")
    MarkdownV2
}

public data class UsersShared(
    @param:JsonProperty("request_id") val requestId: Long,
    @param:JsonProperty("user_ids") val userIds: List<Long>,
)

public data class ChatShared(
    @param:JsonProperty("request_id") val requestId: Long,
    @param:JsonProperty("chat_id") val chatId: Long,
)

public enum class Action {
    @field:JsonProperty("typing")
    Typing,

    @field:JsonProperty("upload_photo")
    UploadPhoto,

    @field:JsonProperty("record_video")
    RecordVideo,

    @field:JsonProperty("upload_video")
    UploadVideo,

    @field:JsonProperty("record_audio")
    RecordAudio,

    @field:JsonProperty("upload_audio")
    UploadAudio,

    @field:JsonProperty("upload_document")
    UploadDocument,

    @field:JsonProperty("find_location")
    FindLocation,

    @field:JsonProperty("choose_sticker")
    ChooseSticker,

    @field:JsonProperty("record_video_note")
    RecordVideoNote,

    @field:JsonProperty("upload_video_note")
    UploadVideoNote,
}

public data class BotName(
    @param:JsonProperty("name") val name: String,
)

public data class BotDescription(
    @param:JsonProperty("description") val description: String,
)

public data class BotShortDescription(
    @param:JsonProperty("short_description") val shortDescription: String,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = MessageOrigin.MessageOriginUser::class, name = "user"),
    JsonSubTypes.Type(value = MessageOrigin.MessageOriginHiddenUser::class, name = "hidden_user"),
    JsonSubTypes.Type(value = MessageOrigin.MessageOriginChat::class, name = "chat"),
    JsonSubTypes.Type(value = MessageOrigin.MessageOriginChannel::class, name = "channel"),
)
public sealed class MessageOrigin {

    public abstract val type: String

    @JsonTypeName("user")
    public data class MessageOriginUser(
        @param:JsonProperty("type") override val type: String = "user",
        @param:JsonProperty("date") val date: Long,
        @param:JsonProperty("sender_user") val senderUser: User,
    ) : MessageOrigin()

    @JsonTypeName("hidden_user")
    public data class MessageOriginHiddenUser(
        @param:JsonProperty("type") override val type: String = "hidden_user",
        @param:JsonProperty("date") val date: Long,
        @param:JsonProperty("sender_user_name") val senderUserName: String,
    ) : MessageOrigin()

    @JsonTypeName("chat")
    public data class MessageOriginChat(
        @param:JsonProperty("type") override val type: String = "chat",
        @param:JsonProperty("date") val date: Long,
        @param:JsonProperty("sender_chat") val senderChat: Chat,
        @param:JsonProperty("author_signature") val authorSignature: String? = null,
    ) : MessageOrigin()

    @JsonTypeName("channel")
    public data class MessageOriginChannel(
        @param:JsonProperty("type") override val type: String = "channel",
        @param:JsonProperty("date") val date: Long,
        @param:JsonProperty("chat") val chat: Chat,
        @param:JsonProperty("message_id") val messageId: Long,
        @param:JsonProperty("author_signature") val authorSignature: String? = null,
    ) : MessageOrigin()
}

public data class LinkPreviewOptions(
    @get:JsonProperty("is_disabled") @param:JsonProperty("is_disabled") val isDisabled: Boolean? = null,
    @get:JsonProperty("url") @param:JsonProperty("url") val url: String? = null,
    @get:JsonProperty("prefer_small_media") @param:JsonProperty("prefer_small_media") val preferSmallMedia: Boolean? = null,
    @get:JsonProperty("prefer_large_media") @param:JsonProperty("prefer_large_media") val preferLargeMedia: Boolean? = null,
    @get:JsonProperty("show_above_text") @param:JsonProperty("show_above_text") val showAboveText: Boolean? = null,
)

public data class ExternalReplyInfo(
    @param:JsonProperty("origin") val origin: MessageOrigin,
    @param:JsonProperty("chat") val chat: Chat? = null,
    @param:JsonProperty("message_id") val messageId: Long? = null,
    @param:JsonProperty("link_preview_options") val linkPreviewOptions: LinkPreviewOptions? = null,
    @param:JsonProperty("animation") val animation: Animation? = null,
    @param:JsonProperty("audio") val audio: Audio? = null,
    @param:JsonProperty("document") val document: Document? = null,
    @param:JsonProperty("photo") val photo: List<PhotoSize>? = null,
    @param:JsonProperty("sticker") val sticker: Sticker? = null,
    @param:JsonProperty("story") val story: Story? = null,
    @param:JsonProperty("video") val video: Video? = null,
    @param:JsonProperty("video_note") val videoNote: VideoNote? = null,
    @param:JsonProperty("voice") val voice: Voice? = null,
    @param:JsonProperty("has_media_spoiler") val hasMediaSpoiler: Boolean? = null,
    @param:JsonProperty("contact") val contact: Contact? = null,
    @param:JsonProperty("dice") val dice: Dice? = null,
    @param:JsonProperty("game") val game: Game? = null,
    @param:JsonProperty("giveaway") val giveaway: Giveaway? = null,
    @param:JsonProperty("giveaway_winners") val giveawayWinners: GiveawayWinners? = null,
    @param:JsonProperty("invoice") val invoice: Invoice? = null,
    @param:JsonProperty("location") val location: Location? = null,
    @param:JsonProperty("poll") val poll: Poll? = null,
    @param:JsonProperty("venue") val venue: Venue? = null,
)

public data class TextQuote(
    @param:JsonProperty("text") val text: String,
    @param:JsonProperty("entities") val entities: List<MessageEntity> = emptyList(),
    @param:JsonProperty("position") val position: Int,
    @param:JsonProperty("is_manual") val isManual: Boolean? = null,
)

public data class ReplyParameters(
    @param:JsonProperty("message_id") val messageId: Long,
    @param:JsonProperty("chat_id") val chatId: String? = null,
    @param:JsonProperty("allow_sending_without_reply") val allowSendingWithoutReply: Boolean? = null,
    @param:JsonProperty("quote") val quote: String? = null,
    @param:JsonProperty("quote_parse_mode") val quoteParseMode: ParseMode? = null,
    @param:JsonProperty("quote_entities") val quoteEntities: List<MessageEntity>? = null,
    @param:JsonProperty("quote_position") val quotePosition: Int? = null,
)