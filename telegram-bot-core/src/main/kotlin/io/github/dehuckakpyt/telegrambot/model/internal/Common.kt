package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.*


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal class SendMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("text") val text: String,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("parse_mode") val parseMode: ParseMode? = null,
    @get:JsonProperty("entities") val entities: List<MessageEntity>? = null,
    @get:JsonProperty("link_preview_options") val linkPreviewOptions: LinkPreviewOptions? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class ForwardMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("from_chat_id") val fromChatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
)

internal class ForwardMessages(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("from_chat_id") val fromChatId: String,
    @get:JsonProperty("message_ids") val messageIds: Iterable<Long>,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
)

internal class CopyMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("from_chat_id") val fromChatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: ParseMode? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class CopyMessages(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("from_chat_id") val fromChatId: String,
    @get:JsonProperty("message_ids") val messageIds: Iterable<Long>,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("remove_caption") val removeCaption: Boolean? = null,
)

internal class SendLocation(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @get:JsonProperty("live_period") val livePeriod: Long? = null,
    @get:JsonProperty("heading") val heading: Long? = null,
    @get:JsonProperty("proximity_alert_radius") val proximityAlertRadius: Long? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class EditMessageLiveLocation(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @get:JsonProperty("heading") val heading: Long? = null,
    @get:JsonProperty("proximity_alert_radius") val proximityAlertRadius: Long? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatid and messageid are required if inlinemessageid not provided")
        }
    }
}

internal class StopMessageLiveLocation(
    @get:JsonProperty("chat_id") val chatId: String? = null,
    @get:JsonProperty("message_id") val messageId: Long? = null,
    @get:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
) {
    init {
        if (chatId == null && messageId == null) {
            requireNotNull(
                value = inlineMessageId,
                lazyMessage = { "inlineMessageId is required if chatId and messageId are not provided" }
            )
        }

        if (inlineMessageId == null &&
            (chatId == null || messageId == null)
        ) {
            throw IllegalArgumentException("chatid and messageid are required if inlinemessageid not provided")
        }
    }
}

internal class SendVenue(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("address") val address: String,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("foursquare_id") val foursquareId: String? = null,
    @get:JsonProperty("foursquare_type") val foursquareType: String? = null,
    @get:JsonProperty("google_place_id") val googlePlaceId: String? = null,
    @get:JsonProperty("google_place_type") val googlePlaceType: String? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class SendContact(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("phone_number") val phone: String,
    @get:JsonProperty("first_name") val firstName: String,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("last_name") val lastName: String? = null,
    @get:JsonProperty("vcard") val vcard: String? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class SendChatAction(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("action") val action: Action,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
)

internal class BanChatSenderChat(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("sender_chat_id") val senderChatId: Long,
)

internal class UnbanChatSenderChat(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("sender_chat_id") val senderChatId: Long,
)

internal class SendPoll(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("question") val question: String,
    @get:JsonProperty("options") val options: List<String>,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("is_anonymous") val isAnonymous: Boolean? = null,
    @get:JsonProperty("type") val type: String? = null,
    @get:JsonProperty("allows_multiple_answers") val allowsMultipleAnswers: Boolean? = null,
    @get:JsonProperty("correct_option_id") val correctOptionId: Long? = null,
    @get:JsonProperty("explanation") val explanation: String? = null,
    @get:JsonProperty("explanation_parse_mode") val explanationParseMode: String? = null,
    @get:JsonProperty("explanation_entities") val explanationEntities: List<MessageEntity>? = null,
    @get:JsonProperty("open_period") val openPeriod: Long? = null,
    @get:JsonProperty("close_date") val closeDate: Long? = null,
    @get:JsonProperty("is_closed") val isClosed: Boolean? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
) {
    init {
        if (options.size < 2 || options.size > 10) {
            throw IllegalArgumentException("options size must be in [2, 10]")
        }
    }
}

internal class SendDice(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("business_connection_id") val businessConnectionId: String? = null,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long? = null,
    @get:JsonProperty("emoji") val emoji: String? = null,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
    @get:JsonProperty("protect_content") val protectContent: Boolean? = null,
    @get:JsonProperty("reply_parameters") val replyParameters: ReplyParameters? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: ReplyKeyboard? = null,
)

internal class BanChatMember(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("until_date") val untilDate: Long? = null,
    @get:JsonProperty("revoke_messages") val revokeMessages: Boolean? = null,
)

internal class UnbanChatMember(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("only_if_banned") val onlyIfBanned: Boolean? = null,
)

internal class RestrictChatMember(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("permissions") val permissions: ChatPermissions,
    @get:JsonProperty("use_independent_chat_permissions") val useIndependentChatPermissions: Boolean? = null,
    @get:JsonProperty("until_date") val untilDate: Long? = null,
)

internal class PromoteChatMember(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("is_anonymous") val isAnonymous: Boolean? = null,
    @get:JsonProperty("can_manage_chat") val canManageChat: Boolean? = null,
    @get:JsonProperty("can_delete_messages") val canDeleteMessages: Boolean? = null,
    @get:JsonProperty("can_manage_video_chats") val canManageVideoChats: Boolean? = null,
    @get:JsonProperty("can_restrict_members") val canRestrictMembers: Boolean? = null,
    @get:JsonProperty("can_promote_members") val canPromoteMembers: Boolean? = null,
    @get:JsonProperty("can_change_info") val canChangeInfo: Boolean? = null,
    @get:JsonProperty("can_invite_users") val canInviteUsers: Boolean? = null,
    @get:JsonProperty("can_post_messages") val canPostMessages: Boolean? = null,
    @get:JsonProperty("can_edit_messages") val canEditMessages: Boolean? = null,
    @get:JsonProperty("can_pin_messages") val canPinMessages: Boolean? = null,
    @get:JsonProperty("can_post_stories") val canPostStories: Boolean? = null,
    @get:JsonProperty("can_edit_stories") val canEditStories: Boolean? = null,
    @get:JsonProperty("can_delete_stories") val canDeleteStories: Boolean? = null,
    @get:JsonProperty("can_manage_topics") val canManageTopics: Boolean? = null,
)

internal class SetChatAdministratorCustomTitle(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
    @get:JsonProperty("custom_title") val customTitle: String,
)

internal class SetChatPermissions(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("permissions") val permissions: ChatPermissions,
    @get:JsonProperty("use_independent_chat_permissions") val useIndependentChatPermissions: Boolean? = null,
)

internal class ExportChatInviteLink(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal class CreateChatInviteLink(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("name") val name: String? = null,
    @get:JsonProperty("expire_date") val expireDate: Long? = null,
    @get:JsonProperty("member_limit") val memberLimit: Long? = null,
    @get:JsonProperty("creates_join_request") val createsJoinRequest: Boolean? = null,
)

internal class EditChatInviteLink(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("invite_link") val inviteLink: String,
    @get:JsonProperty("name") val name: String? = null,
    @get:JsonProperty("expire_date") val expireDate: Long? = null,
    @get:JsonProperty("member_limit") val memberLimit: Long? = null,
    @get:JsonProperty("creates_join_request") val createsJoinRequest: Boolean? = null,
)

internal class RevokeChatInviteLink(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("invite_link") val inviteLink: String,
)

internal class ApproveChatJoinRequest(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
)

internal class DeclineChatJoinRequest(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("user_id") val userId: Long,
)

internal class DeleteChatPhoto(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal class SetChatTitle(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("title") val title: String,
)

internal class SetChatDescription(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("description") val description: String,
)

internal class PinChatMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_id") val messageId: Long,
    @get:JsonProperty("disable_notification") val disableNotification: Boolean? = null,
)

internal class UnpinChatMessage(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_id") val messageId: Long? = null,
)

internal class UnpinAllChatMessages(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal class LeaveChat(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal class SetChatStickerSet(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("sticker_set_name") val stickerSetName: String,
)

internal class DeleteChatStickerSet(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal class AnswerCallbackQuery(
    @get:JsonProperty("callback_query_id") val callbackQueryId: String,
    @get:JsonProperty("text") val text: String? = null,
    @get:JsonProperty("show_alert") val showAlert: Boolean? = null,
    @get:JsonProperty("url") val url: String? = null,
    @get:JsonProperty("cache_time") val cacheTime: Long? = null,
)

internal class SetMyCommands(
    @get:JsonProperty("commands") val commands: List<BotCommand>,
    @get:JsonProperty("scope") val scope: BotCommandScope? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)

internal class DeleteMyCommands(
    @get:JsonProperty("scope") val scope: BotCommandScope? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)

internal class GetMyCommands(
    @get:JsonProperty("scope") val scope: BotCommandScope? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)

internal class SetMyName(
    @get:JsonProperty("name") val name: String? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)

internal class SetMyDescription(
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)

internal class SetMyShortDescription(
    @get:JsonProperty("short_description") val shortDescription: String? = null,
    @get:JsonProperty("language_code") val languageCode: String? = null,
)
