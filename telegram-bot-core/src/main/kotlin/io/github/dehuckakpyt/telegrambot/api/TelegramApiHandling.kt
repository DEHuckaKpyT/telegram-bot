package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.ext.container.chatId
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
    suspend fun Container.getMe(): User = bot.getMe()

    suspend fun Container.logOut(): Boolean = bot.logOut()

    suspend fun Container.close(): Boolean = bot.close()

    suspend fun Container.sendMessage(
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        linkPreviewOptions = linkPreviewOptions,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.forwardMessage(
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

    suspend fun Container.forwardMessages(
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

    suspend fun Container.copyMessage(
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

    suspend fun Container.copyMessages(
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

    suspend fun Container.sendPhoto(
        photo: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendPhoto(
        photo: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendAudio(
        audio: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendAudio(
        audio: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendDocument(
        document: NamedContent,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendDocument(
        document: String,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendVideo(
        video: Content,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendVideo(
        video: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendAnimation(
        animation: Content,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendAnimation(
        animation: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: Content? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendVoice(
        voice: Content,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendVoice(
        voice: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendVideoNote(
        videoNote: Content,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendVideoNote(
        videoNote: String,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendMediaGroup(
        media: Iterable<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
    ): ArrayList<Message> = bot.sendMediaGroup(
        chatId = chatId,
        businessConnectionId = businessConnectionId,
        media = media,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
    )

    suspend fun Container.sendLocation(
        latitude: Float,
        longitude: Float,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendVenue(
        latitude: Float,
        longitude: Float,
        title: String,
        address: String,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendContact(
        phoneNumber: String,
        firstName: String,
        businessConnectionId: String? = null,
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
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        lastName = lastName,
        vcard = vcard,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendPoll(
        question: String,
        options: List<InputPollOption>,
        questionParseMode: String? = null,
        questionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
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
        questionParseMode = questionParseMode,
        questionEntities = questionEntities,
        businessConnectionId = businessConnectionId,
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

    suspend fun Container.sendDice(
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendDice(
        chatId = chatId,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.sendChatAction(
        action: Action,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
    ): Boolean = bot.sendChatAction(
        chatId = chatId,
        action = action,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId
    )

    suspend fun Container.getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long? = null,
    ): UserProfilePhotos = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun Container.getFile(
        fileId: String,
    ): File = bot.getFile(
        fileId = fileId
    )

    suspend fun Container.banChatMember(
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean = bot.banChatMember(
        chatId = chatId,
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages
    )

    suspend fun Container.unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean = bot.unbanChatMember(
        chatId = chatId,
        userId = userId,
        onlyIfBanned = onlyIfBanned
    )

    suspend fun Container.restrictChatMember(
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

    suspend fun Container.promoteChatMember(
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

    suspend fun Container.setChatAdministratorCustomTitle(
        userId: Long,
        customTitle: String,
    ): Boolean = bot.setChatAdministratorCustomTitle(
        chatId = chatId,
        userId = userId,
        customTitle = customTitle
    )

    suspend fun Container.banChatSenderChat(
        senderChatId: Long,
    ): Boolean = bot.banChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    suspend fun Container.unbanChatSenderChat(
        senderChatId: Long,
    ): Boolean = bot.unbanChatSenderChat(
        chatId = chatId,
        senderChatId = senderChatId
    )

    suspend fun Container.setChatPermissions(
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean = bot.setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions
    )

    suspend fun Container.exportChatInviteLink(): String = bot.exportChatInviteLink(
        chatId = chatId
    )

    suspend fun Container.createChatInviteLink(
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

    suspend fun Container.editChatInviteLink(
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

    suspend fun Container.revokeChatInviteLink(
        inviteLink: String,
    ): ChatInviteLink = bot.revokeChatInviteLink(
        chatId = chatId,
        inviteLink = inviteLink
    )

    suspend fun Container.approveChatJoinRequest(
        userId: Long,
    ): Boolean = bot.approveChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    suspend fun Container.declineChatJoinRequest(
        userId: Long,
    ): Boolean = bot.declineChatJoinRequest(
        chatId = chatId,
        userId = userId
    )

    suspend fun Container.setChatPhoto(
        photo: Content,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    suspend fun Container.setChatPhoto(
        photo: String,
    ): Boolean = bot.setChatPhoto(
        chatId = chatId,
        photo = photo
    )

    suspend fun Container.deleteChatPhoto(): Boolean = bot.deleteChatPhoto(
        chatId = chatId
    )

    suspend fun Container.setChatTitle(
        title: String,
    ): Boolean = bot.setChatTitle(
        chatId = chatId,
        title = title
    )

    suspend fun Container.setChatDescription(
        description: String,
    ): Boolean = bot.setChatDescription(
        chatId = chatId,
        description = description
    )

    suspend fun Container.pinChatMessage(
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean = bot.pinChatMessage(
        chatId = chatId,
        messageId = messageId,
        disableNotification = disableNotification
    )

    suspend fun Container.unpinChatMessage(
        messageId: Long? = null,
    ): Boolean = bot.unpinChatMessage(
        chatId = chatId,
        messageId = messageId
    )

    suspend fun Container.unpinAllChatMessages(): Boolean = bot.unpinAllChatMessages(
        chatId = chatId
    )

    suspend fun Container.leaveChat(): Boolean = bot.leaveChat(
        chatId = chatId
    )

    suspend fun Container.getChat(): ChatFullInfo = bot.getChat(
        chatId = chatId
    )

    suspend fun Container.getChatAdministrators(): ArrayList<ChatMember> = bot.getChatAdministrators(
        chatId = chatId
    )

    suspend fun Container.getChatMemberCount(): Long = bot.getChatMemberCount(
        chatId = chatId
    )

    suspend fun Container.getChatMember(
        userId: Long,
    ): ChatMember = bot.getChatMember(
        chatId = chatId,
        userId = userId
    )

    suspend fun Container.setChatStickerSet(
        stickerSetName: String,
    ): Boolean = bot.setChatStickerSet(
        chatId = chatId,
        stickerSetName = stickerSetName
    )

    suspend fun Container.deleteChatStickerSet(): Boolean = bot.deleteChatStickerSet(
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

    suspend fun Container.getUserChatBoosts(
        userId: Long,
    ): UserChatBoosts = bot.getUserChatBoosts(
        chatId = chatId,
        userId = userId
    )

    suspend fun Container.getBusinessConnection(
        businessConnectionId: String,
    ): BusinessConnection = bot.getBusinessConnection(
        businessConnectionId = businessConnectionId
    )

    suspend fun Container.setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyCommands(
        commands = commands,
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Container.deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = bot.deleteMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Container.getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): List<BotCommand> = bot.getMyCommands(
        scope = scope,
        languageCode = languageCode
    )

    suspend fun Container.setMyName(
        name: String? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyName(
        name = name,
        languageCode = languageCode
    )

    suspend fun Container.getMyName(
        languageCode: String? = null,
    ): BotName = bot.getMyName(
        languageCode = languageCode
    )

    suspend fun Container.setMyDescription(
        description: String? = null,
        languageCode: String? = null,
    ): Boolean = bot.setMyDescription(
        description = description,
        languageCode = languageCode
    )

    suspend fun Container.getMyDescription(
        languageCode: String? = null,
    ): BotDescription = bot.getMyDescription(
        languageCode = languageCode
    )

    suspend fun Container.setMyShortDescription(
        shortDescription: String? = null, languageCode: String? = null,
    ): Boolean = bot.setMyShortDescription(
        shortDescription = shortDescription,
        languageCode = languageCode
    )

    suspend fun Container.getMyShortDescription(
        languageCode: String? = null,
    ): BotShortDescription = bot.getMyShortDescription(
        languageCode = languageCode
    )

    suspend fun Container.editMessageLiveLocation(
        messageId: Long,
        latitude: Float,
        longitude: Float,
        livePeriod: Int? = null,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageLiveLocation(
        latitude = latitude,
        longitude = longitude,
        livePeriod = livePeriod,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Container.stopMessageLiveLocation(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.stopMessageLiveLocation(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Container.setChatMenuButton(
        chatId: Long? = null,
        menuButton: MenuButton? = null,
    ): Boolean = bot.setChatMenuButton(
        chatId = chatId,
        menuButton = menuButton
    )

    suspend fun Container.getChatMenuButton(
        chatId: Long? = null,
    ): MenuButton = bot.getChatMenuButton(
        chatId = chatId
    )

    suspend fun Container.setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ): Boolean = bot.setMyDefaultAdministratorRights(
        rights = rights,
        forChannels = forChannels
    )

    suspend fun Container.getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ): ChatAdministratorRights = bot.getMyDefaultAdministratorRights(
        forChannels = forChannels
    )

    suspend fun Container.getForumTopicIconStickers(): List<Sticker> = bot.getForumTopicIconStickers()

    suspend fun Container.createForumTopic(
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun Container.editForumTopic(
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun Container.closeForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Container.reopenForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Container.deleteForumTopic(
        messageThreadId: Long,
    ): Boolean = bot.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Container.unpinAllForumTopicMessages(
        messageThreadId: Long,
    ): Boolean = bot.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId
    )

    suspend fun Container.editGeneralForumTopic(
        name: String,
    ): Boolean = bot.editGeneralForumTopic(
        chatId = chatId,
        name = name
    )

    suspend fun Container.closeGeneralForumTopic(): Boolean = bot.closeGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Container.reopenGeneralForumTopic(): Boolean = bot.reopenGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Container.hideGeneralForumTopic(): Boolean = bot.hideGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Container.unhideGeneralForumTopic(): Boolean = bot.unhideGeneralForumTopic(
        chatId = chatId
    )

    suspend fun Container.unpinAllGeneralForumTopicMessages(): Boolean = bot.unpinAllGeneralForumTopicMessages(
        chatId = chatId
    )

    suspend fun Container.sendGame(
        gameShortName: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.sendGame(
        chatId = chatId,
        gameShortName = gameShortName,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.setGameScore(
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

    suspend fun Container.getGameHighScores(
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

    suspend fun Container.setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>,
    ): Boolean = bot.setPassportDataErrors(
        userId = userId,
        errors = errors
    )

    suspend fun Container.sendInvoice(
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

    suspend fun Container.createInvoiceLink(
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

    suspend fun Container.sendSticker(
        sticker: Content,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = bot.sendSticker(
        chatId = chatId,
        sticker = sticker,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun Container.getStickerSet(
        name: String,
    ): StickerSet = bot.getStickerSet(
        name = name
    )

    suspend fun Container.getCustomEmojiStickers(
        customEmojiIds: List<String>,
    ): List<Sticker> = bot.getCustomEmojiStickers(
        customEmojiIds = customEmojiIds
    )

    suspend fun Container.uploadStickerFile(
        userId: Long,
        sticker: Content,
        stickerFormat: String,
    ): File = bot.uploadStickerFile(
        userId = userId,
        sticker = sticker,
        stickerFormat = stickerFormat
    )

    suspend fun Container.createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerType: String? = null,
        needsRepainting: Boolean? = null,
    ): Boolean = bot.createNewStickerSet(
        userId = userId,
        name = name,
        title = title,
        stickers = stickers,
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

    suspend fun Container.setStickerPositionInSet(
        sticker: String,
        position: Int,
    ): Boolean = bot.setStickerPositionInSet(
        sticker = sticker,
        position = position
    )

    suspend fun Container.deleteStickerFromSet(
        sticker: String,
    ): Boolean = bot.deleteStickerFromSet(
        sticker = sticker
    )

    suspend fun Container.replaceStickerInSet(
        userId: Long,
        name: String,
        oldSticker: String,
        sticker: InputSticker,
    ): Boolean = bot.replaceStickerInSet(
        userId = userId,
        name = name,
        oldSticker = oldSticker,
        sticker = sticker
    )

    suspend fun Container.setStickerEmojiList(
        sticker: String,
        emojiList: Iterable<String>,
    ): Boolean = bot.setStickerEmojiList(
        sticker = sticker,
        emojiList = emojiList
    )

    suspend fun Container.setStickerKeywords(
        sticker: String,
        keywords: Iterable<String>? = null,
    ): Boolean = bot.setStickerKeywords(
        sticker = sticker,
        keywords = keywords
    )

    suspend fun Container.setStickerMaskPosition(
        sticker: String,
        maskPosition: MaskPosition? = null,
    ): Boolean = bot.setStickerMaskPosition(
        sticker = sticker,
        maskPosition = maskPosition
    )

    suspend fun Container.setStickerSetTitle(
        sticker: String,
        title: String,
    ): Boolean = bot.setStickerSetTitle(
        sticker = sticker,
        title = title
    )

    suspend fun Container.setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: Content? = null,
    ): Boolean = bot.setStickerSetThumbnail(
        name = name,
        userId = userId,
        format = format,
        thumbnail = thumbnail
    )

    suspend fun Container.setCustomEmojiStickerSetThumbnail(
        name: String,
        customEmojiId: String? = null,
    ): Boolean = bot.setCustomEmojiStickerSetThumbnail(
        name = name,
        customEmojiId = customEmojiId
    )

    suspend fun Container.deleteStickerSet(
        name: String,
    ): Boolean = bot.deleteStickerSet(
        name = name,
    )

    suspend fun Container.getUpdates(
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

    suspend fun Container.setWebhook(
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

    suspend fun Container.deleteWebhook(
        dropPendingUpdates: Boolean? = null,
    ): Boolean = bot.deleteWebhook(
        dropPendingUpdates = dropPendingUpdates
    )

    suspend fun Container.getWebhookInfo(): WebhookInfo = bot.getWebhookInfo()

    suspend fun Container.editMessageText(
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

    suspend fun Container.editMessageCaption(
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

    suspend fun Container.editMessageMedia(
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

    suspend fun Container.editMessageReplyMarkup(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = bot.editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun Container.stopPoll(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll = bot.stopPoll(
        chatId = chatId,
        messageId = messageId,
        replyMarkup = replyMarkup
    )

    suspend fun Container.deleteMessage(
        messageId: Long,
    ): Boolean = bot.deleteMessage(
        chatId = chatId,
        messageId = messageId
    )

    suspend fun Container.deleteMessages(
        messageIds: Iterable<Long>,
    ): Boolean = bot.deleteMessages(
        chatId = chatId,
        messageIds = messageIds
    )
    //endregion Telegram methods
}