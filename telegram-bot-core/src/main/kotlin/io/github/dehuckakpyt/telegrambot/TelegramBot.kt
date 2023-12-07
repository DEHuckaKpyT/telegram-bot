package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import java.io.File


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBot(
    private val client: TelegramApiClient,
    private val messageSource: MessageSource,
    val username: String,
) {


    //region Telegram methods
    suspend fun getMe(): User = client.getMe()

    suspend fun logOut(): Boolean = client.logOut()

    suspend fun close(): Boolean = client.close()

    internal suspend fun getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: List<AllowedUpdate>?
    ): List<UpdateResponse> =
        client.getUpdates(
            offset,
            limit,
            timeout,
            allowedUpdates
        )

    suspend fun getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String?
    ): List<BotCommand> = client.getMyCommands(scope, languageCode)

    suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String?
    ): Boolean = client.setMyCommands(commands, scope, languageCode)

    suspend fun deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String?
    ): Boolean = client.deleteMyCommands(scope, languageCode)

    internal suspend fun setWebhook(
        url: String,
        certificate: NamedContent? = null,
        ipAddress: String? = null,
        maxConnections: Int? = null,
        allowedUpdates: List<AllowedUpdate>? = null,
        dropPendingUpdates: Boolean? = null,
        secretToken: String? = null,
    ) = client.setWebhook(url, certificate, ipAddress, maxConnections, allowedUpdates, dropPendingUpdates, secretToken)

    internal suspend fun deleteWebhook(dropPendingUpdates: Boolean?) = client.deleteWebhook(dropPendingUpdates)

    suspend fun getWebhookInfo() = client.getWebhookInfo()

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        entities: List<MessageEntity>? = null,
        disableWebPagePreview: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null
    ): Message {
        return client.sendMessage(
            chatId = chatId.toString(),
            text = text,
            messageThreadId = messageThreadId,
            parseMode = parseMode,
            entities = entities,
            disableWebPagePreview = disableWebPagePreview,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }

    suspend fun forwardMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ) = client.forwardMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        msgId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    suspend fun copyMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.copyMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendPhoto(
        chatId: Long,
        photo: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendPhoto(
        chatId = chatId.toString(),
        photo = photo,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendAudio(
        chatId: Long,
        audio: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendAudio(
        chatId = chatId.toString(),
        audio = audio,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumb = thumb,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendDocument(
        chatId: Long,
        document: NamedContent,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendDocument(
        chatId = chatId.toString(),
        document = document,
        messageThreadId = messageThreadId,
        thumb = thumb,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendVideo(
        chatId: Long,
        video: NamedContent,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        streaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVideo(
        chatId = chatId.toString(),
        video = video,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumb = thumb,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        hasSpoiler = hasSpoiler,
        streaming = streaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendAnimation(
        chatId: Long,
        animation: NamedContent,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendAnimation(
        chatId = chatId.toString(),
        animation = animation,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumb = thumb,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendVoice(
        chatId: Long,
        voice: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        messageThreadId: Long? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVoice(
        chatId = chatId.toString(),
        voice = voice,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendVideoNote(
        chatId: Long,
        note: NamedContent,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVideoNote(
        chatId = chatId.toString(),
        note = note,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumb = thumb,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendMediaGroup(
        chatId: Long,
        media: List<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean?
    ): ArrayList<Message> {
        return client.sendMediaGroup(
            chatId = chatId.toString(),
            media = media,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    }

    suspend fun sendLocation(
        chatId: Long,
        latitude: Float,
        longitude: Float,
        messageThreadId: Long? = null,
        horizontalAccuracy: Float? = null,
        livePeriod: Long? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendLocation(
        chatId = chatId.toString(),
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

    suspend fun editMessageLiveLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageLiveLocation(
            latitude = latitude,
            longitude = longitude,
            horizontalAccuracy = horizontalAccuracy,
            heading = heading,
            proximityAlertRadius = proximityAlertRadius,
            chatId = chatId?.toString(),
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            replyMarkup = replyMarkup
        )
    }

    suspend fun stopMessageLiveLocation(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.stopMessageLiveLocation(chatId?.toString(), messageId, inlineMessageId, replyMarkup)
    }

    suspend fun sendVenue(
        chatId: Long,
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
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVenue(
        chatId = chatId.toString(),
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

    suspend fun sendContact(
        chatId: Long,
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long? = null,
        lastName: String? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendContact(
        chatId = chatId.toString(),
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

    suspend fun sendChatAction(
        chatId: Long,
        action: Action,
        messageThreadId: Long? = null,
    ) = client.sendChatAction(
        chatId = chatId.toString(),
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun setChatMenuButton(chatId: Long? = null, menuButton: MenuButton?) =
        client.setChatMenuButton(chatId = chatId, menuButton = menuButton)

    suspend fun getChatMenuButton(chatId: Long? = null) =
        client.getChatMenuButton(chatId = chatId)

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean?
    ) = client.setMyDefaultAdministratorRights(rights = rights, forChannels = forChannels)

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean?
    ) = client.getMyDefaultAdministratorRights(forChannels = forChannels)

    suspend fun getForumTopicIconStickers() = client.getForumTopicIconStickers()

    suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String?
    ) = client.createForumTopic(
        chatId = chatId.toString(),
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun editForumTopic(
        chatId: Long,
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String?
    ) = client.editForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun closeForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = client.closeForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    suspend fun reopenForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = client.reopenForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    suspend fun deleteForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = client.deleteForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    suspend fun unpinAllForumTopicMessages(
        chatId: Long,
        messageThreadId: Long
    ) = client.unpinAllForumTopicMessages(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    suspend fun editGeneralForumTopic(
        chatId: Long,
        name: String
    ) = client.editGeneralForumTopic(
        chatId = chatId.toString(),
        name = name,
    )

    suspend fun closeGeneralForumTopic(
        chatId: Long,
    ) = client.closeGeneralForumTopic(
        chatId = chatId.toString(),
    )

    suspend fun reopenGeneralForumTopic(
        chatId: Long,
    ) = client.reopenGeneralForumTopic(
        chatId = chatId.toString(),
    )

    suspend fun hideGeneralForumTopic(
        chatId: Long,
    ) = client.hideGeneralForumTopic(
        chatId = chatId.toString(),
    )

    suspend fun unhideGeneralForumTopic(
        chatId: Long,
    ) = client.unhideGeneralForumTopic(
        chatId = chatId.toString(),
    )

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long?
    ) = client.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun banChatSenderChat(
        chatId: Long,
        senderString: Long
    ): Boolean = client.banChatSenderChat(chatId.toString(), senderString)

    suspend fun unbanChatSenderChat(
        chatId: Long,
        senderString: Long
    ): Boolean = client.unbanChatSenderChat(chatId.toString(), senderString)

    suspend fun getFile(fileId: String) = client.getFile(fileId)

    suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean?
    ) = client.banChatMember(
        chatId.toString(),
        userId,
        untilDate,
        revokeMessages
    )

    suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean?
    ) = client.unbanChatMember(
        chatId.toString(),
        userId,
        onlyIfBanned
    )

    suspend fun restrictChatMember(
        chatId: Long,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ) = client.restrictChatMember(
        chatId = chatId.toString(),
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate,
    )

    suspend fun promoteChatMember(
        chatId: Long,
        userId: Long,
        isAnonymous: Boolean? = null,
        canManageChat: Boolean? = null,
        canPostMessages: Boolean? = null,
        canEditMessages: Boolean? = null,
        canDeleteMessages: Boolean? = null,
        canManageVideoChats: Boolean? = null,
        canRestrictMembers: Boolean? = null,
        canPromoteMembers: Boolean? = null,
        canChangeInfo: Boolean? = null,
        canInviteUsers: Boolean? = null,
        canPinMessages: Boolean? = null,
        canManageTopics: Boolean? = null,
    ): Boolean = client.promoteChatMember(
        chatId = chatId.toString(),
        userId = userId,
        isAnonymous = isAnonymous,
        canManageChat = canManageChat,
        canPostMessages = canPostMessages,
        canEditMessages = canEditMessages,
        canDeleteMessages = canDeleteMessages,
        canManageVideoChats = canManageVideoChats,
        canRestrictMembers = canRestrictMembers,
        canPromoteMembers = canPromoteMembers,
        canChangeInfo = canChangeInfo,
        canInviteUsers = canInviteUsers,
        canPinMessages = canPinMessages,
        canManageTopics = canManageTopics,
    )

    suspend fun exportChatInviteLink(chatId: Long) = client.exportChatInviteLink(chatId.toString())

    suspend fun setChatPhoto(
        chatId: Long,
        photo: Any
    ) = client.setChatPhoto(chatId.toString(), photo)

    suspend fun deleteChatPhoto(chatId: Long) = client.deleteChatPhoto(chatId.toString())

    suspend fun setChatTitle(
        chatId: Long,
        title: String
    ) = client.setChatTitle(chatId.toString(), title)

    suspend fun setChatDescription(
        chatId: Long,
        description: String
    ) = client.setChatDescription(chatId.toString(), description)

    suspend fun pinChatMessage(
        chatId: Long,
        messageId: Long,
        disableNotification: Boolean?
    ) = client.pinChatMessage(chatId.toString(), messageId, disableNotification)

    suspend fun unpinChatMessage(
        chatId: Long,
        messageId: Long?
    ) = client.unpinChatMessage(chatId.toString(), messageId)

    suspend fun unpinAllChatMessages(chatId: Long): Boolean = client.unpinAllChatMessages(chatId.toString())

    suspend fun leaveChat(chatId: Long) = client.leaveChat(chatId.toString())

    suspend fun getChat(chatId: Long) = client.getChat(chatId.toString())

    suspend fun getChatAdministrators(chatId: Long) = client.getChatAdministrators(chatId.toString())

    suspend fun getChatMemberCount(chatId: Long) = client.getChatMemberCount(chatId.toString())

    suspend fun getChatMember(
        chatId: Long,
        userId: Long
    ) = client.getChatMember(chatId.toString(), userId)

    suspend fun setChatStickerSet(
        chatId: Long,
        stickerSetName: String
    ) = client.setChatStickerSet(chatId.toString(), stickerSetName)

    suspend fun deleteChatStickerSet(chatId: Long) = client.deleteChatStickerSet(chatId.toString())

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long?
    ) = client.answerCallbackQuery(
        callbackQueryId = callbackQueryId,
        text = text,
        showAlert = showAlert,
        url = url,
        cacheTime = cacheTime
    )

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        switchPmText: String? = null,
        switchPmParameter: String?
    ) = client.answerInlineQuery(
        inlineQueryId = inlineQueryId,
        results = results,
        cacheTime = cacheTime,
        isPersonal = isPersonal,
        nextOffset = nextOffset,
        switchPmText = switchPmText,
        switchPmParameter = switchPmParameter
    )

    suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult) =
        client.answerWebAppQuery(
            webAppQueryId = webAppQueryId,
            result = result
        )

    suspend fun editMessageText(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        disableWebPagePreview: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageText(
            chatId = chatId?.toString(),
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            text = text,
            parseMode = parseMode,
            entities = entities,
            disableWebPagePreview = disableWebPagePreview,
            replyMarkup = replyMarkup
        )
    }

    suspend fun editMessageCaption(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageCaption(
            chatId = chatId?.toString(),
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            replyMarkup = replyMarkup
        )
    }

    suspend fun editMessageMedia(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageMedia(chatId?.toString(), messageId, inlineMessageId, media, replyMarkup)
    }

    suspend fun editMessageReplyMarkup(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageReplyMarkup(chatId?.toString(), messageId, inlineMessageId, replyMarkup)
    }

    suspend fun sendSticker(
        chatId: Long,
        sticker: Any,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ): Message {
        return client.sendSticker(
            chatId = chatId.toString(),
            sticker = sticker,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }

    suspend fun getStickerSet(name: String) = client.getStickerSet(name)

    suspend fun getCustomEmojiStickers(customEmojiIds: List<String>) =
        client.getCustomEmojiStickers(customEmojiIds)

    suspend fun uploadStickerFile(
        userId: Long,
        pngSticker: File
    ) = client.uploadStickerFile(userId, pngSticker)

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        emojis: String,
        pngSticker: Any? = null,
        tgsSticker: File? = null,
        webmSticker: File? = null,
        stickerType: String? = null,
        maskPosition: MaskPosition?
    ): Boolean {
        return client.createNewStickerSet(
            userId = userId,
            name = name,
            title = title,
            emojis = emojis,
            pngSticker = pngSticker,
            tgsSticker = tgsSticker,
            webmSticker = webmSticker,
            stickerType = stickerType,
            maskPosition = maskPosition,
        )
    }

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        emojis: String,
        pngSticker: Any? = null,
        tgsSticker: File? = null,
        webmSticker: File? = null,
        maskPosition: MaskPosition?
    ): Boolean {
        return client.addStickerToSet(
            userId = userId,
            name = name,
            emojis = emojis,
            pngSticker = pngSticker,
            tgsSticker = tgsSticker,
            webmSticker = webmSticker,
            maskPosition = maskPosition,
        )
    }

    suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int
    ) = client.setStickerPositionInSet(sticker, position)

    suspend fun deleteStickerFromSet(sticker: String) = client.deleteStickerFromSet(sticker)

    suspend fun setStickerSetThumb(name: String, userId: Long, thumb: Any?): Boolean {
        return client.setStickerSetThumb(name, userId, thumb)
    }

    suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup?
    ) = client.sendGame(
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
        force: Boolean? = null,
        disableEditMessage: Boolean? = null,
        chatId: Long? = null,
        messageId: Long? = null, inlineMessageId: String?
    ): Message {
        return client.setGameScore(
            userId,
            score,
            force,
            disableEditMessage,
            chatId?.let { chatId },
            messageId,
            inlineMessageId
        )
    }

    suspend fun getGameHighScores(
        userId: Long,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String?
    ): List<GameHighScore> {
        return client.getGameHighScores(userId, chatId?.let { chatId }, messageId, inlineMessageId)
    }

    suspend fun sendInvoice(
        chatId: Long,
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
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message = client.sendInvoice(
        chatId = chatId.toString(),
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
        protectContent = protectContent,
        disableNotification = disableNotification,
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
        isFlexible: Boolean?
    ): String = client.createInvoiceLink(
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
        errorMessage: String?
    ) = client.answerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage)

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?
    ) = client.answerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage)

    suspend fun setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>
    ) = client.setPassportDataErrors(userId, errors)

    suspend fun sendPoll(
        chatId: Long,
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
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ): Message {
        return client.sendPoll(
            chatId = chatId.toString(),
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
    }

    suspend fun stopPoll(chatId: Long, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll =
        client.stopPoll(chatId.toString(), messageId, replyMarkup)

    suspend fun setChatPermissions(
        chatId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ) = client.setChatPermissions(
        chatId = chatId.toString(),
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
    )

    suspend fun createChatInviteLink(
        chatId: Long,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = client.createChatInviteLink(
        chatId.toString(),
        name,
        expireDate,
        memberLimit,
        createsJoinRequest,
    )

    suspend fun editChatInviteLink(
        chatId: Long,
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = client.editChatInviteLink(
        chatId.toString(),
        inviteLink,
        name,
        expireDate,
        memberLimit,
        createsJoinRequest
    )

    suspend fun revokeChatInviteLink(
        chatId: Long,
        inviteLink: String
    ): ChatInviteLink = client.revokeChatInviteLink(
        chatId.toString(),
        inviteLink
    )

    suspend fun approveChatJoinRequest(
        chatId: Long,
        inviteLink: String
    ): Boolean = client.approveChatJoinRequest(
        chatId.toString(),
        inviteLink
    )

    suspend fun declineChatJoinRequest(
        chatId: Long,
        inviteLink: String
    ): Boolean = client.declineChatJoinRequest(
        chatId.toString(),
        inviteLink
    )

    suspend fun setChatAdministratorCustomTitle(chatId: Long, userId: Long, customTitle: String) =
        client.setChatAdministratorCustomTitle(chatId.toString(), userId, customTitle)

    suspend fun deleteMessage(chatId: Long, messageId: Long): Boolean =
        client.deleteMessage(chatId.toString(), messageId)

    suspend fun sendDice(
        chatId: Long,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = client.sendDice(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )
    //endregion
}