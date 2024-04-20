package io.github.dehuckakpyt.telegrambot.test.mock

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.Content
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.NamedContent
import io.ktor.client.statement.*
import io.mockk.mockk


/**
 * Created on 19.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class MockTelegramBot : TelegramBot {

    override val username: String = "mock_bot"

    override fun stop() {}

    override suspend fun getUpdates(
        offset: Int?, limit: Int?, timeout: Int?, allowedUpdates: Iterable<AllowedUpdate>?,
    ): List<Update> = mockk()

    override suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ): Boolean = mockk()

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean = mockk()

    override suspend fun getWebhookInfo(): WebhookInfo = mockk()

    override suspend fun getMe(): User = mockk()

    override suspend fun logOut(): Boolean = mockk()

    override suspend fun close(): Boolean = mockk()

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        linkPreviewOptions: LinkPreviewOptions?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): Message = mockk()

    override suspend fun forwardMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): List<MessageId> = mockk()

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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): MessageId = mockk()

    override suspend fun copyMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = mockk()

    override suspend fun sendPhoto(
        chatId: String,
        photo: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendPhoto(
        chatId: String,
        photo: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendAudio(
        chatId: String,
        audio: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendAudio(
        chatId: String,
        audio: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendDocument(
        chatId: String,
        document: NamedContent,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendDocument(
        chatId: String,
        document: String,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVideo(
        chatId: String,
        video: Content,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVideo(
        chatId: String,
        video: String,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendAnimation(
        chatId: String,
        animation: Content,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendAnimation(
        chatId: String,
        animation: String,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVoice(
        chatId: String,
        voice: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVoice(
        chatId: String,
        voice: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: Content,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
    ): ArrayList<Message> = mockk()

    override suspend fun sendLocation(
        chatId: String,
        latitude: Float,
        longitude: Float,
        businessConnectionId: String?,
        messageThreadId: Long?,
        horizontalAccuracy: Float?,
        livePeriod: Long?,
        heading: Long?,
        proximityAlertRadius: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendVenue(
        chatId: String,
        latitude: Float,
        longitude: Float,
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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendPoll(
        chatId: String,
        question: String,
        options: List<String>,
        businessConnectionId: String?,
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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendDice(
        chatId: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun sendChatAction(
        chatId: String,
        action: Action,
        businessConnectionId: String?,
        messageThreadId: Long?,
    ): Boolean = mockk()

    override suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>?,
        isBig: Boolean?,
    ): Boolean = mockk()

    override suspend fun getUserProfilePhotos(userId: Long, offset: Long?, limit: Long?): UserProfilePhotos = mockk()

    override suspend fun getFile(fileId: String): File = mockk()

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = mockk()

    override suspend fun unbanChatMember(chatId: String, userId: Long, onlyIfBanned: Boolean?): Boolean = mockk()

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ): Boolean = mockk()

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
    ): Boolean = mockk()

    override suspend fun setChatAdministratorCustomTitle(chatId: String, userId: Long, customTitle: String): Boolean = mockk()

    override suspend fun banChatSenderChat(chatId: String, senderChatId: Long): Boolean = mockk()

    override suspend fun unbanChatSenderChat(chatId: String, senderChatId: Long): Boolean = mockk()

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ): Boolean = mockk()

    override suspend fun exportChatInviteLink(chatId: String): String = mockk()

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = mockk()

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = mockk()

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink = mockk()

    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean = mockk()

    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean = mockk()

    override suspend fun setChatPhoto(chatId: String, photo: Content): Boolean = mockk()

    override suspend fun setChatPhoto(chatId: String, photo: String): Boolean = mockk()

    override suspend fun deleteChatPhoto(chatId: String): Boolean = mockk()

    override suspend fun setChatTitle(chatId: String, title: String): Boolean = mockk()

    override suspend fun setChatDescription(chatId: String, description: String): Boolean = mockk()

    override suspend fun pinChatMessage(chatId: String, messageId: Long, disableNotification: Boolean?): Boolean = mockk()

    override suspend fun unpinChatMessage(chatId: String, messageId: Long?): Boolean = mockk()

    override suspend fun unpinAllChatMessages(chatId: String): Boolean = mockk()

    override suspend fun leaveChat(chatId: String): Boolean = mockk()

    override suspend fun getChat(chatId: String): Chat = mockk()

    override suspend fun getChatAdministrators(chatId: String): ArrayList<ChatMember> = mockk()

    override suspend fun getChatMemberCount(chatId: String): Long = mockk()

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember = mockk()

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean = mockk()

    override suspend fun deleteChatStickerSet(chatId: String): Boolean = mockk()

    override suspend fun getForumTopicIconStickers(): List<Sticker> = mockk()

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = mockk()

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
    ): Boolean = mockk()

    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean = mockk()

    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean = mockk()

    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean = mockk()

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean = mockk()

    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean = mockk()

    override suspend fun closeGeneralForumTopic(chatId: String): Boolean = mockk()

    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean = mockk()

    override suspend fun hideGeneralForumTopic(chatId: String): Boolean = mockk()

    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean = mockk()

    override suspend fun unpinAllGeneralForumTopicMessages(chatId: String): Boolean = mockk()

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Long?,
    ): Boolean = mockk()

    override suspend fun getUserChatBoosts(
        chatId: String,
        userId: Long,
    ): UserChatBoosts = mockk()

    override suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection = mockk()

    override suspend fun setMyCommands(
        commands: List<BotCommand>, scope: BotCommandScope?, languageCode: String?,
    ): Boolean = mockk()

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean = mockk()

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?): List<BotCommand> = mockk()

    override suspend fun setMyName(name: String?, languageCode: String?): Boolean = mockk()

    override suspend fun getMyName(languageCode: String?): BotName = mockk()

    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean = mockk()

    override suspend fun getMyDescription(languageCode: String?): BotDescription = mockk()

    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?): Boolean = mockk()

    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription = mockk()

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean = mockk()

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton = mockk()

    override suspend fun setMyDefaultAdministratorRights(rights: ChatAdministratorRights?, forChannels: Boolean?): Boolean = mockk()

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?): ChatAdministratorRights = mockk()

    override suspend fun editMessageText(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageCaption(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageMedia(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

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
    ): Message = mockk()

    override suspend fun stopMessageLiveLocation(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageReplyMarkup(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun stopPoll(chatId: String, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll = mockk()

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean = mockk()

    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean = mockk()

    override suspend fun sendSticker(
        chatId: String,
        sticker: Content,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = mockk()

    override suspend fun getStickerSet(name: String): StickerSet = mockk()

    override suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker> = mockk()

    override suspend fun uploadStickerFile(userId: Long, sticker: Content, stickerFormat: String): File = mockk()

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerType: String?,
        needsRepainting: Boolean?,
    ): Boolean = mockk()

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = mockk()

    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean = mockk()

    override suspend fun deleteStickerFromSet(sticker: String): Boolean = mockk()
    override suspend fun replaceStickerInSet(userId: Long, name: String, oldSticker: String, sticker: InputSticker): Boolean = mockk()

    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean = mockk()

    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean = mockk()

    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?): Boolean = mockk()

    override suspend fun setStickerSetTitle(sticker: String, title: String): Boolean = mockk()

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: Content?,
    ): Boolean = mockk()

    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?): Boolean = mockk()

    override suspend fun deleteStickerSet(name: String): Boolean = mockk()

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
    ): Boolean = mockk()

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): SentWebAppMessage = mockk()

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
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

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
        isFlexible: Boolean?,
    ): String = mockk()

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = mockk()

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = mockk()

    override suspend fun setPassportDataErrors(userId: Long, errors: List<PassportElementError>): Boolean = mockk()

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): Message = mockk()

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = mockk()

    override suspend fun download(filePath: String): HttpResponse = mockk()
}