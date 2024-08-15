package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.client.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.ext.appendContent
import io.github.dehuckakpyt.telegrambot.ext.appendContentIfNotNull
import io.github.dehuckakpyt.telegrambot.ext.appendIfNotNull
import io.github.dehuckakpyt.telegrambot.ext.toJson
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.AnswerCallbackQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.AnswerInlineQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.AnswerPreCheckoutQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.AnswerShippingQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.AnswerWebAppQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ApproveChatJoinRequest
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.BanChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.BanChatSenderChat
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CloseForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CloseGeneralForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CopyMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CopyMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CreateChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CreateChatSubscriptionInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CreateForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.CreateInvoiceLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeclineChatJoinRequest
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteChatPhoto
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteChatStickerSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteMyCommands
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteStickerFromSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteStickerSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.DeleteWebhook
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditChatSubscriptionInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditGeneralForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageCaptionByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageCaptionByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageLiveLocationByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageLiveLocationByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageReplyMarkupByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageReplyMarkupByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageTextByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.EditMessageTextByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ExportChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ForwardMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ForwardMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetBusinessConnection
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetChat
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetChatAdministrators
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetChatMemberCount
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetChatMenuButton
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetCustomEmojiStickers
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetFile
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetGameHighScores
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetMyCommands
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetMyDefaultAdministratorRights
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetMyDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetMyName
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetMyShortDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetStarTransactions
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetStickerSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetUpdates
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetUserChatBoosts
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.GetUserProfilePhotos
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.HideGeneralForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.LeaveChat
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.PinChatMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.PromoteChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.RefundStarPayment
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ReopenForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.ReopenGeneralForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.RestrictChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.RevokeChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendChatAction
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendContact
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendDice
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendGame
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendInvoice
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendLocation
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SendVenue
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatAdministratorCustomTitle
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatMenuButton
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatPermissions
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatStickerSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetChatTitle
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetCustomEmojiStickerSetThumbnail
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetGameScoreByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetGameScoreByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMessageReaction
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMyCommands
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMyDefaultAdministratorRights
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMyDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMyName
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetMyShortDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetPassportDataErrors
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetStickerEmojiList
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetStickerKeywords
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetStickerMaskPosition
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetStickerPositionInSet
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.SetStickerSetTitle
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.StopMessageLiveLocationByChatIdAndMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.StopMessageLiveLocationByInlineMessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.StopPoll
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnbanChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnbanChatSenderChat
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnhideGeneralForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnpinAllChatMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnpinAllForumTopicMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnpinAllGeneralForumTopicMessages
import io.github.dehuckakpyt.telegrambot.model.telegram.`internal`.UnpinChatMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List

/**
 * Created on 15.08.2024.
 *
 * @author KScript
 */
public class TelegramBotImpl(
    private val token: String,
    override val username: String,
    private val messageSource: MessageSource,
) : TelegramBot {
    override val client: TelegramApiClient = TelegramApiClient(token)

    override suspend fun getUpdates(
        offset: Long?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: Iterable<String>?,
    ): List<Update> = client.postJson("getUpdates", GetUpdates(
            offset = offset, 
            limit = limit, 
            timeout = timeout, 
            allowedUpdates = allowedUpdates
        )
    )

    override suspend fun setWebhook(
        url: String,
        certificate: ContentInput?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: Iterable<String>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ): Boolean = client.postMultiPart("setWebhook") {
        append("url", url)
        appendContentIfNotNull("certificate", certificate)
        appendIfNotNull("ip_address", ipAddress)
        appendIfNotNull("max_connections", maxConnections)
        appendIfNotNull("allowed_updates", client.toJson(allowedUpdates))
        appendIfNotNull("drop_pending_updates", dropPendingUpdates)
        appendIfNotNull("secret_token", secretToken)
    }

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean =
            client.postJson("deleteWebhook", DeleteWebhook(
            dropPendingUpdates = dropPendingUpdates
        )
    )

    override suspend fun getWebhookInfo(): WebhookInfo = client.get("getWebhookInfo")

    override suspend fun getMe(): User = client.get("getMe")

    override suspend fun logOut(): Boolean = client.get("logOut")

    override suspend fun close(): Boolean = client.get("close")

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendMessage", SendMessage(
            chatId = chatId, 
            text = text, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            parseMode = parseMode, 
            entities = entities, 
            linkPreviewOptions = linkPreviewOptions, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "TEXT", text = text) }

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): Message = client.postJson("forwardMessage", ForwardMessage(
            chatId = chatId, 
            fromChatId = fromChatId, 
            messageId = messageId, 
            messageThreadId = messageThreadId, 
            disableNotification = disableNotification, 
            protectContent = protectContent
        )
    )

    override suspend fun forwardMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): List<MessageId> = client.postJson("forwardMessages", ForwardMessages(
            chatId = chatId, 
            fromChatId = fromChatId, 
            messageIds = messageIds, 
            messageThreadId = messageThreadId, 
            disableNotification = disableNotification, 
            protectContent = protectContent
        )
    )

    override suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): MessageId = client.postJson("copyMessage", CopyMessage(
            chatId = chatId, 
            fromChatId = fromChatId, 
            messageId = messageId, 
            messageThreadId = messageThreadId, 
            caption = caption, 
            parseMode = parseMode, 
            captionEntities = captionEntities, 
            showCaptionAboveMedia = showCaptionAboveMedia, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun copyMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = client.postJson("copyMessages", CopyMessages(
            chatId = chatId, 
            fromChatId = fromChatId, 
            messageIds = messageIds, 
            messageThreadId = messageThreadId, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            removeCaption = removeCaption
        )
    )

    override suspend fun sendPhoto(
        chatId: String,
        photo: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendPhoto") {
        append("chat_id", chatId)
        appendContent("photo", photo)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "PHOTO", text = caption, fileIds = it.photo!!.map(PhotoSize::fileId)) }

    override suspend fun sendAudio(
        chatId: String,
        audio: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        duration: Int?,
        performer: String?,
        title: String?,
        thumbnail: ContentInput?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendAudio") {
        append("chat_id", chatId)
        appendContent("audio", audio)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("duration", duration)
        appendIfNotNull("performer", performer)
        appendIfNotNull("title", title)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "AUDIO", text = caption, fileIds = listOf(it.audio!!.fileId)) }

    override suspend fun sendDocument(
        chatId: String,
        document: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        thumbnail: ContentInput?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendDocument") {
        append("chat_id", chatId)
        appendContent("document", document)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "DOCUMENT", text = caption, fileIds = listOf(it.document!!.fileId)) }

    override suspend fun sendVideo(
        chatId: String,
        video: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        width: Int?,
        height: Int?,
        thumbnail: ContentInput?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVideo") {
        append("chat_id", chatId)
        appendContent("video", video)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("supports_streaming", supportsStreaming)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "VIDEO", text = caption, fileIds = listOf(it.video!!.fileId)) }

    override suspend fun sendAnimation(
        chatId: String,
        animation: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        width: Int?,
        height: Int?,
        thumbnail: ContentInput?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendAnimation") {
        append("chat_id", chatId)
        appendContent("animation", animation)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "ANIMATION", text = caption, fileIds = listOf(it.animation!!.fileId)) }

    override suspend fun sendVoice(
        chatId: String,
        voice: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        duration: Int?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVoice") {
        append("chat_id", chatId)
        appendContent("voice", voice)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("duration", duration)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "VOICE", text = caption, fileIds = listOf(it.voice!!.fileId)) }

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        length: Int?,
        thumbnail: ContentInput?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVideoNote") {
        append("chat_id", chatId)
        appendContent("video_note", videoNote)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("length", length)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "VIDEO_NOTE", fileIds = listOf(it.videoNote!!.fileId)) }

    override suspend fun sendPaidMedia(
        chatId: String,
        starCount: Int,
        media: Iterable<InputPaidMedia>,
        businessConnectionId: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendPaidMedia") {
        append("chat_id", chatId)
        append("star_count", starCount)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
        
        media.forEach { media ->
            appendContent(media.media)
        }
        
        media.forEach { media ->
            appendContentIfNotNull(media.thumbnail)
        }
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "PAID_MEDIA", text = caption) }

    override suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
    ): List<Message> = client.postMultiPart("sendMediaGroup") {
        append("chat_id", chatId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        
        media.forEach { media ->
            appendContent(media.media)
        }
        
        media.forEach { media ->
            appendContentIfNotNull(media.thumbnail)
        }
    }

    override suspend fun sendLocation(
        chatId: String,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String?,
        messageThreadId: Long?,
        horizontalAccuracy: Double?,
        livePeriod: Int?,
        heading: Int?,
        proximityAlertRadius: Int?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendLocation", SendLocation(
            chatId = chatId, 
            latitude = latitude, 
            longitude = longitude, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            horizontalAccuracy = horizontalAccuracy, 
            livePeriod = livePeriod, 
            heading = heading, 
            proximityAlertRadius = proximityAlertRadius, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "LOCATION", text = "latitude = $latitude, longitude = $longitude") }

    override suspend fun sendVenue(
        chatId: String,
        latitude: Double,
        longitude: Double,
        title: String,
        address: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        foursquareId: String?,
        foursquareType: String?,
        googlePlaceId: String?,
        googlePlaceType: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendVenue", SendVenue(
            chatId = chatId, 
            latitude = latitude, 
            longitude = longitude, 
            title = title, 
            address = address, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            foursquareId = foursquareId, 
            foursquareType = foursquareType, 
            googlePlaceId = googlePlaceId, 
            googlePlaceType = googlePlaceType, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "VENUE", text = "latitude = $latitude, longitude = $longitude, title = $title, address = $address") }

    override suspend fun sendContact(
        chatId: String,
        phoneNumber: String,
        firstName: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        lastName: String?,
        vcard: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendContact", SendContact(
            chatId = chatId, 
            phoneNumber = phoneNumber, 
            firstName = firstName, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            lastName = lastName, 
            vcard = vcard, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "CONTACT", text = "phoneNumber = $phoneNumber, firstName = $firstName") }

    override suspend fun sendPoll(
        chatId: String,
        question: String,
        options: Iterable<InputPollOption>,
        businessConnectionId: String?,
        messageThreadId: Long?,
        questionParseMode: String?,
        questionEntities: Iterable<MessageEntity>?,
        isAnonymous: Boolean?,
        type: String?,
        allowsMultipleAnswers: Boolean?,
        correctOptionId: Long?,
        explanation: String?,
        explanationParseMode: String?,
        explanationEntities: Iterable<MessageEntity>?,
        openPeriod: Int?,
        closeDate: Long?,
        isClosed: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendPoll") {
        append("chat_id", chatId)
        append("question", question)
        append("options", client.toJson(options))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("question_parse_mode", questionParseMode)
        appendIfNotNull("question_entities", client.toJson(questionEntities))
        appendIfNotNull("is_anonymous", isAnonymous)
        appendIfNotNull("type", type)
        appendIfNotNull("allows_multiple_answers", allowsMultipleAnswers)
        appendIfNotNull("correct_option_id", correctOptionId)
        appendIfNotNull("explanation", explanation)
        appendIfNotNull("explanation_parse_mode", explanationParseMode)
        appendIfNotNull("explanation_entities", client.toJson(explanationEntities))
        appendIfNotNull("open_period", openPeriod)
        appendIfNotNull("close_date", closeDate)
        appendIfNotNull("is_closed", isClosed)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "POLL", text = question) }

    override suspend fun sendDice(
        chatId: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendDice", SendDice(
            chatId = chatId, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            emoji = emoji, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "DICE", text = emoji) }

    override suspend fun sendChatAction(
        chatId: String,
        action: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
    ): Boolean = client.postJson("sendChatAction", SendChatAction(
            chatId = chatId, 
            action = action, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId
        )
    )

    override suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>?,
        isBig: Boolean?,
    ): Boolean = client.postJson("setMessageReaction", SetMessageReaction(
            chatId = chatId, 
            messageId = messageId, 
            reaction = reaction, 
            isBig = isBig
        )
    )

    override suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Int?,
        limit: Int?,
    ): UserProfilePhotos = client.postJson("getUserProfilePhotos", GetUserProfilePhotos(
            userId = userId, 
            offset = offset, 
            limit = limit
        )
    )

    override suspend fun getFile(fileId: String): File = client.postJson("getFile", GetFile(
            fileId = fileId
        )
    )

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = client.postJson("banChatMember", BanChatMember(
            chatId = chatId, 
            userId = userId, 
            untilDate = untilDate, 
            revokeMessages = revokeMessages
        )
    )

    override suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean?,
    ): Boolean = client.postJson("unbanChatMember", UnbanChatMember(
            chatId = chatId, 
            userId = userId, 
            onlyIfBanned = onlyIfBanned
        )
    )

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ): Boolean = client.postJson("restrictChatMember", RestrictChatMember(
            chatId = chatId, 
            userId = userId, 
            permissions = permissions, 
            useIndependentChatPermissions = useIndependentChatPermissions, 
            untilDate = untilDate
        )
    )

    override suspend fun promoteChatMember(
        chatId: String,
        userId: Long,
        isAnonymous: Boolean?,
        canManageChat: Boolean?,
        canDeleteMessages: Boolean?,
        canManageVideoChats: Boolean?,
        canRestrictMembers: Boolean?,
        canPromoteMembers: Boolean?,
        canChangeInfo: Boolean?,
        canInviteUsers: Boolean?,
        canPostStories: Boolean?,
        canEditStories: Boolean?,
        canDeleteStories: Boolean?,
        canPostMessages: Boolean?,
        canEditMessages: Boolean?,
        canPinMessages: Boolean?,
        canManageTopics: Boolean?,
    ): Boolean = client.postJson("promoteChatMember", PromoteChatMember(
            chatId = chatId, 
            userId = userId, 
            isAnonymous = isAnonymous, 
            canManageChat = canManageChat, 
            canDeleteMessages = canDeleteMessages, 
            canManageVideoChats = canManageVideoChats, 
            canRestrictMembers = canRestrictMembers, 
            canPromoteMembers = canPromoteMembers, 
            canChangeInfo = canChangeInfo, 
            canInviteUsers = canInviteUsers, 
            canPostStories = canPostStories, 
            canEditStories = canEditStories, 
            canDeleteStories = canDeleteStories, 
            canPostMessages = canPostMessages, 
            canEditMessages = canEditMessages, 
            canPinMessages = canPinMessages, 
            canManageTopics = canManageTopics
        )
    )

    override suspend fun setChatAdministratorCustomTitle(
        chatId: String,
        userId: Long,
        customTitle: String,
    ): Boolean = client.postJson("setChatAdministratorCustomTitle", SetChatAdministratorCustomTitle(
            chatId = chatId, 
            userId = userId, 
            customTitle = customTitle
        )
    )

    override suspend fun banChatSenderChat(chatId: String, senderChatId: Long): Boolean =
            client.postJson("banChatSenderChat", BanChatSenderChat(
            chatId = chatId, 
            senderChatId = senderChatId
        )
    )

    override suspend fun unbanChatSenderChat(chatId: String, senderChatId: Long): Boolean =
            client.postJson("unbanChatSenderChat", UnbanChatSenderChat(
            chatId = chatId, 
            senderChatId = senderChatId
        )
    )

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ): Boolean = client.postJson("setChatPermissions", SetChatPermissions(
            chatId = chatId, 
            permissions = permissions, 
            useIndependentChatPermissions = useIndependentChatPermissions
        )
    )

    override suspend fun exportChatInviteLink(chatId: String): String =
            client.postJson("exportChatInviteLink", ExportChatInviteLink(
            chatId = chatId
        )
    )

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.postJson("createChatInviteLink", CreateChatInviteLink(
            chatId = chatId, 
            name = name, 
            expireDate = expireDate, 
            memberLimit = memberLimit, 
            createsJoinRequest = createsJoinRequest
        )
    )

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.postJson("editChatInviteLink", EditChatInviteLink(
            chatId = chatId, 
            inviteLink = inviteLink, 
            name = name, 
            expireDate = expireDate, 
            memberLimit = memberLimit, 
            createsJoinRequest = createsJoinRequest
        )
    )

    override suspend fun createChatSubscriptionInviteLink(
        chatId: String,
        subscriptionPeriod: Int,
        subscriptionPrice: Int,
        name: String?,
    ): ChatInviteLink = client.postJson("createChatSubscriptionInviteLink",
            CreateChatSubscriptionInviteLink(
            chatId = chatId, 
            subscriptionPeriod = subscriptionPeriod, 
            subscriptionPrice = subscriptionPrice, 
            name = name
        )
    )

    override suspend fun editChatSubscriptionInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
    ): ChatInviteLink = client.postJson("editChatSubscriptionInviteLink",
            EditChatSubscriptionInviteLink(
            chatId = chatId, 
            inviteLink = inviteLink, 
            name = name
        )
    )

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink =
            client.postJson("revokeChatInviteLink", RevokeChatInviteLink(
            chatId = chatId, 
            inviteLink = inviteLink
        )
    )

    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean =
            client.postJson("approveChatJoinRequest", ApproveChatJoinRequest(
            chatId = chatId, 
            userId = userId
        )
    )

    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean =
            client.postJson("declineChatJoinRequest", DeclineChatJoinRequest(
            chatId = chatId, 
            userId = userId
        )
    )

    override suspend fun setChatPhoto(chatId: String, photo: Input): Boolean =
            client.postMultiPart("setChatPhoto") {
        append("chat_id", chatId)
        appendContent("photo", photo)
    }

    override suspend fun deleteChatPhoto(chatId: String): Boolean =
            client.postJson("deleteChatPhoto", DeleteChatPhoto(
            chatId = chatId
        )
    )

    override suspend fun setChatTitle(chatId: String, title: String): Boolean =
            client.postJson("setChatTitle", SetChatTitle(
            chatId = chatId, 
            title = title
        )
    )

    override suspend fun setChatDescription(chatId: String, description: String?): Boolean =
            client.postJson("setChatDescription", SetChatDescription(
            chatId = chatId, 
            description = description
        )
    )

    override suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        disableNotification: Boolean?,
    ): Boolean = client.postJson("pinChatMessage", PinChatMessage(
            chatId = chatId, 
            messageId = messageId, 
            businessConnectionId = businessConnectionId, 
            disableNotification = disableNotification
        )
    )

    override suspend fun unpinChatMessage(
        chatId: String,
        businessConnectionId: String?,
        messageId: Long?,
    ): Boolean = client.postJson("unpinChatMessage", UnpinChatMessage(
            chatId = chatId, 
            businessConnectionId = businessConnectionId, 
            messageId = messageId
        )
    )

    override suspend fun unpinAllChatMessages(chatId: String): Boolean =
            client.postJson("unpinAllChatMessages", UnpinAllChatMessages(
            chatId = chatId
        )
    )

    override suspend fun leaveChat(chatId: String): Boolean = client.postJson("leaveChat",
            LeaveChat(
            chatId = chatId
        )
    )

    override suspend fun getChat(chatId: String): ChatFullInfo = client.postJson("getChat", GetChat(
            chatId = chatId
        )
    )

    override suspend fun getChatAdministrators(chatId: String): List<ChatMember> =
            client.postJson("getChatAdministrators", GetChatAdministrators(
            chatId = chatId
        )
    )

    override suspend fun getChatMemberCount(chatId: String): Int =
            client.postJson("getChatMemberCount", GetChatMemberCount(
            chatId = chatId
        )
    )

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember =
            client.postJson("getChatMember", GetChatMember(
            chatId = chatId, 
            userId = userId
        )
    )

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean =
            client.postJson("setChatStickerSet", SetChatStickerSet(
            chatId = chatId, 
            stickerSetName = stickerSetName
        )
    )

    override suspend fun deleteChatStickerSet(chatId: String): Boolean =
            client.postJson("deleteChatStickerSet", DeleteChatStickerSet(
            chatId = chatId
        )
    )

    override suspend fun getForumTopicIconStickers(): List<Sticker> =
            client.get("getForumTopicIconStickers")

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = client.postJson("createForumTopic", CreateForumTopic(
            chatId = chatId, 
            name = name, 
            iconColor = iconColor, 
            iconCustomEmojiId = iconCustomEmojiId
        )
    )

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
    ): Boolean = client.postJson("editForumTopic", EditForumTopic(
            chatId = chatId, 
            messageThreadId = messageThreadId, 
            name = name, 
            iconCustomEmojiId = iconCustomEmojiId
        )
    )

    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean =
            client.postJson("closeForumTopic", CloseForumTopic(
            chatId = chatId, 
            messageThreadId = messageThreadId
        )
    )

    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean =
            client.postJson("reopenForumTopic", ReopenForumTopic(
            chatId = chatId, 
            messageThreadId = messageThreadId
        )
    )

    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean =
            client.postJson("deleteForumTopic", DeleteForumTopic(
            chatId = chatId, 
            messageThreadId = messageThreadId
        )
    )

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean
            = client.postJson("unpinAllForumTopicMessages", UnpinAllForumTopicMessages(
            chatId = chatId, 
            messageThreadId = messageThreadId
        )
    )

    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean =
            client.postJson("editGeneralForumTopic", EditGeneralForumTopic(
            chatId = chatId, 
            name = name
        )
    )

    override suspend fun closeGeneralForumTopic(chatId: String): Boolean =
            client.postJson("closeGeneralForumTopic", CloseGeneralForumTopic(
            chatId = chatId
        )
    )

    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean =
            client.postJson("reopenGeneralForumTopic", ReopenGeneralForumTopic(
            chatId = chatId
        )
    )

    override suspend fun hideGeneralForumTopic(chatId: String): Boolean =
            client.postJson("hideGeneralForumTopic", HideGeneralForumTopic(
            chatId = chatId
        )
    )

    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean =
            client.postJson("unhideGeneralForumTopic", UnhideGeneralForumTopic(
            chatId = chatId
        )
    )

    override suspend fun unpinAllGeneralForumTopicMessages(chatId: String): Boolean =
            client.postJson("unpinAllGeneralForumTopicMessages", UnpinAllGeneralForumTopicMessages(
            chatId = chatId
        )
    )

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Int?,
    ): Boolean = client.postJson("answerCallbackQuery", AnswerCallbackQuery(
            callbackQueryId = callbackQueryId, 
            text = text, 
            showAlert = showAlert, 
            url = url, 
            cacheTime = cacheTime
        )
    )

    override suspend fun getUserChatBoosts(chatId: String, userId: Long): UserChatBoosts =
            client.postJson("getUserChatBoosts", GetUserChatBoosts(
            chatId = chatId, 
            userId = userId
        )
    )

    override suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection =
            client.postJson("getBusinessConnection", GetBusinessConnection(
            businessConnectionId = businessConnectionId
        )
    )

    override suspend fun setMyCommands(
        commands: Iterable<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = client.postJson("setMyCommands", SetMyCommands(
            commands = commands, 
            scope = scope, 
            languageCode = languageCode
        )
    )

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
            client.postJson("deleteMyCommands", DeleteMyCommands(
            scope = scope, 
            languageCode = languageCode
        )
    )

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?):
            List<BotCommand> = client.postJson("getMyCommands", GetMyCommands(
            scope = scope, 
            languageCode = languageCode
        )
    )

    override suspend fun setMyName(name: String?, languageCode: String?): Boolean =
            client.postJson("setMyName", SetMyName(
            name = name, 
            languageCode = languageCode
        )
    )

    override suspend fun getMyName(languageCode: String?): BotName = client.postJson("getMyName",
            GetMyName(
            languageCode = languageCode
        )
    )

    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean =
            client.postJson("setMyDescription", SetMyDescription(
            description = description, 
            languageCode = languageCode
        )
    )

    override suspend fun getMyDescription(languageCode: String?): BotDescription =
            client.postJson("getMyDescription", GetMyDescription(
            languageCode = languageCode
        )
    )

    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?):
            Boolean = client.postJson("setMyShortDescription", SetMyShortDescription(
            shortDescription = shortDescription, 
            languageCode = languageCode
        )
    )

    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription =
            client.postJson("getMyShortDescription", GetMyShortDescription(
            languageCode = languageCode
        )
    )

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean =
            client.postJson("setChatMenuButton", SetChatMenuButton(
            chatId = chatId, 
            menuButton = menuButton
        )
    )

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton =
            client.postJson("getChatMenuButton", GetChatMenuButton(
            chatId = chatId
        )
    )

    override suspend fun setMyDefaultAdministratorRights(rights: ChatAdministratorRights?,
            forChannels: Boolean?): Boolean = client.postJson("setMyDefaultAdministratorRights",
            SetMyDefaultAdministratorRights(
            rights = rights, 
            forChannels = forChannels
        )
    )

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?):
            ChatAdministratorRights = client.postJson("getMyDefaultAdministratorRights",
            GetMyDefaultAdministratorRights(
            forChannels = forChannels
        )
    )

    override suspend fun editMessageText(
        chatId: String,
        messageId: Long,
        text: String,
        businessConnectionId: String?,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageText", EditMessageTextByChatIdAndMessageId(
            chatId = chatId, 
            messageId = messageId, 
            text = text, 
            businessConnectionId = businessConnectionId, 
            parseMode = parseMode, 
            entities = entities, 
            linkPreviewOptions = linkPreviewOptions, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "EDIT_TEXT", text = text) }

    override suspend fun editMessageCaption(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageCaption", EditMessageCaptionByChatIdAndMessageId(
            chatId = chatId, 
            messageId = messageId, 
            businessConnectionId = businessConnectionId, 
            caption = caption, 
            parseMode = parseMode, 
            captionEntities = captionEntities, 
            showCaptionAboveMedia = showCaptionAboveMedia, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "EDIT_CAPTION", text = caption) }

    override suspend fun editMessageMedia(
        chatId: String,
        messageId: Long,
        media: InputMedia,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postMultiPart("editMessageMedia") {
        append("chat_id", chatId)
        append("message_id", messageId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
        
        appendContent(media.media)
        
        appendContentIfNotNull(media.thumbnail)
    }

    override suspend fun editMessageLiveLocation(
        chatId: String,
        messageId: Long,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String?,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageLiveLocation",
            EditMessageLiveLocationByChatIdAndMessageId(
            chatId = chatId, 
            messageId = messageId, 
            latitude = latitude, 
            longitude = longitude, 
            businessConnectionId = businessConnectionId, 
            livePeriod = livePeriod, 
            horizontalAccuracy = horizontalAccuracy, 
            heading = heading, 
            proximityAlertRadius = proximityAlertRadius, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "EDIT_LIVE_LOCATION", text = "latitude = $latitude, longitude = $longitude") }

    override suspend fun stopMessageLiveLocation(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("stopMessageLiveLocation",
            StopMessageLiveLocationByChatIdAndMessageId(
            chatId = chatId, 
            messageId = messageId, 
            businessConnectionId = businessConnectionId, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "STOP_LIVE_LOCATION") }

    override suspend fun editMessageReplyMarkup(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageReplyMarkup",
            EditMessageReplyMarkupByChatIdAndMessageId(
            chatId = chatId, 
            messageId = messageId, 
            businessConnectionId = businessConnectionId, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "EDIT_REPLY_MARKUP") }

    override suspend fun stopPoll(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Poll = client.postJson("stopPoll", StopPoll(
            chatId = chatId, 
            messageId = messageId, 
            businessConnectionId = businessConnectionId, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean =
            client.postJson("deleteMessage", DeleteMessage(
            chatId = chatId, 
            messageId = messageId
        )
    )

    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean =
            client.postJson("deleteMessages", DeleteMessages(
            chatId = chatId, 
            messageIds = messageIds
        )
    )

    override suspend fun sendSticker(
        chatId: String,
        sticker: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendSticker") {
        append("chat_id", chatId)
        appendContent("sticker", sticker)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("emoji", emoji)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "STICKER", text = emoji) }

    override suspend fun getStickerSet(name: String): StickerSet = client.postJson("getStickerSet",
            GetStickerSet(
            name = name
        )
    )

    override suspend fun getCustomEmojiStickers(customEmojiIds: Iterable<String>): List<Sticker> =
            client.postJson("getCustomEmojiStickers", GetCustomEmojiStickers(
            customEmojiIds = customEmojiIds
        )
    )

    override suspend fun uploadStickerFile(
        userId: Long,
        sticker: Input,
        stickerFormat: String,
    ): File = client.postMultiPart("uploadStickerFile") {
        append("user_id", userId)
        appendContent("sticker", sticker)
        append("sticker_format", stickerFormat)
    }

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerType: String?,
        needsRepainting: Boolean?,
    ): Boolean = client.postMultiPart("createNewStickerSet") {
        append("user_id", userId)
        append("name", name)
        append("title", title)
        append("stickers", client.toJson(stickers))
        appendIfNotNull("sticker_type", stickerType)
        appendIfNotNull("needs_repainting", needsRepainting)
        
        stickers.forEach { stickers ->
            appendContent(stickers.sticker)
        }
    }

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = client.postMultiPart("addStickerToSet") {
        append("user_id", userId)
        append("name", name)
        append("sticker", client.toJson(sticker))
        
        appendContent(sticker.sticker)
    }

    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean =
            client.postJson("setStickerPositionInSet", SetStickerPositionInSet(
            sticker = sticker, 
            position = position
        )
    )

    override suspend fun deleteStickerFromSet(sticker: String): Boolean =
            client.postJson("deleteStickerFromSet", DeleteStickerFromSet(
            sticker = sticker
        )
    )

    override suspend fun replaceStickerInSet(
        userId: Long,
        name: String,
        oldSticker: String,
        sticker: InputSticker,
    ): Boolean = client.postMultiPart("replaceStickerInSet") {
        append("user_id", userId)
        append("name", name)
        append("old_sticker", oldSticker)
        append("sticker", client.toJson(sticker))
        
        appendContent(sticker.sticker)
    }

    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean
            = client.postJson("setStickerEmojiList", SetStickerEmojiList(
            sticker = sticker, 
            emojiList = emojiList
        )
    )

    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean =
            client.postJson("setStickerKeywords", SetStickerKeywords(
            sticker = sticker, 
            keywords = keywords
        )
    )

    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?):
            Boolean = client.postJson("setStickerMaskPosition", SetStickerMaskPosition(
            sticker = sticker, 
            maskPosition = maskPosition
        )
    )

    override suspend fun setStickerSetTitle(name: String, title: String): Boolean =
            client.postJson("setStickerSetTitle", SetStickerSetTitle(
            name = name, 
            title = title
        )
    )

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: Input?,
    ): Boolean = client.postMultiPart("setStickerSetThumbnail") {
        append("name", name)
        append("user_id", userId)
        append("format", format)
        appendContentIfNotNull("thumbnail", thumbnail)
    }

    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?):
            Boolean = client.postJson("setCustomEmojiStickerSetThumbnail",
            SetCustomEmojiStickerSetThumbnail(
            name = name, 
            customEmojiId = customEmojiId
        )
    )

    override suspend fun deleteStickerSet(name: String): Boolean =
            client.postJson("deleteStickerSet", DeleteStickerSet(
            name = name
        )
    )

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: Iterable<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
    ): Boolean = client.postJson("answerInlineQuery", AnswerInlineQuery(
            inlineQueryId = inlineQueryId, 
            results = results, 
            cacheTime = cacheTime, 
            isPersonal = isPersonal, 
            nextOffset = nextOffset, 
            button = button
        )
    )

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult):
            SentWebAppMessage = client.postJson("answerWebAppQuery", AnswerWebAppQuery(
            webAppQueryId = webAppQueryId, 
            result = result
        )
    )

    override suspend fun sendInvoice(
        chatId: String,
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: Iterable<LabeledPrice>,
        messageThreadId: Long?,
        providerToken: String?,
        maxTipAmount: Int?,
        suggestedTipAmounts: Iterable<Int>?,
        startParameter: String?,
        providerData: String?,
        photoUrl: String?,
        photoSize: Int?,
        photoWidth: Int?,
        photoHeight: Int?,
        needName: Boolean?,
        needPhoneNumber: Boolean?,
        needEmail: Boolean?,
        needShippingAddress: Boolean?,
        sendPhoneNumberToProvider: Boolean?,
        sendEmailToProvider: Boolean?,
        isFlexible: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("sendInvoice", SendInvoice(
            chatId = chatId, 
            title = title, 
            description = description, 
            payload = payload, 
            currency = currency, 
            prices = prices, 
            messageThreadId = messageThreadId, 
            providerToken = providerToken, 
            maxTipAmount = maxTipAmount, 
            suggestedTipAmounts = suggestedTipAmounts, 
            startParameter = startParameter, 
            providerData = providerData, 
            photoUrl = photoUrl, 
            photoSize = photoSize, 
            photoWidth = photoWidth, 
            photoHeight = photoHeight, 
            needName = needName, 
            needPhoneNumber = needPhoneNumber, 
            needEmail = needEmail, 
            needShippingAddress = needShippingAddress, 
            sendPhoneNumberToProvider = sendPhoneNumberToProvider, 
            sendEmailToProvider = sendEmailToProvider, 
            isFlexible = isFlexible, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "INVOICE", text = title) }

    override suspend fun createInvoiceLink(
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: Iterable<LabeledPrice>,
        providerToken: String?,
        maxTipAmount: Int?,
        suggestedTipAmounts: Iterable<Int>?,
        providerData: String?,
        photoUrl: String?,
        photoSize: Int?,
        photoWidth: Int?,
        photoHeight: Int?,
        needName: Boolean?,
        needPhoneNumber: Boolean?,
        needEmail: Boolean?,
        needShippingAddress: Boolean?,
        sendPhoneNumberToProvider: Boolean?,
        sendEmailToProvider: Boolean?,
        isFlexible: Boolean?,
    ): String = client.postJson("createInvoiceLink", CreateInvoiceLink(
            title = title, 
            description = description, 
            payload = payload, 
            currency = currency, 
            prices = prices, 
            providerToken = providerToken, 
            maxTipAmount = maxTipAmount, 
            suggestedTipAmounts = suggestedTipAmounts, 
            providerData = providerData, 
            photoUrl = photoUrl, 
            photoSize = photoSize, 
            photoWidth = photoWidth, 
            photoHeight = photoHeight, 
            needName = needName, 
            needPhoneNumber = needPhoneNumber, 
            needEmail = needEmail, 
            needShippingAddress = needShippingAddress, 
            sendPhoneNumberToProvider = sendPhoneNumberToProvider, 
            sendEmailToProvider = sendEmailToProvider, 
            isFlexible = isFlexible
        )
    )

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: Iterable<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = client.postJson("answerShippingQuery", AnswerShippingQuery(
            shippingQueryId = shippingQueryId, 
            ok = ok, 
            shippingOptions = shippingOptions, 
            errorMessage = errorMessage
        )
    )

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = client.postJson("answerPreCheckoutQuery", AnswerPreCheckoutQuery(
            preCheckoutQueryId = preCheckoutQueryId, 
            ok = ok, 
            errorMessage = errorMessage
        )
    )

    override suspend fun getStarTransactions(offset: Int?, limit: Int?): StarTransactions =
            client.postJson("getStarTransactions", GetStarTransactions(
            offset = offset, 
            limit = limit
        )
    )

    override suspend fun refundStarPayment(userId: Long, telegramPaymentChargeId: String): Boolean =
            client.postJson("refundStarPayment", RefundStarPayment(
            userId = userId, 
            telegramPaymentChargeId = telegramPaymentChargeId
        )
    )

    override suspend fun setPassportDataErrors(userId: Long,
            errors: Iterable<PassportElementError>): Boolean =
            client.postJson("setPassportDataErrors", SetPassportDataErrors(
            userId = userId, 
            errors = errors
        )
    )

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("sendGame", SendGame(
            chatId = chatId, 
            gameShortName = gameShortName, 
            businessConnectionId = businessConnectionId, 
            messageThreadId = messageThreadId, 
            disableNotification = disableNotification, 
            protectContent = protectContent, 
            messageEffectId = messageEffectId, 
            replyParameters = replyParameters, 
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "GAME", text = gameShortName) }

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        chatId: Long,
        messageId: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Message = client.postJson<Message>("setGameScore", SetGameScoreByChatIdAndMessageId(
            userId = userId, 
            score = score, 
            chatId = chatId, 
            messageId = messageId, 
            force = force, 
            disableEditMessage = disableEditMessage
        )
    ).also { messageSource.save(it.chat.id, it.from!!.id, true, it.messageId, type = "GAME_SCORE", text = "userId = $userId, score = $score") }

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = client.postJson("getGameHighScores", GetGameHighScores(
            userId = userId, 
            chatId = chatId, 
            messageId = messageId, 
            inlineMessageId = inlineMessageId
        )
    )

    override suspend fun editMessageText(
        inlineMessageId: String,
        text: String,
        businessConnectionId: String?,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson("editMessageText", EditMessageTextByInlineMessageId(
            inlineMessageId = inlineMessageId, 
            text = text, 
            businessConnectionId = businessConnectionId, 
            parseMode = parseMode, 
            entities = entities, 
            linkPreviewOptions = linkPreviewOptions, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun editMessageCaption(
        inlineMessageId: String,
        businessConnectionId: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson("editMessageCaption", EditMessageCaptionByInlineMessageId(
            inlineMessageId = inlineMessageId, 
            businessConnectionId = businessConnectionId, 
            caption = caption, 
            parseMode = parseMode, 
            captionEntities = captionEntities, 
            showCaptionAboveMedia = showCaptionAboveMedia, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun editMessageMedia(
        inlineMessageId: String,
        media: InputMedia,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postMultiPart("editMessageMedia") {
        append("inline_message_id", inlineMessageId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
        
        appendContent(media.media)
        
        appendContentIfNotNull(media.thumbnail)
    }

    override suspend fun editMessageLiveLocation(
        inlineMessageId: String,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String?,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson("editMessageLiveLocation",
            EditMessageLiveLocationByInlineMessageId(
            inlineMessageId = inlineMessageId, 
            latitude = latitude, 
            longitude = longitude, 
            businessConnectionId = businessConnectionId, 
            livePeriod = livePeriod, 
            horizontalAccuracy = horizontalAccuracy, 
            heading = heading, 
            proximityAlertRadius = proximityAlertRadius, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun stopMessageLiveLocation(
        inlineMessageId: String,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson("stopMessageLiveLocation",
            StopMessageLiveLocationByInlineMessageId(
            inlineMessageId = inlineMessageId, 
            businessConnectionId = businessConnectionId, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun editMessageReplyMarkup(
        inlineMessageId: String,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson("editMessageReplyMarkup", EditMessageReplyMarkupByInlineMessageId(
            inlineMessageId = inlineMessageId, 
            businessConnectionId = businessConnectionId, 
            replyMarkup = replyMarkup
        )
    )

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        inlineMessageId: String,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Boolean = client.postJson("setGameScore", SetGameScoreByInlineMessageId(
            userId = userId, 
            score = score, 
            inlineMessageId = inlineMessageId, 
            force = force, 
            disableEditMessage = disableEditMessage
        )
    )
}
