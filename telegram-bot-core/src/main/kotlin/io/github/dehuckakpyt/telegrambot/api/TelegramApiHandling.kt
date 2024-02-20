package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.Content
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.NamedContent


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class TelegramApiHandling {

    protected abstract val bot: TelegramBot

    //region Telegram methods
    suspend fun Argument.getMe(): User = bot.getMe()

    suspend fun Argument.logOut(): Boolean = bot.logOut()

    suspend fun Argument.close(): Boolean = bot.close()

    suspend fun Argument.sendMessage(
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendMessage(
        chatId = chatId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        messageThreadId = messageThreadId,
        linkPreviewOptions = linkPreviewOptions,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.forwardMessage(
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = bot.forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun Argument.forwardMessages(
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = bot.forwardMessages(
        chatId = chatId,
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun Argument.copyMessage(
        fromChatId: String,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.copyMessages(
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = bot.copyMessages(
        chatId = chatId,
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    suspend fun Argument.sendPhoto(
        photo: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendPhoto(
        photo: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendAudio(
        audio: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendAudio(
        audio: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendDocument(
        document: NamedContent,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendDocument(
        document: String,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVideo(
        video: Content,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVideo(
        video: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendAnimation(
        animation: Content,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendAnimation(
        animation: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVoice(
        voice: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVoice(
        voice: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVideoNote(
        videoNote: Content,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendVideoNote(
        chatId = chatId,
        videoNote = videoNote,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVideoNote(
        videoNote: String,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendVideoNote(
        chatId = chatId,
        videoNote = videoNote,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendMediaGroup(
        media: Iterable<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
    ): ArrayList<Message> = bot.sendMediaGroup(
        chatId = chatId,
        media = media,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
    )

    suspend fun Argument.sendLocation(
        latitude: Float,
        longitude: Float,
        messageThreadId: Long? = null,
        horizontalAccuracy: Float? = null,
        livePeriod: Long? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendVenue(
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        messageThreadId: Long? = null,
        foursquareId: String? = null,
        foursquareType: String? = null,
        googlePlaceId: String? = null,
        googlePlaceType: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendContact(
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long? = null,
        lastName: String? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendContact(
        chatId = chatId,
        phoneNumber = phoneNumber,
        firstName = firstName,
        messageThreadId = messageThreadId,
        lastName = lastName,
        vcard = vcard,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendPoll(
        question: String,
        options: List<String>,
        messageThreadId: Long? = null,
        isAnonymous: Boolean? = null,
        type: String? = null,
        allowsMultipleAnswers: Boolean? = null,
        correctOptionId: Long? = null,
        explanation: String? = null,
        explanationParseMode: String? = null,
        explanationEntities: List<MessageEntity>? = null,
        openPeriod: Long? = null,
        closeDate: Long? = null,
        isClosed: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendDice(
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendDice(
        chatId = chatId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendChatAction(
        action: Action,
        messageThreadId: Long? = null,
    ): Boolean = bot.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun Argument.getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long? = null,
    ): UserProfilePhotos = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun Argument.getFile(
        fileId: String,
    ): File = bot.getFile(
        fileId = fileId
    )

    suspend fun Argument.banChatMember(
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean = bot.banChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages
    )

    suspend fun Argument.unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean = bot.unbanChatMember(
        chatId = chatId,
        userId = userId,
        onlyIfBanned = onlyIfBanned
    )

    suspend fun Argument.restrictChatMember(
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ): Boolean = bot.restrictChatMember(
        chatId = chatId,
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate
    )

    suspend fun Argument.promoteChatMember(
        userId: Long,
        isAnonymous: Boolean? = null,
        canManageChat: Boolean? = null,
        canDeleteMessages: Boolean? = null,
        canManageVideoChats: Boolean? = null,
        canRestrictMembers: Boolean? = null,
        canPromoteMembers: Boolean? = null,
        canChangeInfo: Boolean? = null,
        canInviteUsers: Boolean? = null,
        canPostMessages: Boolean? = null,
        canEditMessages: Boolean? = null,
        canPinMessages: Boolean? = null,
        canPostStories: Boolean? = null,
        canEditStories: Boolean? = null,
        canDeleteStories: Boolean? = null,
        canManageTopics: Boolean? = null,
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

    suspend fun Argument.setChatAdministratorCustomTitle(
        userId: Long,
        customTitle: String,
    ): Boolean = bot.setChatAdministratorCustomTitle(
        chatId = chatId,
        userId = userId,
        customTitle = customTitle
    )

    suspend fun Argument.banChatSenderChat(
        senderChatId: Long,
    ): Boolean = bot.banChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    suspend fun Argument.unbanChatSenderChat(
        senderChatId: Long,
    ): Boolean = bot.unbanChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    suspend fun Argument.setChatPermissions(
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean = bot.setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions
    )

    suspend fun Argument.exportChatInviteLink(): String = bot.exportChatInviteLink(
        chatId = chatId
    )

    suspend fun Argument.createChatInviteLink(
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = bot.createChatInviteLink(
        chatId = chatId,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    suspend fun Argument.editChatInviteLink(
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = bot.editChatInviteLink(
        chatId = chatId,
        inviteLink = inviteLink,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    suspend fun Argument.revokeChatInviteLink(
        inviteLink: String,
    ): ChatInviteLink = bot.revokeChatInviteLink(
        chatId = chatId,
        inviteLink = inviteLink
    )

    suspend fun Argument.approveChatJoinRequest(
        userId: Long,
    ): Boolean = bot.approveChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    suspend fun Argument.declineChatJoinRequest(
        userId: Long,
    ): Boolean = bot.declineChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    suspend fun Argument.setChatPhoto(
        photo: Content,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    suspend fun Argument.setChatPhoto(
        photo: String,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    suspend fun Argument.deleteChatPhoto(): Boolean = bot.deleteChatPhoto(
        chatId = chatId
    )

    suspend fun Argument.setChatTitle(
        title: String,
    ): Boolean = bot.setChatTitle(
        chatId = chatId,
        title = title
    )

    suspend fun Argument.setChatDescription(
        description: String,
    ): Boolean = bot.setChatDescription(
        chatId = chatId,
        description = description
    )

    suspend fun Argument.pinChatMessage(
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean = bot.pinChatMessage(
        chatId = chatId,
        messageId = messageId,
        disableNotification = disableNotification
    )

    suspend fun Argument.unpinChatMessage(
        messageId: Long? = null,
    ): Boolean = bot.unpinChatMessage(
        chatId = chatId,
        messageId = messageId
    )

    suspend fun Argument.unpinAllChatMessages(): Boolean = bot.unpinAllChatMessages(
        chatId = chatId
    )

    suspend fun Argument.leaveChat(): Boolean = bot.leaveChat(
        chatId = chatId
    )

    suspend fun Argument.getChat(): Chat = bot.getChat(
        chatId = chatId
    )

    suspend fun Argument.getChatAdministrators(): ArrayList<ChatMember> = bot.getChatAdministrators(
        chatId = chatId
    )

    suspend fun Argument.getChatMemberCount(): Long = bot.getChatMemberCount(
        chatId = chatId
    )

    suspend fun Argument.getChatMember(
        userId: Long,
    ): ChatMember = bot.getChatMember(
        chatId = chatId,
        userId = userId
    )

    suspend fun Argument.setChatStickerSet(
        stickerSetName: String,
    ): Boolean = bot.setChatStickerSet(
        chatId = chatId,
        stickerSetName = stickerSetName
    )

    suspend fun Argument.deleteChatStickerSet(): Boolean = bot.deleteChatStickerSet(
        chatId = chatId
    )

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long? = null,
    ): Boolean = bot.answerCallbackQuery(
        callbackQueryId = callbackQueryId,
        text = text,
        showAlert = showAlert,
        url = url,
        cacheTime = cacheTime
    )

    suspend fun Argument.getUserChatBoosts(
        userId: Long,
    ): UserChatBoosts = bot.getUserChatBoosts(
        chatId = chatId,
        userId = userId
    )

    suspend fun Argument.setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyCommands(
        commands = commands,
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Argument.deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = bot.deleteMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Argument.getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): List<BotCommand> = bot.getMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Argument.setMyName(
        name: String? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyName(
        name = name,
        languageCode = languageCode
    )

    suspend fun Argument.getMyName(
        languageCode: String? = null,
    ): BotName = bot.getMyName(
        languageCode = languageCode
    )

    suspend fun Argument.setMyDescription(
        description: String? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyDescription(
        description = description,
        languageCode = languageCode
    )

    suspend fun Argument.getMyDescription(
        languageCode: String? = null,
    ): BotDescription = bot.getMyDescription(
        languageCode = languageCode
    )

    suspend fun Argument.setMyShortDescription(
        shortDescription: String? = null, languageCode: String? = null,
    ): Boolean = bot.setMyShortDescription(
        shortDescription = shortDescription,
        languageCode = languageCode
    )

    suspend fun Argument.getMyShortDescription(
        languageCode: String? = null,
    ): BotShortDescription = bot.getMyShortDescription(
        languageCode = languageCode
    )

    suspend fun Argument.editMessageLiveLocation(
        messageId: Long,
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageLiveLocation(
        latitude = latitude,
        longitude = longitude,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.stopMessageLiveLocation(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.stopMessageLiveLocation(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.setChatMenuButton(
        chatId: Long? = null,
        menuButton: MenuButton? = null,
    ): Boolean = bot.setChatMenuButton(
        chatId = chatId,
        menuButton = menuButton
    )

    suspend fun Argument.getChatMenuButton(
        chatId: Long? = null,
    ): MenuButton = bot.getChatMenuButton(
        chatId = chatId
    )

    suspend fun Argument.setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ): Boolean = bot.setMyDefaultAdministratorRights(
        rights = rights,
        forChannels = forChannels
    )

    suspend fun Argument.getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ): ChatAdministratorRights = bot.getMyDefaultAdministratorRights(
        forChannels = forChannels
    )

    suspend fun Argument.getForumTopicIconStickers(): List<Sticker> = bot.getForumTopicIconStickers()

    suspend fun Argument.createForumTopic(
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun Argument.editForumTopic(
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun Argument.closeForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Argument.reopenForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Argument.deleteForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Argument.unpinAllForumTopicMessages(
        messageThreadId: Long,
    ): Boolean = bot.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Argument.editGeneralForumTopic(
        name: String,
    ): Boolean = bot.editGeneralForumTopic(
        chatId = chatId,
        name = name
    )

    suspend fun Argument.closeGeneralForumTopic(): Boolean = bot.closeGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Argument.reopenGeneralForumTopic(): Boolean = bot.reopenGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Argument.hideGeneralForumTopic(): Boolean = bot.hideGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Argument.unhideGeneralForumTopic(): Boolean = bot.unhideGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Argument.unpinAllGeneralForumTopicMessages(): Boolean = bot.unpinAllGeneralForumTopicMessages(
        chatId = chatId
    )

    suspend fun Argument.sendGame(
        gameShortName: String,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.sendGame(
        chatId = chatId,
        gameShortName = gameShortName,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.setGameScore(
        userId: Long,
        score: Long,
        force: Boolean? = null,
        disableEditMessage: Boolean? = null,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
    ): Message = bot.setGameScore(
        userId = userId,
        score = score,
        force = force,
        disableEditMessage = disableEditMessage,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    suspend fun Argument.getGameHighScores(
        userId: Long,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
    ): List<GameHighScore> = bot.getGameHighScores(
        userId = userId,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        button: InlineQueryResultsButton? = null,
    ): Boolean = bot.answerInlineQuery(
        inlineQueryId = inlineQueryId,
        results = results,
        cacheTime = cacheTime,
        isPersonal = isPersonal,
        nextOffset = nextOffset,
        button = button
    )

    suspend fun answerWebAppQuery(
        webAppQueryId: String,
        result: InlineQueryResult,
    ): SentWebAppMessage = bot.answerWebAppQuery(
        webAppQueryId = webAppQueryId,
        result = result
    )

    suspend fun Argument.setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>,
    ): Boolean = bot.setPassportDataErrors(
        userId = userId,
        errors = errors
    )

    suspend fun Argument.sendInvoice(
        title: String,
        description: String,
        payload: String,
        providerToken: String,
        currency: String,
        prices: List<LabeledPrice>,
        messageThreadId: Long? = null,
        maxTipAmount: Int? = null,
        suggestedTipAmount: List<Int>? = null,
        startParameter: String? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.createInvoiceLink(
        title: String,
        description: String,
        payload: String,
        providerToken: String,
        currency: String,
        prices: List<LabeledPrice>,
        maxTipAmount: Int? = null,
        suggestedTipAmount: List<Int>? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
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

    suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>? = null,
        errorMessage: String? = null,
    ): Boolean = bot.answerShippingQuery(
        shippingQueryId = shippingQueryId,
        ok = ok,
        shippingOptions = shippingOptions,
        errorMessage = errorMessage
    )

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String? = null,
    ): Boolean = bot.answerPreCheckoutQuery(
        preCheckoutQueryId = preCheckoutQueryId,
        ok = ok,
        errorMessage = errorMessage
    )

    suspend fun Argument.sendSticker(
        sticker: Content,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendSticker(
        chatId = chatId,
        sticker = sticker,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.getStickerSet(
        name: String,
    ): StickerSet = bot.getStickerSet(
        name = name
    )

    suspend fun Argument.getCustomEmojiStickers(
        customEmojiIds: List<String>,
    ): List<Sticker> = bot.getCustomEmojiStickers(
        customEmojiIds = customEmojiIds
    )

    suspend fun Argument.uploadStickerFile(
        userId: Long,
        sticker: Content,
        stickerFormat: String,
    ): File = bot.uploadStickerFile(
        userId = userId,
        sticker = sticker,
        stickerFormat = stickerFormat
    )

    suspend fun Argument.createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerFormat: String,
        stickerType: String? = null,
        needsRepainting: Boolean? = null,
    ): Boolean = bot.createNewStickerSet(
        userId = userId,
        name = name,
        title = title,
        stickers = stickers,
        stickerFormat = stickerFormat,
        stickerType = stickerType,
        needsRepainting = needsRepainting
    )

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = bot.addStickerToSet(
        userId = userId,
        name = name,
        sticker = sticker
    )

    suspend fun Argument.setStickerPositionInSet(
        sticker: String,
        position: Int,
    ): Boolean = bot.setStickerPositionInSet(
        sticker = sticker,
        position = position
    )

    suspend fun Argument.deleteStickerFromSet(
        sticker: String,
    ): Boolean = bot.deleteStickerFromSet(
        sticker = sticker
    )

    suspend fun Argument.setStickerEmojiList(
        sticker: String,
        emojiList: Iterable<String>,
    ): Boolean = bot.setStickerEmojiList(
        sticker = sticker,
        emojiList = emojiList
    )

    suspend fun Argument.setStickerKeywords(
        sticker: String,
        keywords: Iterable<String>? = null,
    ): Boolean = bot.setStickerKeywords(
        sticker = sticker,
        keywords = keywords
    )

    suspend fun Argument.setStickerMaskPosition(
        sticker: String,
        maskPosition: MaskPosition? = null,
    ): Boolean = bot.setStickerMaskPosition(
        sticker = sticker,
        maskPosition = maskPosition
    )

    suspend fun Argument.setStickerSetTitle(
        sticker: String,
        title: String,
    ): Boolean = bot.setStickerSetTitle(
        sticker = sticker,
        title = title
    )

    suspend fun Argument.setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: Content? = null,
    ): Boolean = bot.setStickerSetThumbnail(
        name = name,
        userId = userId,
        thumbnail = thumbnail
    )

    suspend fun Argument.setCustomEmojiStickerSetThumbnail(
        name: String,
        customEmojiId: String? = null,
    ): Boolean = bot.setCustomEmojiStickerSetThumbnail(
        name = name,
        customEmojiId = customEmojiId
    )

    suspend fun Argument.deleteStickerSet(
        name: String,
    ): Boolean = bot.deleteStickerSet(
        name = name,
    )

    suspend fun Argument.getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: List<AllowedUpdate>? = null,
    ): List<Update> = bot.getUpdates(
        offset = offset,
        limit = limit,
        timeout = timeout,
        allowedUpdates = allowedUpdates
    )

    suspend fun Argument.setWebhook(
        url: String,
        certificate: NamedContent? = null,
        ipAddress: String? = null,
        maxConnections: Int? = null,
        allowedUpdates: List<AllowedUpdate>? = null,
        dropPendingUpdates: Boolean? = null,
        secretToken: String? = null,
    ): Boolean = bot.setWebhook(
        url = url,
        certificate = certificate,
        ipAddress = ipAddress,
        maxConnections = maxConnections,
        allowedUpdates = allowedUpdates,
        dropPendingUpdates = dropPendingUpdates,
        secretToken = secretToken
    )

    suspend fun Argument.deleteWebhook(
        dropPendingUpdates: Boolean? = null,
    ): Boolean = bot.deleteWebhook(
        dropPendingUpdates = dropPendingUpdates
    )

    suspend fun Argument.getWebhookInfo(): WebhookInfo = bot.getWebhookInfo()

    suspend fun Argument.editMessageText(
        messageId: Long,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageText(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.editMessageCaption(
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageCaption(
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = null,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.editMessageMedia(
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageMedia(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.editMessageReplyMarkup(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.stopPoll(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll = bot.stopPoll(
        chatId = chatId,
        messageId = messageId,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.deleteMessage(
        messageId: Long,
    ): Boolean = bot.deleteMessage(
        chatId = chatId,
        messageId = messageId
    )

    suspend fun Argument.deleteMessages(
        messageIds: Iterable<Long>,
    ): Boolean = bot.deleteMessages(
        chatId = chatId,
        messageIds = messageIds
    )
    //endregion Telegram methods
}