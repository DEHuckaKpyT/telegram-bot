package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.TelegramApi
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
) : TelegramApi {

    //region Telegram methods

    override suspend fun getUpdates(
        offset: Int?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: List<AllowedUpdate>?
    ): List<UpdateResponse> = client.getUpdates(offset, limit, timeout, allowedUpdates)

    override suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ) = client.setWebhook(url, certificate, ipAddress, maxConnections, allowedUpdates, dropPendingUpdates, secretToken)

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?) = client.deleteWebhook(dropPendingUpdates)

    override suspend fun getWebhookInfo() = client.getWebhookInfo()

    override suspend fun deleteMyCommands(
        scope: BotCommandScope?,
        languageCode: String?
    ): Boolean = client.deleteMyCommands(scope, languageCode)

    override suspend fun getMe(): User = client.getMe()

    override suspend fun logOut(): Boolean = client.logOut()

    override suspend fun close(): Boolean = client.close()

    override suspend fun getMyCommands(
        scope: BotCommandScope?,
        languageCode: String?
    ): List<BotCommand> = client.getMyCommands(scope, languageCode)

    override suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?
    ): Boolean = client.setMyCommands(commands, scope, languageCode)

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        return client.sendMessage(
            chatId = chatId,
            text = text,
            parseMode = parseMode,
            messageThreadId = messageThreadId,
            entities = entities,
            disableWebPagePreview = disableWebPagePreview,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ) = client.forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    override suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.copyMessage(
        chatId = chatId,
        fromChatId = fromChatId,
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

    override suspend fun sendPhoto(
        chatId: String,
        photo: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendPhoto(
        chatId = chatId,
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

    override suspend fun sendPhoto(
        chatId: String,
        photo: String,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendPhoto(
        chatId = chatId,
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

    override suspend fun sendAudio(
        chatId: String,
        audio: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: NamedContent?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendAudio(
        chatId = chatId,
        audio = audio,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
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
        thumbnail: File?,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendDocument(
        chatId = chatId,
        document = document,
        messageThreadId = messageThreadId,
        thumbnail = thumbnail,
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

    override suspend fun sendVideo(
        chatId: String,
        video: NamedContent,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: File?,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        streaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVideo(
        chatId = chatId,
        video = video,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
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

    override suspend fun sendAnimation(
        chatId: String,
        animation: NamedContent,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: File?,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendAnimation(
        chatId = chatId,
        animation = animation,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
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

    override suspend fun sendVoice(
        chatId: String,
        voice: NamedContent,
        caption: String?,
        parseMode: ParseMode?,
        messageThreadId: Long?,
        captionEntities: List<MessageEntity>?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVoice(
        chatId = chatId,
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

    override suspend fun sendVideoNote(
        chatId: String,
        note: NamedContent,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: File?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendVideoNote(
        chatId = chatId,
        videoNote = note,
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
        allowSendingWithoutReply: Boolean?
    ): ArrayList<Message> {
        return client.sendMediaGroup(
            chatId = chatId,
            media = media,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    }

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
        replyMarkup: ReplyKeyboard?
    ) = client.sendLocation(
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

    override suspend fun editMessageLiveLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float?,
        heading: Long?,
        proximityAlertRadius: Long?,
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
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

    override suspend fun stopMessageLiveLocation(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.stopMessageLiveLocation(chatId?.toString(), messageId, inlineMessageId, replyMarkup)
    }

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
        replyMarkup: ReplyKeyboard?
    ) = client.sendVenue(
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
        replyMarkup: ReplyKeyboard?
    ) = client.sendContact(
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

    override suspend fun sendChatAction(
        chatId: String,
        action: Action,
        messageThreadId: Long?,
    ) = client.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?) =
        client.setChatMenuButton(chatId = chatId, menuButton = menuButton)

    override suspend fun getChatMenuButton(chatId: Long?) =
        client.getChatMenuButton(chatId = chatId)

    override suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?
    ) = client.setMyDefaultAdministratorRights(rights = rights, forChannels = forChannels)

    override suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean?
    ) = client.getMyDefaultAdministratorRights(forChannels = forChannels)

    override suspend fun getForumTopicIconStickers() = client.getForumTopicIconStickers()

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?
    ) = client.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?
    ) = client.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    override suspend fun closeForumTopic(
        chatId: String,
        messageThreadId: Long
    ) = client.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    override suspend fun reopenForumTopic(
        chatId: String,
        messageThreadId: Long
    ) = client.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    override suspend fun deleteForumTopic(
        chatId: String,
        messageThreadId: Long
    ) = client.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    override suspend fun unpinAllForumTopicMessages(
        chatId: String,
        messageThreadId: Long
    ) = client.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    override suspend fun editGeneralForumTopic(
        chatId: String,
        name: String
    ) = client.editGeneralForumTopic(
        chatId = chatId,
        name = name,
    )

    override suspend fun closeGeneralForumTopic(
        chatId: String,
    ) = client.closeGeneralForumTopic(
        chatId = chatId,
    )

    override suspend fun reopenGeneralForumTopic(
        chatId: String,
    ) = client.reopenGeneralForumTopic(
        chatId = chatId,
    )

    override suspend fun hideGeneralForumTopic(
        chatId: String,
    ) = client.hideGeneralForumTopic(
        chatId = chatId,
    )

    override suspend fun unhideGeneralForumTopic(
        chatId: String,
    ) = client.unhideGeneralForumTopic(
        chatId = chatId,
    )

    override suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long?,
        limit: Long?
    ) = client.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    override suspend fun banChatSenderChat(
        chatId: String,
        senderString: Long
    ): Boolean = client.banChatSenderChat(chatId, senderString)

    override suspend fun unbanChatSenderChat(
        chatId: String,
        senderString: Long
    ): Boolean = client.unbanChatSenderChat(chatId, senderString)

    override suspend fun getFile(fileId: String) = client.getFile(fileId)

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?
    ) = client.banChatMember(
        chatId,
        userId,
        untilDate,
        revokeMessages
    )

    override suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean?
    ) = client.unbanChatMember(
        chatId,
        userId,
        onlyIfBanned
    )

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ) = client.restrictChatMember(
        chatId = chatId,
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate,
    )

    override suspend fun promoteChatMember(
        chatId: String,
        userId: Long,
        isAnonymous: Boolean?,
        canManageChat: Boolean?,
        canPostMessages: Boolean?,
        canEditMessages: Boolean?,
        canDeleteMessages: Boolean?,
        canManageVideoChats: Boolean?,
        canRestrictMembers: Boolean?,
        canPromoteMembers: Boolean?,
        canChangeInfo: Boolean?,
        canInviteUsers: Boolean?,
        canPinMessages: Boolean?,
        canManageTopics: Boolean?,
    ): Boolean = client.promoteChatMember(
        chatId = chatId,
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

    override suspend fun exportChatInviteLink(chatId: String) = client.exportChatInviteLink(chatId)

    override suspend fun setChatPhoto(
        chatId: String,
        photo: Any
    ) = client.setChatPhoto(chatId, photo)

    override suspend fun deleteChatPhoto(chatId: String) = client.deleteChatPhoto(chatId)

    override suspend fun setChatTitle(
        chatId: String,
        title: String
    ) = client.setChatTitle(chatId, title)

    override suspend fun setChatDescription(
        chatId: String,
        description: String
    ) = client.setChatDescription(chatId, description)

    override suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        disableNotification: Boolean?
    ) = client.pinChatMessage(chatId, messageId, disableNotification)

    override suspend fun unpinChatMessage(
        chatId: String,
        messageId: Long?
    ) = client.unpinChatMessage(chatId, messageId)

    override suspend fun unpinAllChatMessages(chatId: String): Boolean = client.unpinAllChatMessages(chatId)

    override suspend fun leaveChat(chatId: String) = client.leaveChat(chatId)

    override suspend fun getChat(chatId: String) = client.getChat(chatId)

    override suspend fun getChatAdministrators(chatId: String) = client.getChatAdministrators(chatId)

    override suspend fun getChatMemberCount(chatId: String) = client.getChatMemberCount(chatId)

    override suspend fun getChatMember(
        chatId: String,
        userId: Long
    ) = client.getChatMember(chatId, userId)

    override suspend fun setChatStickerSet(
        chatId: String,
        stickerSetName: String
    ) = client.setChatStickerSet(chatId, stickerSetName)

    override suspend fun deleteChatStickerSet(chatId: String) = client.deleteChatStickerSet(chatId)

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Long?
    ) = client.answerCallbackQuery(
        callbackQueryId = callbackQueryId,
        text = text,
        showAlert = showAlert,
        url = url,
        cacheTime = cacheTime
    )

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        switchPmText: String?,
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

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult) =
        client.answerWebAppQuery(
            webAppQueryId = webAppQueryId,
            result = result
        )

    override suspend fun editMessageText(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
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

    override suspend fun editMessageCaption(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
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

    override suspend fun editMessageMedia(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageMedia(chatId?.toString(), messageId, inlineMessageId, media, replyMarkup)
    }

    override suspend fun editMessageReplyMarkup(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return client.editMessageReplyMarkup(chatId?.toString(), messageId, inlineMessageId, replyMarkup)
    }

    override suspend fun sendSticker(
        chatId: String,
        sticker: Any,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        return client.sendSticker(
            chatId = chatId,
            sticker = sticker,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply,
            replyMarkup = replyMarkup
        )
    }

    override suspend fun getStickerSet(name: String) = client.getStickerSet(name)

    override suspend fun getCustomEmojiStickers(customEmojiIds: List<String>) =
        client.getCustomEmojiStickers(customEmojiIds)

    override suspend fun uploadStickerFile(
        userId: Long,
        pngSticker: File
    ) = client.uploadStickerFile(userId, pngSticker)

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        emojis: String,
        pngSticker: Any?,
        tgsSticker: File?,
        webmSticker: File?,
        stickerType: String?,
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

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        emojis: String,
        pngSticker: Any?,
        tgsSticker: File?,
        webmSticker: File?,
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

    override suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int
    ) = client.setStickerPositionInSet(sticker, position)

    override suspend fun deleteStickerFromSet(sticker: String) = client.deleteStickerFromSet(sticker)

    override suspend fun setStickerSetThumbnail(name: String, userId: Long, thumbnail: Any?): Boolean {
        return client.setStickerSetThumbnail(name, userId, thumbnail)
    }

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
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

    override suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?, inlineMessageId: String?
    ): Message {
        return client.setGameScore(
            userId = userId,
            score = score,
            force = force,
            disableEditMessage = disableEditMessage,
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId
        )
    }

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?
    ): List<GameHighScore> {
        return client.getGameHighScores(userId, chatId, messageId, inlineMessageId)
    }

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
    ): Message = client.sendInvoice(
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
        protectContent = protectContent,
        disableNotification = disableNotification,
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

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?
    ) = client.answerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage)

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?
    ) = client.answerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage)

    override suspend fun setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>
    ) = client.setPassportDataErrors(userId, errors)

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
        replyMarkup: ReplyKeyboard?
    ): Message {
        return client.sendPoll(
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
    }

    override suspend fun stopPoll(chatId: String, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll =
        client.stopPoll(chatId, messageId, replyMarkup)

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ) = client.setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
    )

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.createChatInviteLink(
        chatId,
        name,
        expireDate,
        memberLimit,
        createsJoinRequest,
    )

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.editChatInviteLink(
        chatId,
        inviteLink,
        name,
        expireDate,
        memberLimit,
        createsJoinRequest
    )

    override suspend fun revokeChatInviteLink(
        chatId: String,
        inviteLink: String
    ): ChatInviteLink = client.revokeChatInviteLink(
        chatId,
        inviteLink
    )

    override suspend fun approveChatJoinRequest(
        chatId: String,
        inviteLink: String
    ): Boolean = client.approveChatJoinRequest(
        chatId,
        inviteLink
    )

    override suspend fun declineChatJoinRequest(
        chatId: String,
        inviteLink: String
    ): Boolean = client.declineChatJoinRequest(
        chatId,
        inviteLink
    )

    override suspend fun setChatAdministratorCustomTitle(chatId: String, userId: Long, customTitle: String) =
        client.setChatAdministratorCustomTitle(chatId, userId, customTitle)

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean =
        client.deleteMessage(chatId, messageId)

    override suspend fun sendDice(
        chatId: String,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ) = client.sendDice(
        chatId = chatId,
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