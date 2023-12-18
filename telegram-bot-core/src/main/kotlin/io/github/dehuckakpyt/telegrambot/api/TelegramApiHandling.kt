package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class TelegramApiHandling : TelegramApi, KoinComponent {

    private val bot = get<TelegramBot>()

    //region Telegram methods
    override suspend fun getMe(): User = bot.getMe()

    override suspend fun logOut(): Boolean = bot.logOut()

    override suspend fun close(): Boolean = bot.close()

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableWebPagePreview: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendMessage(
        chatId = chatId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        messageThreadId = messageThreadId,
        disableWebPagePreview = disableWebPagePreview,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): Message = bot.forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    override suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): MessageId = bot.copyMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendPhoto(
        chatId: String,
        photo: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendPhoto(
        chatId = chatId,
        photo = photo,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendPhoto(
        chatId: String,
        photo: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendPhoto(
        chatId = chatId,
        photo = photo,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendAudio(
        chatId: String,
        audio: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: NamedContent?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendAudio(
        chatId = chatId,
        audio = audio,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendAudio(
        chatId: String,
        audio: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: NamedContent?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendAudio(
        chatId = chatId,
        audio = audio,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendDocument(
        chatId: String,
        document: NamedContent,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendDocument(
        chatId = chatId,
        document = document,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendDocument(
        chatId: String,
        document: String,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendDocument(
        chatId = chatId,
        document = document,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVideo(
        chatId: String,
        video: NamedContent,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVideo(
        chatId = chatId,
        video = video,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVideo(
        chatId: String,
        video: String,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVideo(
        chatId = chatId,
        video = video,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendAnimation(
        chatId: String,
        animation: NamedContent,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendAnimation(
        chatId = chatId,
        animation = animation,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendAnimation(
        chatId: String,
        animation: String,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: NamedContent?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendAnimation(
        chatId = chatId,
        animation = animation,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVoice(
        chatId: String,
        voice: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVoice(
        chatId = chatId,
        voice = voice,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVoice(
        chatId: String,
        voice: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVoice(
        chatId = chatId,
        voice = voice,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        messageThreadId = messageThreadId,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: NamedContent,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: NamedContent?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVideoNote(
        chatId = chatId,
        videoNote = videoNote,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: String,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: NamedContent?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVideoNote(
        chatId = chatId,
        videoNote = videoNote,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendMediaGroup(
        chatId: String,
        media: List<InputMedia>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
    ): ArrayList<Message> = bot.sendMediaGroup(
        chatId = chatId,
        media = media,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply
    )

    override suspend fun sendLocation(
        chatId: String,
        latitude: Float,
        longitude: Float,
        messageThreadId: Long?,
        horizontalAccuracy: Float?,
        livePeriod: Long?,
        heading: Long?,
        proximityAlertRadius: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendLocation(
        chatId = chatId,
        latitude = latitude,
        longitude = longitude,
        messageThreadId = messageThreadId,
        horizontalAccuracy = horizontalAccuracy,
        livePeriod = livePeriod,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendVenue(
        chatId: String,
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        messageThreadId: Long?,
        foursquareId: String?,
        foursquareType: String?,
        googlePlaceId: String?,
        googlePlaceType: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendVenue(
        chatId = chatId,
        latitude = latitude,
        longitude = longitude,
        title = title,
        address = address,
        messageThreadId = messageThreadId,
        foursquareId = foursquareId,
        foursquareType = foursquareType,
        googlePlaceId = googlePlaceId,
        googlePlaceType = googlePlaceType,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendContact(
        chatId: String,
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long?,
        lastName: String?,
        vcard: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendContact(
        chatId = chatId,
        phoneNumber = phoneNumber,
        firstName = firstName,
        messageThreadId = messageThreadId,
        lastName = lastName,
        vcard = vcard,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendPoll(
        chatId: String,
        question: String,
        options: List<String>,
        messageThreadId: Long?,
        isAnonymous: Boolean?,
        type: String?,
        allowsMultipleAnswers: Boolean?,
        correctOptionId: Long?,
        explanation: String?,
        explanationParseMode: String?,
        explanationEntities: List<MessageEntity>?,
        openPeriod: Long?,
        closeDate: Long?,
        isClosed: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendPoll(
        chatId = chatId,
        question = question,
        options = options,
        messageThreadId = messageThreadId,
        isAnonymous = isAnonymous,
        type = type,
        allowsMultipleAnswers = allowsMultipleAnswers,
        correctOptionId = correctOptionId,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        explanationEntities = explanationEntities,
        openPeriod = openPeriod,
        closeDate = closeDate,
        isClosed = isClosed,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendDice(
        chatId: String,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
    ): Message = bot.sendDice(
        chatId = chatId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun sendChatAction(
        chatId: String,
        action: Action,
        messageThreadId: Long?,
    ): Boolean = bot.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    override suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long?,
        limit: Long?,
    ): UserProfilePhotos = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    override suspend fun getFile(
        fileId: String,
    ): File = bot.getFile(
        fileId = fileId
    )

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = bot.banChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages
    )

    override suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean?,
    ): Boolean = bot.unbanChatMember(
        chatId = chatId,
        userId = userId,
        onlyIfBanned = onlyIfBanned
    )

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ): Boolean = bot.restrictChatMember(
        chatId = chatId,
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate
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
        canPostMessages: Boolean?,
        canEditMessages: Boolean?,
        canPinMessages: Boolean?,
        canPostStories: Boolean?,
        canEditStories: Boolean?,
        canDeleteStories: Boolean?,
        canManageTopics: Boolean?,
    ): Boolean = bot.promoteChatMember(
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
        canPostMessages = canPostMessages,
        canEditMessages = canEditMessages,
        canPinMessages = canPinMessages,
        canPostStories = canPostStories,
        canEditStories = canEditStories,
        canDeleteStories = canDeleteStories,
        canManageTopics = canManageTopics
    )

    override suspend fun setChatAdministratorCustomTitle(
        chatId: String,
        userId: Long,
        customTitle: String,
    ): Boolean = bot.setChatAdministratorCustomTitle(
        chatId = chatId,
        userId = userId,
        customTitle = customTitle
    )

    override suspend fun banChatSenderChat(
        chatId: String,
        senderChatId: Long,
    ): Boolean = bot.banChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    override suspend fun unbanChatSenderChat(
        chatId: String,
        senderChatId: Long,
    ): Boolean = bot.unbanChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ): Boolean = setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions
    )

    override suspend fun exportChatInviteLink(
        chatId: String,
    ): String = bot.exportChatInviteLink(
        chatId = chatId
    )

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = bot.createChatInviteLink(
        chatId = chatId,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = bot.editChatInviteLink(
        chatId = chatId,
        inviteLink = inviteLink,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    override suspend fun revokeChatInviteLink(
        chatId: String,
        inviteLink: String,
    ): ChatInviteLink = bot.revokeChatInviteLink(
        chatId = chatId,
        inviteLink = inviteLink
    )

    override suspend fun approveChatJoinRequest(
        chatId: String,
        userId: Long,
    ): Boolean = bot.approveChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    override suspend fun declineChatJoinRequest(
        chatId: String,
        userId: Long,
    ): Boolean = bot.declineChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    override suspend fun setChatPhoto(
        chatId: String,
        photo: NamedContent,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    override suspend fun setChatPhoto(
        chatId: String,
        photo: String,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    override suspend fun deleteChatPhoto(
        chatId: String,
    ): Boolean = bot.deleteChatPhoto(
        chatId = chatId
    )

    override suspend fun setChatTitle(
        chatId: String,
        title: String,
    ): Boolean = bot.setChatTitle(
        chatId = chatId,
        title = title
    )

    override suspend fun setChatDescription(
        chatId: String,
        description: String,
    ): Boolean = bot.setChatDescription(
        chatId = chatId,
        description = description
    )

    override suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        disableNotification: Boolean?,
    ): Boolean = bot.pinChatMessage(
        chatId = chatId,
        messageId = messageId,
        disableNotification = disableNotification
    )

    override suspend fun unpinChatMessage(
        chatId: String,
        messageId: Long?,
    ): Boolean = bot.unpinChatMessage(
        chatId = chatId,
        messageId = messageId
    )

    override suspend fun unpinAllChatMessages(
        chatId: String,
    ): Boolean = bot.unpinAllChatMessages(
        chatId = chatId
    )

    override suspend fun leaveChat(
        chatId: String,
    ): Boolean = bot.leaveChat(
        chatId = chatId
    )

    override suspend fun getChat(
        chatId: String,
    ): Chat = bot.getChat(
        chatId = chatId
    )

    override suspend fun getChatAdministrators(
        chatId: String,
    ): ArrayList<ChatMember> = bot.getChatAdministrators(
        chatId = chatId
    )

    override suspend fun getChatMemberCount(
        chatId: String,
    ): Long = bot.getChatMemberCount(
        chatId = chatId
    )

    override suspend fun getChatMember(
        chatId: String,
        userId: Long,
    ): ChatMember = bot.getChatMember(
        chatId = chatId,
        userId = userId
    )

    override suspend fun setChatStickerSet(
        chatId: String,
        stickerSetName: String,
    ): Boolean = bot.setChatStickerSet(
        chatId = chatId,
        stickerSetName = stickerSetName
    )

    override suspend fun deleteChatStickerSet(
        chatId: String,
    ): Boolean = bot.deleteChatStickerSet(
        chatId = chatId
    )

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Long?,
    ): Boolean = bot.answerCallbackQuery(
        callbackQueryId = callbackQueryId,
        text = text,
        showAlert = showAlert,
        url = url,
        cacheTime = cacheTime
    )

    override suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = bot.setMyCommands(
        commands = commands,
        scope = scope,
        languageCode = languageCode
    )

    override suspend fun deleteMyCommands(
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = bot.deleteMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    override suspend fun getMyCommands(
        scope: BotCommandScope?,
        languageCode: String?,
    ): List<BotCommand> = bot.getMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    override suspend fun setMyName(
        name: String?,
        languageCode: String?,
    ): Boolean = bot.setMyName(
        name = name,
        languageCode = languageCode
    )

    override suspend fun getMyName(
        languageCode: String?,
    ): BotName = bot.getMyName(
        languageCode = languageCode
    )

    override suspend fun setMyDescription(
        description: String?,
        languageCode: String?,
    ): Boolean = bot.setMyDescription(
        description = description,
        languageCode = languageCode
    )

    override suspend fun getMyDescription(
        languageCode: String?,
    ): BotDescription = bot.getMyDescription(
        languageCode = languageCode
    )

    override suspend fun setMyShortDescription(
        shortDescription: String?, languageCode: String?,
    ): Boolean = bot.setMyShortDescription(
        shortDescription = shortDescription,
        languageCode = languageCode
    )

    override suspend fun getMyShortDescription(
        languageCode: String?,
    ): BotShortDescription = bot.getMyShortDescription(
        languageCode = languageCode
    )

    override suspend fun editMessageLiveLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float?,
        heading: Long?,
        proximityAlertRadius: Long?,
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.editMessageLiveLocation(
        latitude = latitude,
        longitude = longitude,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    override suspend fun stopMessageLiveLocation(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.stopMessageLiveLocation(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    override suspend fun setChatMenuButton(
        chatId: Long?,
        menuButton: MenuButton?,
    ): Boolean = bot.setChatMenuButton(
        chatId = chatId,
        menuButton = menuButton
    )

    override suspend fun getChatMenuButton(
        chatId: Long?,
    ): MenuButton = bot.getChatMenuButton(
        chatId = chatId
    )

    override suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?,
    ): Boolean = bot.setMyDefaultAdministratorRights(
        rights = rights,
        forChannels = forChannels
    )

    override suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean?,
    ): ChatAdministratorRights = bot.getMyDefaultAdministratorRights(
        forChannels = forChannels
    )

    override suspend fun getForumTopicIconStickers(): List<Sticker> = bot.getForumTopicIconStickers()

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId
    )

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
    ): Boolean = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId
    )

    override suspend fun closeForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean = bot.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    override suspend fun reopenForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean = bot.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    override suspend fun deleteForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean = bot.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    override suspend fun unpinAllForumTopicMessages(
        chatId: String,
        messageThreadId: Long
    ): Boolean = bot.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    override suspend fun editGeneralForumTopic(
        chatId: String,
        name: String
    ): Boolean = bot.editGeneralForumTopic(
        chatId = chatId,
        name = name
    )

    override suspend fun closeGeneralForumTopic(
        chatId: String
    ): Boolean = bot.closeGeneralForumTopic(
        chatId = chatId
    )

    override suspend fun reopenGeneralForumTopic(
        chatId: String
    ): Boolean = bot.reopenGeneralForumTopic(
        chatId = chatId
    )

    override suspend fun hideGeneralForumTopic(
        chatId: String
    ): Boolean = bot.hideGeneralForumTopic(
        chatId = chatId
    )

    override suspend fun unhideGeneralForumTopic(
        chatId: String
    ): Boolean = bot.unhideGeneralForumTopic(
        chatId = chatId
    )

    override suspend fun unpinAllGeneralForumTopicMessages(
        chatId: String
    ): Boolean = bot.unpinAllGeneralForumTopicMessages(
        chatId = chatId
    )

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.sendGame(
        chatId = chatId,
        gameShortName = gameShortName,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?
    ): Message = bot.setGameScore(
        userId = userId,
        score = score,
        force = force,
        disableEditMessage = disableEditMessage,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?
    ): List<GameHighScore> = bot.getGameHighScores(
        userId = userId,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?
    ): Boolean = bot.answerInlineQuery(
        inlineQueryId = inlineQueryId,
        results = results,
        cacheTime = cacheTime,
        isPersonal = isPersonal,
        nextOffset = nextOffset,
        button = button
    )

    override suspend fun answerWebAppQuery(
        webAppQueryId: String,
        result: InlineQueryResult
    ): SentWebAppMessage = bot.answerWebAppQuery(
        webAppQueryId = webAppQueryId,
        result = result
    )

    override suspend fun setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>
    ): Boolean = bot.setPassportDataErrors(
        userId = userId,
        errors = errors
    )

    override suspend fun sendInvoice(
        chatId: String,
        title: String,
        description: String,
        payload: String,
        providerToken: String,
        currency: String,
        prices: List<LabeledPrice>,
        messageThreadId: Long?,
        maxTipAmount: Int?,
        suggestedTipAmount: List<Int>?,
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
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.sendInvoice(
        chatId = chatId,
        title = title,
        description = description,
        payload = payload,
        providerToken = providerToken,
        currency = currency,
        prices = prices,
        messageThreadId = messageThreadId,
        maxTipAmount = maxTipAmount,
        suggestedTipAmount = suggestedTipAmount,
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
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun createInvoiceLink(
        title: String,
        description: String,
        payload: String,
        providerToken: String,
        currency: String,
        prices: List<LabeledPrice>,
        maxTipAmount: Int?,
        suggestedTipAmount: List<Int>?,
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
        isFlexible: Boolean?
    ): String = bot.createInvoiceLink(
        title = title,
        description = description,
        payload = payload,
        providerToken = providerToken,
        currency = currency,
        prices = prices,
        maxTipAmount = maxTipAmount,
        suggestedTipAmount = suggestedTipAmount,
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

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?
    ): Boolean = bot.answerShippingQuery(
        shippingQueryId = shippingQueryId,
        ok = ok,
        shippingOptions = shippingOptions,
        errorMessage = errorMessage
    )

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?
    ): Boolean = bot.answerPreCheckoutQuery(
        preCheckoutQueryId = preCheckoutQueryId,
        ok = ok,
        errorMessage = errorMessage
    )

    override suspend fun sendSticker(
        chatId: String,
        sticker: NamedContent,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message = bot.sendSticker(
        chatId = chatId,
        sticker = sticker,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    override suspend fun getStickerSet(
        name: String
    ): StickerSet = bot.getStickerSet(
        name = name
    )

    override suspend fun getCustomEmojiStickers(
        customEmojiIds: List<String>
    ): List<Sticker> = bot.getCustomEmojiStickers(
        customEmojiIds = customEmojiIds
    )

    override suspend fun uploadStickerFile(
        userId: Long,
        sticker: NamedContent,
        stickerFormat: String
    ): File = bot.uploadStickerFile(
        userId = userId,
        sticker = sticker,
        stickerFormat = stickerFormat
    )

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Collection<Any>,
        stickerFormat: String,
        stickerType: String?,
        needsRepainting: Boolean?
    ): Boolean = bot.createNewStickerSet(
        userId = userId,
        name = name,
        title = title,
        stickers = stickers,
        stickerFormat = stickerFormat,
        stickerType = stickerType,
        needsRepainting = needsRepainting
    )

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: Any
    ): Boolean = bot.addStickerToSet(
        userId = userId,
        name = name,
        sticker = sticker
    )

    override suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int
    ): Boolean = bot.setStickerPositionInSet(
        sticker = sticker,
        position = position
    )

    override suspend fun deleteStickerFromSet(
        sticker: String
    ): Boolean = bot.deleteStickerFromSet(
        sticker = sticker
    )

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: Any?
    ): Boolean = bot.setStickerSetThumbnail(
        name = name,
        userId = userId,
        thumbnail = thumbnail
    )

    override suspend fun getUpdates(
        offset: Int?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: List<AllowedUpdate>?
    ): List<UpdateResponse> = bot.getUpdates(
        offset = offset,
        limit = limit,
        timeout = timeout,
        allowedUpdates = allowedUpdates
    )

    override suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?
    ): Boolean = bot.setWebhook(
        url = url,
        certificate = certificate,
        ipAddress = ipAddress,
        maxConnections = maxConnections,
        allowedUpdates = allowedUpdates,
        dropPendingUpdates = dropPendingUpdates,
        secretToken = secretToken
    )

    override suspend fun deleteWebhook(
        dropPendingUpdates: Boolean?
    ): Boolean = bot.deleteWebhook(
        dropPendingUpdates = dropPendingUpdates
    )

    override suspend fun getWebhookInfo(): WebhookInfo = bot.getWebhookInfo()

    override suspend fun editMessageText(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.editMessageText(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        disableWebPagePreview = disableWebPagePreview,
        replyMarkup = replyMarkup
    )

    override suspend fun editMessageCaption(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.editMessageCaption(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    override suspend fun editMessageMedia(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.editMessageMedia(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        media = media,
        replyMarkup = replyMarkup
    )

    override suspend fun editMessageReplyMarkup(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = bot.editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    override suspend fun stopPoll(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?
    ): Poll = bot.stopPoll(
        chatId = chatId,
        messageId = messageId,
        replyMarkup = replyMarkup
    )

    override suspend fun deleteMessage(
        chatId: String,
        messageId: Long
    ): Boolean = bot.deleteMessage(
        chatId = chatId,
        messageId = messageId
    )
    //endregion Telegram methods
}