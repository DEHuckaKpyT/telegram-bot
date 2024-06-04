package io.github.dehuckakpyt.telegrambot.test.mock

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.api.client.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommand
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommandScope
import io.github.dehuckakpyt.telegrambot.model.telegram.BotDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.BotName
import io.github.dehuckakpyt.telegrambot.model.telegram.BotShortDescription
import io.github.dehuckakpyt.telegrambot.model.telegram.BusinessConnection
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatAdministratorRights
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatFullInfo
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatPermissions
import io.github.dehuckakpyt.telegrambot.model.telegram.File
import io.github.dehuckakpyt.telegrambot.model.telegram.ForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.GameHighScore
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResult
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineQueryResultsButton
import io.github.dehuckakpyt.telegrambot.model.telegram.InputMedia
import io.github.dehuckakpyt.telegrambot.model.telegram.InputPollOption
import io.github.dehuckakpyt.telegrambot.model.telegram.InputSticker
import io.github.dehuckakpyt.telegrambot.model.telegram.LabeledPrice
import io.github.dehuckakpyt.telegrambot.model.telegram.LinkPreviewOptions
import io.github.dehuckakpyt.telegrambot.model.telegram.MaskPosition
import io.github.dehuckakpyt.telegrambot.model.telegram.MenuButton
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.PassportElementError
import io.github.dehuckakpyt.telegrambot.model.telegram.Poll
import io.github.dehuckakpyt.telegrambot.model.telegram.ReactionType
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.telegram.SentWebAppMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.ShippingOption
import io.github.dehuckakpyt.telegrambot.model.telegram.Sticker
import io.github.dehuckakpyt.telegrambot.model.telegram.StickerSet
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.model.telegram.User
import io.github.dehuckakpyt.telegrambot.model.telegram.UserChatBoosts
import io.github.dehuckakpyt.telegrambot.model.telegram.UserProfilePhotos
import io.github.dehuckakpyt.telegrambot.model.telegram.WebhookInfo
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.mockk.mockk
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
internal class MockTelegramBot : TelegramBot {
    override val username: String = "mock_bot"

    override val client: TelegramApiClient = mockk()

    override suspend fun getUpdates(
        offset: Long?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: Iterable<String>?,
    ): List<Update> = mockk()

    override suspend fun setWebhook(
        url: String,
        certificate: ContentInput?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: Iterable<String>?,
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
        messageThreadId: Long?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
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
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

    override suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
    ): List<Message> = mockk()

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
    ): Message = mockk()

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
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = mockk()

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
    ): Message = mockk()

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
    ): Message = mockk()

    override suspend fun sendChatAction(
        chatId: String,
        action: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
    ): Boolean = mockk()

    override suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>?,
        isBig: Boolean?,
    ): Boolean = mockk()

    override suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Int?,
        limit: Int?,
    ): UserProfilePhotos = mockk()

    override suspend fun getFile(fileId: String): File = mockk()

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = mockk()

    override suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean?,
    ): Boolean = mockk()

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
        canPostStories: Boolean?,
        canEditStories: Boolean?,
        canDeleteStories: Boolean?,
        canPostMessages: Boolean?,
        canEditMessages: Boolean?,
        canPinMessages: Boolean?,
        canManageTopics: Boolean?,
    ): Boolean = mockk()

    override suspend fun setChatAdministratorCustomTitle(
        chatId: String,
        userId: Long,
        customTitle: String,
    ): Boolean = mockk()

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
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = mockk()

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = mockk()

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink =
            mockk()

    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean = mockk()

    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean = mockk()

    override suspend fun setChatPhoto(chatId: String, photo: Input): Boolean = mockk()

    override suspend fun deleteChatPhoto(chatId: String): Boolean = mockk()

    override suspend fun setChatTitle(chatId: String, title: String): Boolean = mockk()

    override suspend fun setChatDescription(chatId: String, description: String?): Boolean = mockk()

    override suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        disableNotification: Boolean?,
    ): Boolean = mockk()

    override suspend fun unpinChatMessage(chatId: String, messageId: Long?): Boolean = mockk()

    override suspend fun unpinAllChatMessages(chatId: String): Boolean = mockk()

    override suspend fun leaveChat(chatId: String): Boolean = mockk()

    override suspend fun getChat(chatId: String): ChatFullInfo = mockk()

    override suspend fun getChatAdministrators(chatId: String): List<ChatMember> = mockk()

    override suspend fun getChatMemberCount(chatId: String): Int = mockk()

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember = mockk()

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean =
            mockk()

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

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean
            = mockk()

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
        cacheTime: Int?,
    ): Boolean = mockk()

    override suspend fun getUserChatBoosts(chatId: String, userId: Long): UserChatBoosts = mockk()

    override suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection =
            mockk()

    override suspend fun setMyCommands(
        commands: Iterable<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = mockk()

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
            mockk()

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?):
            List<BotCommand> = mockk()

    override suspend fun setMyName(name: String?, languageCode: String?): Boolean = mockk()

    override suspend fun getMyName(languageCode: String?): BotName = mockk()

    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean =
            mockk()

    override suspend fun getMyDescription(languageCode: String?): BotDescription = mockk()

    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?):
            Boolean = mockk()

    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription = mockk()

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean =
            mockk()

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton = mockk()

    override suspend fun setMyDefaultAdministratorRights(rights: ChatAdministratorRights?,
            forChannels: Boolean?): Boolean = mockk()

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?):
            ChatAdministratorRights = mockk()

    override suspend fun editMessageText(
        chatId: String,
        messageId: Long,
        text: String,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageCaption(
        chatId: String,
        messageId: Long,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageMedia(
        chatId: String,
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageLiveLocation(
        chatId: String,
        messageId: Long,
        latitude: Double,
        longitude: Double,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun stopMessageLiveLocation(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun editMessageReplyMarkup(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = mockk()

    override suspend fun stopPoll(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup?,
    ): Poll = mockk()

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean = mockk()

    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean =
            mockk()

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
    ): Message = mockk()

    override suspend fun getStickerSet(name: String): StickerSet = mockk()

    override suspend fun getCustomEmojiStickers(customEmojiIds: Iterable<String>): List<Sticker> =
            mockk()

    override suspend fun uploadStickerFile(
        userId: Long,
        sticker: Input,
        stickerFormat: String,
    ): File = mockk()

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

    override suspend fun replaceStickerInSet(
        userId: Long,
        name: String,
        oldSticker: String,
        sticker: InputSticker,
    ): Boolean = mockk()

    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean
            = mockk()

    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean =
            mockk()

    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?):
            Boolean = mockk()

    override suspend fun setStickerSetTitle(name: String, title: String): Boolean = mockk()

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: Input?,
    ): Boolean = mockk()

    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?):
            Boolean = mockk()

    override suspend fun deleteStickerSet(name: String): Boolean = mockk()

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: Iterable<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
    ): Boolean = mockk()

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult):
            SentWebAppMessage = mockk()

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
    ): Message = mockk()

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
    ): String = mockk()

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: Iterable<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = mockk()

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = mockk()

    override suspend fun refundStarPayment(userId: Long, telegramPaymentChargeId: String): Boolean =
            mockk()

    override suspend fun setPassportDataErrors(userId: Long,
            errors: Iterable<PassportElementError>): Boolean = mockk()

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
    ): Message = mockk()

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        chatId: Long,
        messageId: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Message = mockk()

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = mockk()

    override suspend fun editMessageText(
        inlineMessageId: String,
        text: String,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = mockk()

    override suspend fun editMessageCaption(
        inlineMessageId: String,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = mockk()

    override suspend fun editMessageMedia(
        inlineMessageId: String,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = mockk()

    override suspend fun editMessageLiveLocation(
        inlineMessageId: String,
        latitude: Double,
        longitude: Double,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = mockk()

    override suspend fun stopMessageLiveLocation(inlineMessageId: String,
            replyMarkup: InlineKeyboardMarkup?): Boolean = mockk()

    override suspend fun editMessageReplyMarkup(inlineMessageId: String,
            replyMarkup: InlineKeyboardMarkup?): Boolean = mockk()

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        inlineMessageId: String,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Boolean = mockk()
}
