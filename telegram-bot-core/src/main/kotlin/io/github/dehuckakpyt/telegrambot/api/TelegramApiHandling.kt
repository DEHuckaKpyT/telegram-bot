package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.ext.container.chatId
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.model.telegram.WebhookInfo
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.NamedContentInput

/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class TelegramApiHandling {
    protected abstract val bot: TelegramBot

    //region Telegram methods
    suspend fun Container.getMe(): User = null!!

    suspend fun Container.logOut(): Boolean = null!!

    suspend fun Container.close(): Boolean = null!!

    suspend fun Container.sendMessage(
        text: String,
        parseMode: String? = null,
        entities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.forwardMessage(
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = null!!

    suspend fun Container.forwardMessages(
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = null!!

    suspend fun Container.copyMessage(
        fromChatId: String,
        messageId: Long,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): MessageId = null!!

    suspend fun Container.copyMessages(
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = null!!

    suspend fun Container.sendPhoto(
        photo: ContentInput,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        businessConnectionId: String? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendPhoto(
        photo: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendAudio(
        audio: ContentInput,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendAudio(
        audio: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendDocument(
        document: NamedContentInput,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendDocument(
        document: String,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVideo(
        video: ContentInput,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVideo(
        video: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendAnimation(
        animation: ContentInput,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendAnimation(
        animation: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVoice(
        voice: ContentInput,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVoice(
        voice: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVideoNote(
        videoNote: ContentInput,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendVideoNote(
        videoNote: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendMediaGroup(
        media: Iterable<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
    ): ArrayList<Message> = null!!

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
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

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
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

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
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendPoll(
        question: String,
        options: List<InputPollOption>,
        questionString: String? = null,
        questionEntities: List<MessageEntity>? = null,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        isAnonymous: Boolean? = null,
        type: String? = null,
        allowsMultipleAnswers: Boolean? = null,
        correctOptionId: Long? = null,
        explanation: String? = null,
        explanationString: String? = null,
        explanationEntities: List<MessageEntity>? = null,
        openPeriod: Long? = null,
        closeDate: Long? = null,
        isClosed: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendDice(
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.sendChatAction(
        action: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
    ): Boolean = null!!

    suspend fun Container.getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long? = null,
    ): UserProfilePhotos = null!!

    suspend fun Container.getFile(
        fileId: String,
    ): File = null!!

    suspend fun Container.banChatMember(
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.restrictChatMember(
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ): Boolean = null!!

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
    ): Boolean = null!!

    suspend fun Container.setChatAdministratorCustomTitle(
        userId: Long,
        customTitle: String,
    ): Boolean = null!!

    suspend fun Container.banChatSenderChat(
        senderChatId: Long,
    ): Boolean = null!!

    suspend fun Container.unbanChatSenderChat(
        senderChatId: Long,
    ): Boolean = null!!

    suspend fun Container.setChatPermissions(
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.exportChatInviteLink(): String = null!!

    suspend fun Container.createChatInviteLink(
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = null!!

    suspend fun Container.editChatInviteLink(
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = null!!

    suspend fun Container.revokeChatInviteLink(
        inviteLink: String,
    ): ChatInviteLink = null!!

    suspend fun Container.approveChatJoinRequest(
        userId: Long,
    ): Boolean = null!!

    suspend fun Container.declineChatJoinRequest(
        userId: Long,
    ): Boolean = null!!

    suspend fun Container.setChatPhoto(
        photo: ContentInput,
    ): Boolean = null!!

    suspend fun Container.setChatPhoto(
        photo: String,
    ): Boolean = null!!

    suspend fun Container.deleteChatPhoto(): Boolean = null!!

    suspend fun Container.setChatTitle(
        title: String,
    ): Boolean = null!!

    suspend fun Container.setChatDescription(
        description: String,
    ): Boolean = null!!

    suspend fun Container.pinChatMessage(
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.unpinChatMessage(
        messageId: Long? = null,
    ): Boolean = null!!

    suspend fun Container.unpinAllChatMessages(): Boolean = null!!

    suspend fun Container.leaveChat(): Boolean = null!!

    suspend fun Container.getChat(): ChatFullInfo = null!!

    suspend fun Container.getChatAdministrators(): ArrayList<ChatMember> = null!!

    suspend fun Container.getChatMemberCount(): Long = null!!

    suspend fun Container.getChatMember(
        userId: Long,
    ): ChatMember = null!!

    suspend fun Container.setChatStickerSet(
        stickerSetName: String,
    ): Boolean = null!!

    suspend fun Container.deleteChatStickerSet(): Boolean = null!!

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long? = null,
    ): Boolean = null!!

    suspend fun Container.getUserChatBoosts(
        userId: Long,
    ): UserChatBoosts = null!!

    suspend fun Container.getBusinessConnection(
        businessConnectionId: String,
    ): BusinessConnection = null!!

    suspend fun Container.setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = null!!

    suspend fun Container.deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean = null!!

    suspend fun Container.getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): List<BotCommand> = null!!

    suspend fun Container.setMyName(
        name: String? = null,
        languageCode: String? = null,
    ): Boolean = null!!

    suspend fun Container.getMyName(
        languageCode: String? = null,
    ): BotName = null!!

    suspend fun Container.setMyDescription(
        description: String? = null,
        languageCode: String? = null,
    ): Boolean = null!!

    suspend fun Container.getMyDescription(
        languageCode: String? = null,
    ): BotDescription = null!!

    suspend fun Container.setMyShortDescription(
        shortDescription: String? = null, languageCode: String? = null,
    ): Boolean = null!!

    suspend fun Container.getMyShortDescription(
        languageCode: String? = null,
    ): BotShortDescription = null!!

    suspend fun Container.editMessageLiveLocation(
        messageId: Long,
        latitude: Float,
        longitude: Float,
        livePeriod: Int? = null,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.stopMessageLiveLocation(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.setChatMenuButton(
        chatId: Long? = null,
        menuButton: MenuButton? = null,
    ): Boolean = null!!

    suspend fun Container.getChatMenuButton(
        chatId: Long? = null,
    ): MenuButton = null!!

    suspend fun Container.setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ): ChatAdministratorRights = null!!

    suspend fun Container.getForumTopicIconStickers(): List<Sticker> = null!!

    suspend fun Container.createForumTopic(
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic = null!!

    suspend fun Container.editForumTopic(
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean = null!!

    suspend fun Container.closeForumTopic(
        messageThreadId: Long,
    ): Boolean = null!!

    suspend fun Container.reopenForumTopic(
        messageThreadId: Long,
    ): Boolean = null!!

    suspend fun Container.deleteForumTopic(
        messageThreadId: Long,
    ): Boolean = null!!

    suspend fun Container.unpinAllForumTopicMessages(
        messageThreadId: Long,
    ): Boolean = null!!

    suspend fun Container.editGeneralForumTopic(
        name: String,
    ): Boolean = null!!

    suspend fun Container.closeGeneralForumTopic(): Boolean = null!!

    suspend fun Container.reopenGeneralForumTopic(): Boolean = null!!

    suspend fun Container.hideGeneralForumTopic(): Boolean = null!!

    suspend fun Container.unhideGeneralForumTopic(): Boolean = null!!

    suspend fun Container.unpinAllGeneralForumTopicMessages(): Boolean = null!!

    suspend fun Container.sendGame(
        gameShortName: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.setGameScore(
        userId: Long,
        score: Long,
        force: Boolean? = null,
        disableEditMessage: Boolean? = null,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
    ): Message = null!!

    suspend fun Container.getGameHighScores(
        userId: Long,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
    ): List<GameHighScore> = null!!

    suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int? = null,
        isPersonal: Boolean? = null,
        nextOffset: String? = null,
        button: InlineQueryResultsButton? = null,
    ): Boolean = null!!

    suspend fun answerWebAppQuery(
        webAppQueryId: String,
        result: InlineQueryResult,
    ): SentWebAppMessage = null!!

    suspend fun Container.setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>,
    ): Boolean = null!!

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
    ): Message = null!!

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
    ): String = null!!

    suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>? = null,
        errorMessage: String? = null,
    ): Boolean = null!!

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String? = null,
    ): Boolean = null!!

    suspend fun Container.sendSticker(
        sticker: ContentInput,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = null!!

    suspend fun Container.getStickerSet(
        name: String,
    ): StickerSet = null!!

    suspend fun Container.getCustomEmojiStickers(
        customEmojiIds: List<String>,
    ): List<Sticker> = null!!

    suspend fun Container.uploadStickerFile(
        userId: Long,
        sticker: ContentInput,
        stickerFormat: String,
    ): File = null!!

    suspend fun Container.createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerType: String? = null,
        needsRepainting: Boolean? = null,
    ): Boolean = null!!

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = null!!

    suspend fun Container.setStickerPositionInSet(
        sticker: String,
        position: Int,
    ): Boolean = null!!

    suspend fun Container.deleteStickerFromSet(
        sticker: String,
    ): Boolean = null!!

    suspend fun Container.replaceStickerInSet(
        userId: Long,
        name: String,
        oldSticker: String,
        sticker: InputSticker,
    ): Boolean = null!!

    suspend fun Container.setStickerEmojiList(
        sticker: String,
        emojiList: Iterable<String>,
    ): Boolean = null!!

    suspend fun Container.setStickerKeywords(
        sticker: String,
        keywords: Iterable<String>? = null,
    ): Boolean = null!!

    suspend fun Container.setStickerMaskPosition(
        sticker: String,
        maskPosition: MaskPosition? = null,
    ): Boolean = null!!

    suspend fun Container.setStickerSetTitle(
        sticker: String,
        title: String,
    ): Boolean = null!!

    suspend fun Container.setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: ContentInput? = null,
    ): Boolean = null!!

    suspend fun Container.setCustomEmojiStickerSetThumbnail(
        name: String,
        customEmojiId: String? = null,
    ): Boolean = null!!

    suspend fun Container.deleteStickerSet(
        name: String,
    ): Boolean = null!!

    suspend fun Container.getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: List<String>? = null,
    ): List<Update> = null!!

    suspend fun Container.setWebhook(
        url: String,
        certificate: NamedContentInput? = null,
        ipAddress: String? = null,
        maxConnections: Int? = null,
        allowedUpdates: List<String>? = null,
        dropPendingUpdates: Boolean? = null,
        secretToken: String? = null,
    ): Boolean = null!!

    suspend fun Container.deleteWebhook(
        dropPendingUpdates: Boolean? = null,
    ): Boolean = null!!

    suspend fun Container.getWebhookInfo(): WebhookInfo = null!!

    suspend fun Container.editMessageText(
        messageId: Long,
        text: String,
        parseMode: String? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.editMessageCaption(
        messageId: Long,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.editMessageMedia(
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.editMessageReplyMarkup(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = null!!

    suspend fun Container.stopPoll(
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll = null!!

    suspend fun Container.deleteMessage(
        messageId: Long,
    ): Boolean = null!!

    suspend fun Container.deleteMessages(
        messageIds: Iterable<Long>,
    ): Boolean = null!!
    //endregion Telegram methods
}