package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramGeneralApi {

    suspend fun getMe(): User

    suspend fun logOut(): Boolean

    suspend fun close(): Boolean

    suspend fun sendMessage(
        chatId: String,
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
    ): Message

    suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message

    suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): MessageId

    suspend fun sendPhoto(
        chatId: String,
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
    ): Message

    suspend fun sendPhoto(
        chatId: String,
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
    ): Message

    suspend fun sendAudio(
        chatId: String,
        audio: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: NamedContent? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendAudio(
        chatId: String,
        audio: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: NamedContent? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendDocument(
        chatId: String,
        document: NamedContent,
        thumbnail: NamedContent? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendDocument(
        chatId: String,
        document: String,
        thumbnail: NamedContent? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVideo(
        chatId: String,
        video: NamedContent,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: NamedContent? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVideo(
        chatId: String,
        video: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: NamedContent? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendAnimation(
        chatId: String,
        animation: NamedContent,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: NamedContent? = null,
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
    ): Message

    suspend fun sendAnimation(
        chatId: String,
        animation: String,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumbnail: NamedContent? = null,
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
    ): Message

    suspend fun sendVoice(
        chatId: String,
        voice: NamedContent,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVoice(
        chatId: String,
        voice: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVideoNote(
        chatId: String,
        videoNote: NamedContent,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: NamedContent? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVideoNote(
        chatId: String,
        videoNote: String,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: NamedContent? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
    ): ArrayList<Message>

    suspend fun sendLocation(
        chatId: String,
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
        allowSendingWithoutReply: Boolean? = false,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendVenue(
        chatId: String,
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
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendContact(
        chatId: String,
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long? = null,
        lastName: String? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendPoll(
        chatId: String,
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
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendDice(
        chatId: String,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun sendChatAction(
        chatId: String,
        action: Action,
        messageThreadId: Long? = null,
    ): Boolean

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long? = null,
    ): UserProfilePhotos

    suspend fun getFile(fileId: String): File

    suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean

    suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean

    suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ): Boolean

    suspend fun promoteChatMember(
        chatId: String,
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
    ): Boolean

    suspend fun setChatAdministratorCustomTitle(
        chatId: String,
        userId: Long,
        customTitle: String,
    ): Boolean

    suspend fun banChatSenderChat(
        chatId: String,
        senderChatId: Long,
    ): Boolean

    suspend fun unbanChatSenderChat(
        chatId: String,
        senderChatId: Long,
    ): Boolean

    suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean

    suspend fun exportChatInviteLink(chatId: String): String

    suspend fun createChatInviteLink(
        chatId: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink

    suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink

    suspend fun revokeChatInviteLink(
        chatId: String,
        inviteLink: String,
    ): ChatInviteLink

    suspend fun approveChatJoinRequest(
        chatId: String,
        userId: Long,
    ): Boolean

    suspend fun declineChatJoinRequest(
        chatId: String,
        userId: Long,
    ): Boolean

    suspend fun setChatPhoto(
        chatId: String,
        photo: NamedContent,
    ): Boolean

    suspend fun setChatPhoto(
        chatId: String,
        photo: String,
    ): Boolean

    suspend fun deleteChatPhoto(chatId: String): Boolean

    suspend fun setChatTitle(
        chatId: String,
        title: String,
    ): Boolean

    suspend fun setChatDescription(
        chatId: String,
        description: String,
    ): Boolean

    suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean

    suspend fun unpinChatMessage(chatId: String, messageId: Long? = null): Boolean

    suspend fun unpinAllChatMessages(chatId: String): Boolean

    suspend fun leaveChat(chatId: String): Boolean

    suspend fun getChat(chatId: String): Chat

    suspend fun getChatAdministrators(chatId: String): ArrayList<ChatMember>

    suspend fun getChatMemberCount(chatId: String): Long

    suspend fun getChatMember(
        chatId: String,
        userId: Long,
    ): ChatMember

    suspend fun setChatStickerSet(
        chatId: String,
        stickerSetName: String,
    ): Boolean

    suspend fun deleteChatStickerSet(chatId: String): Boolean

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long? = null,
    ): Boolean

    suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean

    suspend fun deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): Boolean

    suspend fun getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String? = null,
    ): List<BotCommand>

    suspend fun setMyName(
        name: String? = null,
        languageCode: String? = null,
    ): Boolean

    suspend fun getMyName(
        languageCode: String? = null,
    ): BotName

    suspend fun setMyDescription(
        description: String? = null,
        languageCode: String? = null,
    ): Boolean

    suspend fun getMyDescription(
        languageCode: String? = null,
    ): BotDescription

    suspend fun setMyShortDescription(
        shortDescription: String? = null,
        languageCode: String? = null,
    ): Boolean

    suspend fun getMyShortDescription(
        languageCode: String? = null,
    ): BotShortDescription

    suspend fun editMessageLiveLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message

    suspend fun stopMessageLiveLocation(
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message
}