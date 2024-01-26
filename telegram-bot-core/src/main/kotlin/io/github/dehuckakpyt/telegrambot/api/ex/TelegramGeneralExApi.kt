package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramGeneralApi
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.Content
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.NamedContent


/**
 * Created on 18.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramGeneralExApi : TelegramGeneralApi {

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendMessage(
        chatId = chatId.toString(),
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

    suspend fun forwardMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun forwardMessage(
        chatId: Long,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun forwardMessage(
        chatId: String,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun forwardMessages(
        chatId: Long,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun forwardMessages(
        chatId: Long,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun forwardMessages(
        chatId: String,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent
    )

    suspend fun copyMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): MessageId = copyMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
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

    suspend fun copyMessage(
        chatId: Long,
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
    ): MessageId = copyMessage(
        chatId = chatId.toString(),
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

    suspend fun copyMessage(
        chatId: String,
        fromChatId: Long,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): MessageId = copyMessage(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
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

    suspend fun copyMessages(
        chatId: Long,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    suspend fun copyMessages(
        chatId: Long,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    suspend fun copyMessages(
        chatId: String,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    suspend fun sendPhoto(
        chatId: Long,
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
    ): Message = sendPhoto(
        chatId = chatId.toString(),
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

    suspend fun sendPhoto(
        chatId: Long,
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
    ): Message = sendPhoto(
        chatId = chatId.toString(),
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

    suspend fun sendAudio(
        chatId: Long,
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
    ): Message = sendAudio(
        chatId = chatId.toString(),
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

    suspend fun sendAudio(
        chatId: Long,
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
    ): Message = sendAudio(
        chatId = chatId.toString(),
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

    suspend fun sendDocument(
        chatId: Long,
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
    ): Message = sendDocument(
        chatId = chatId.toString(),
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

    suspend fun sendDocument(
        chatId: Long,
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
    ): Message = sendDocument(
        chatId = chatId.toString(),
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

    suspend fun sendVideo(
        chatId: Long,
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
    ): Message = sendVideo(
        chatId = chatId.toString(),
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

    suspend fun sendVideo(
        chatId: Long,
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
    ): Message = sendVideo(
        chatId = chatId.toString(),
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

    suspend fun sendAnimation(
        chatId: Long,
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
    ): Message = sendAnimation(
        chatId = chatId.toString(),
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

    suspend fun sendAnimation(
        chatId: Long,
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
    ): Message = sendAnimation(
        chatId = chatId.toString(),
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

    suspend fun sendVoice(
        chatId: Long,
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
    ): Message = sendVoice(
        chatId = chatId.toString(),
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

    suspend fun sendVoice(
        chatId: Long,
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
    ): Message = sendVoice(
        chatId = chatId.toString(),
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

    suspend fun sendVideoNote(
        chatId: Long,
        videoNote: Content,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendVideoNote(
        chatId = chatId.toString(),
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

    suspend fun sendVideoNote(
        chatId: Long,
        videoNote: String,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumbnail: Content? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendVideoNote(
        chatId = chatId.toString(),
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

    suspend fun sendMediaGroup(
        chatId: Long,
        media: Iterable<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
    ): ArrayList<Message> = sendMediaGroup(
        chatId = chatId.toString(),
        media = media,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
    )

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
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendLocation(
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

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
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendVenue(
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
        replyParameters = replyParameters,
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
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendContact(
        chatId = chatId.toString(),
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
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendPoll(
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
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun sendDice(
        chatId: Long,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendDice(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    suspend fun sendChatAction(
        chatId: Long,
        action: Action,
        messageThreadId: Long? = null,
    ): Boolean = sendChatAction(
        chatId = chatId.toString(),
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean = banChatMember(
        chatId = chatId.toString(),
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages
    )

    suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean = unbanChatMember(
        chatId = chatId.toString(),
        userId = userId,
        onlyIfBanned = onlyIfBanned
    )

    suspend fun restrictChatMember(
        chatId: Long,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ): Boolean = restrictChatMember(
        chatId = chatId.toString(),
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate
    )

    suspend fun promoteChatMember(
        chatId: Long,
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
    ): Boolean = promoteChatMember(
        chatId = chatId.toString(),
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

    suspend fun setChatAdministratorCustomTitle(
        chatId: Long,
        userId: Long,
        customTitle: String,
    ): Boolean = setChatAdministratorCustomTitle(
        chatId = chatId.toString(),
        userId = userId,
        customTitle = customTitle
    )

    suspend fun banChatSenderChat(
        chatId: Long,
        senderChatId: Long,
    ): Boolean = banChatSenderChat(
        chatId = chatId.toString(),
        senderChatId = senderChatId
    )

    suspend fun unbanChatSenderChat(
        chatId: Long,
        senderChatId: Long,
    ): Boolean = unbanChatSenderChat(
        chatId = chatId.toString(),
        senderChatId = senderChatId
    )

    suspend fun setChatPermissions(
        chatId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean = setChatPermissions(
        chatId = chatId.toString(),
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions
    )

    suspend fun exportChatInviteLink(
        chatId: Long,
    ): String = exportChatInviteLink(
        chatId = chatId.toString()
    )

    suspend fun createChatInviteLink(
        chatId: Long,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = createChatInviteLink(
        chatId = chatId.toString(),
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    suspend fun editChatInviteLink(
        chatId: Long,
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = editChatInviteLink(
        chatId = chatId.toString(),
        inviteLink = inviteLink,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest
    )

    suspend fun revokeChatInviteLink(
        chatId: Long,
        inviteLink: String,
    ): ChatInviteLink = revokeChatInviteLink(
        chatId = chatId.toString(),
        inviteLink = inviteLink
    )

    suspend fun approveChatJoinRequest(
        chatId: Long,
        userId: Long,
    ): Boolean = approveChatJoinRequest(
        chatId = chatId.toString(),
        userId = userId
    )

    suspend fun declineChatJoinRequest(
        chatId: Long,
        userId: Long,
    ): Boolean = declineChatJoinRequest(
        chatId = chatId.toString(),
        userId = userId
    )

    suspend fun setChatPhoto(
        chatId: Long,
        photo: Content,
    ): Boolean = setChatPhoto(
        chatId = chatId.toString(),
        photo = photo
    )

    suspend fun setChatPhoto(
        chatId: Long,
        photo: String,
    ): Boolean = setChatPhoto(
        chatId = chatId.toString(),
        photo = photo
    )

    suspend fun deleteChatPhoto(
        chatId: Long,
    ): Boolean = deleteChatPhoto(
        chatId = chatId.toString()
    )

    suspend fun setChatTitle(
        chatId: Long,
        title: String,
    ): Boolean = setChatTitle(
        chatId = chatId.toString(),
        title = title
    )

    suspend fun setChatDescription(
        chatId: Long,
        description: String,
    ): Boolean = setChatDescription(
        chatId = chatId.toString(),
        description = description
    )

    suspend fun pinChatMessage(
        chatId: Long,
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean = pinChatMessage(
        chatId = chatId.toString(),
        messageId = messageId,
        disableNotification = disableNotification
    )

    suspend fun unpinChatMessage(
        chatId: Long,
        messageId: Long? = null,
    ): Boolean = unpinChatMessage(
        chatId = chatId.toString(),
        messageId = messageId
    )

    suspend fun unpinAllChatMessages(
        chatId: Long,
    ): Boolean = unpinAllChatMessages(
        chatId = chatId.toString()
    )

    suspend fun leaveChat(
        chatId: Long,
    ): Boolean = leaveChat(
        chatId = chatId.toString()
    )

    suspend fun getChat(
        chatId: Long,
    ): Chat = getChat(
        chatId = chatId.toString()
    )

    suspend fun getChatAdministrators(
        chatId: Long,
    ): ArrayList<ChatMember> = getChatAdministrators(
        chatId = chatId.toString()
    )

    suspend fun getChatMemberCount(
        chatId: Long,
    ): Long = getChatMemberCount(
        chatId = chatId.toString()
    )

    suspend fun getChatMember(
        chatId: Long,
        userId: Long,
    ): ChatMember = getChatMember(
        chatId = chatId.toString(),
        userId = userId
    )

    suspend fun setChatStickerSet(
        chatId: Long,
        stickerSetName: String,
    ): Boolean = setChatStickerSet(
        chatId = chatId.toString(),
        stickerSetName = stickerSetName
    )

    suspend fun deleteChatStickerSet(
        chatId: Long,
    ): Boolean = deleteChatStickerSet(
        chatId = chatId.toString()
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
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageLiveLocation(
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

    suspend fun editMessageLiveLocation(
        chatId: Long,
        messageId: Long,
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageLiveLocation(
        latitude = latitude,
        longitude = longitude,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageLiveLocation(
        chatId: String,
        messageId: Long,
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageLiveLocation(
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

    suspend fun editMessageLiveLocation(
        inlineMessageId: String,
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float? = null,
        heading: Long? = null,
        proximityAlertRadius: Long? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageLiveLocation(
        latitude = latitude,
        longitude = longitude,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    suspend fun stopMessageLiveLocation(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = stopMessageLiveLocation(
        chatId = chatId?.toString(),
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    suspend fun stopMessageLiveLocation(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = stopMessageLiveLocation(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun stopMessageLiveLocation(
        chatId: Long,
        messageId: Long,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = stopMessageLiveLocation(
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    suspend fun stopMessageLiveLocation(
        inlineMessageId: String,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = stopMessageLiveLocation(
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )
}