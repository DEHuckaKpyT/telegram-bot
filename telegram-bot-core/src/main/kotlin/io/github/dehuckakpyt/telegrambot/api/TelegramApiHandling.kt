package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.argument.Argument
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
abstract class TelegramApiHandling : KoinComponent {

    val bot = get<TelegramBot>()

    //region Telegram methods
    suspend fun getMe(): User = bot.getMe()

    suspend fun logOut(): Boolean = bot.logOut()

    suspend fun close(): Boolean = bot.close()

    suspend fun Argument.sendMessage(
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableWebPagePreview: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.forwardMessage(
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

    suspend fun Argument.copyMessage(
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

    suspend fun Argument.sendPhoto(
        photo: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
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
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
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
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
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
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendAudio(
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

    suspend fun Argument.sendAudio(
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

    suspend fun Argument.sendDocument(
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

    suspend fun Argument.sendDocument(
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

    suspend fun Argument.sendVideo(
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

    suspend fun Argument.sendVideo(
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

    suspend fun Argument.sendAnimation(
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

    suspend fun Argument.sendAnimation(
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

    suspend fun Argument.sendVoice(
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

    suspend fun Argument.sendVoice(
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

    suspend fun Argument.sendVideoNote(
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

    suspend fun Argument.sendVideoNote(
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

    suspend fun Argument.sendMediaGroup(
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

    suspend fun Argument.sendLocation(
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

    suspend fun Argument.sendVenue(
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

    suspend fun Argument.sendContact(
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

    suspend fun Argument.sendPoll(
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

    suspend fun Argument.sendDice(
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

    suspend fun Argument.sendChatAction(
        action: Action,
        messageThreadId: Long?,
    ): Boolean = bot.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long?,
        limit: Long?,
    ): UserProfilePhotos = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun getFile(
        fileId: String,
    ): File = bot.getFile(
        fileId = fileId
    )

    suspend fun Argument.banChatMember(
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = bot.banChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages
    )

    suspend fun Argument.unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean?,
    ): Boolean = bot.unbanChatMember(
        chatId = chatId,
        userId = userId,
        onlyIfBanned = onlyIfBanned
    )

    suspend fun Argument.restrictChatMember(
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

    suspend fun Argument.promoteChatMember(
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
        useIndependentChatPermissions: Boolean?,
    ): Boolean = bot.setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions
    )

    suspend fun Argument.exportChatInviteLink(): String = bot.exportChatInviteLink(
        chatId = chatId
    )

    suspend fun Argument.createChatInviteLink(
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

    suspend fun Argument.editChatInviteLink(
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
        photo: NamedContent,
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
        disableNotification: Boolean?,
    ): Boolean = bot.pinChatMessage(
        chatId = chatId,
        messageId = messageId,
        disableNotification = disableNotification
    )

    suspend fun Argument.unpinChatMessage(
        messageId: Long?,
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

    suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = bot.setMyCommands(
        commands = commands,
        scope = scope,
        languageCode = languageCode
    )

    suspend fun deleteMyCommands(
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = bot.deleteMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun getMyCommands(
        scope: BotCommandScope?,
        languageCode: String?,
    ): List<BotCommand> = bot.getMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun setMyName(
        name: String?,
        languageCode: String?,
    ): Boolean = bot.setMyName(
        name = name,
        languageCode = languageCode
    )

    suspend fun getMyName(
        languageCode: String?,
    ): BotName = bot.getMyName(
        languageCode = languageCode
    )

    suspend fun setMyDescription(
        description: String?,
        languageCode: String?,
    ): Boolean = bot.setMyDescription(
        description = description,
        languageCode = languageCode
    )

    suspend fun getMyDescription(
        languageCode: String?,
    ): BotDescription = bot.getMyDescription(
        languageCode = languageCode
    )

    suspend fun setMyShortDescription(
        shortDescription: String?, languageCode: String?,
    ): Boolean = bot.setMyShortDescription(
        shortDescription = shortDescription,
        languageCode = languageCode
    )

    suspend fun getMyShortDescription(
        languageCode: String?,
    ): BotShortDescription = bot.getMyShortDescription(
        languageCode = languageCode
    )

    suspend fun Argument.editMessageLiveLocation(
        messageId: Long,
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float?,
        heading: Long?,
        proximityAlertRadius: Long?,
        replyMarkup: InlineKeyboardMarkup?,
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
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.stopMessageLiveLocation(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun setChatMenuButton(
        chatId: Long?,
        menuButton: MenuButton?,
    ): Boolean = bot.setChatMenuButton(
        chatId = chatId,
        menuButton = menuButton
    )

    suspend fun getChatMenuButton(
        chatId: Long?,
    ): MenuButton = bot.getChatMenuButton(
        chatId = chatId
    )

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?,
    ): Boolean = bot.setMyDefaultAdministratorRights(
        rights = rights,
        forChannels = forChannels
    )

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean?,
    ): ChatAdministratorRights = bot.getMyDefaultAdministratorRights(
        forChannels = forChannels
    )

    suspend fun getForumTopicIconStickers(): List<Sticker> = bot.getForumTopicIconStickers()

    suspend fun Argument.createForumTopic(
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun Argument.editForumTopic(
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
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
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
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

    suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): Message = bot.setGameScore(
        userId = userId,
        score = score,
        force = force,
        disableEditMessage = disableEditMessage,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = bot.getGameHighScores(
        userId = userId,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId
    )

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
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

    suspend fun setPassportDataErrors(
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
        replyMarkup: InlineKeyboardMarkup?,
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

    suspend fun createInvoiceLink(
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
        isFlexible: Boolean?,
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
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = bot.answerShippingQuery(
        shippingQueryId = shippingQueryId,
        ok = ok,
        shippingOptions = shippingOptions,
        errorMessage = errorMessage
    )

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = bot.answerPreCheckoutQuery(
        preCheckoutQueryId = preCheckoutQueryId,
        ok = ok,
        errorMessage = errorMessage
    )

    suspend fun Argument.sendSticker(
        sticker: NamedContent,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?,
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

    suspend fun getStickerSet(
        name: String,
    ): StickerSet = bot.getStickerSet(
        name = name
    )

    suspend fun getCustomEmojiStickers(
        customEmojiIds: List<String>,
    ): List<Sticker> = bot.getCustomEmojiStickers(
        customEmojiIds = customEmojiIds
    )

    suspend fun uploadStickerFile(
        userId: Long,
        sticker: NamedContent,
        stickerFormat: String,
    ): File = bot.uploadStickerFile(
        userId = userId,
        sticker = sticker,
        stickerFormat = stickerFormat
    )

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Collection<Any>,
        stickerFormat: String,
        stickerType: String?,
        needsRepainting: Boolean?,
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
        sticker: Any,
    ): Boolean = bot.addStickerToSet(
        userId = userId,
        name = name,
        sticker = sticker
    )

    suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int,
    ): Boolean = bot.setStickerPositionInSet(
        sticker = sticker,
        position = position
    )

    suspend fun deleteStickerFromSet(
        sticker: String,
    ): Boolean = bot.deleteStickerFromSet(
        sticker = sticker
    )

    suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: Any?,
    ): Boolean = bot.setStickerSetThumbnail(
        name = name,
        userId = userId,
        thumbnail = thumbnail
    )

    suspend fun getUpdates(
        offset: Int?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: List<AllowedUpdate>?,
    ): List<UpdateResponse> = bot.getUpdates(
        offset = offset,
        limit = limit,
        timeout = timeout,
        allowedUpdates = allowedUpdates
    )

    suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ): Boolean = bot.setWebhook(
        url = url,
        certificate = certificate,
        ipAddress = ipAddress,
        maxConnections = maxConnections,
        allowedUpdates = allowedUpdates,
        dropPendingUpdates = dropPendingUpdates,
        secretToken = secretToken
    )

    suspend fun deleteWebhook(
        dropPendingUpdates: Boolean?,
    ): Boolean = bot.deleteWebhook(
        dropPendingUpdates = dropPendingUpdates
    )

    suspend fun getWebhookInfo(): WebhookInfo = bot.getWebhookInfo()

    suspend fun Argument.editMessageText(
        messageId: Long,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.editMessageText(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        text = text,
        parseMode = parseMode,
        entities = entities,
        disableWebPagePreview = disableWebPagePreview,
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
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.editMessageMedia(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.editMessageReplyMarkup(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = bot.editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.stopPoll(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?,
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
    //endregion Telegram methods
}