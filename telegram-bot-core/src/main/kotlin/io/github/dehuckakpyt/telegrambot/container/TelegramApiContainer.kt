package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.*
import com.elbekd.bot.util.Action
import com.elbekd.bot.util.AllowedUpdate
import com.elbekd.bot.util.SendingDocument
import io.github.dehuckakpyt.telegrambot.TelegramBot
import java.io.File


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class TelegramApiContainer(
    val chatId: Long,
    private val bot: TelegramBot
) {
    //region Telegram methods
    suspend fun getMe(): User = bot.getMe()

    suspend fun logOut(): Boolean = bot.logOut()

    suspend fun close(): Boolean = bot.close()

    suspend fun getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: List<AllowedUpdate>?
    ): List<Update> =
        bot.getUpdates(
            offset,
            limit,
            timeout,
            allowedUpdates
        )

    suspend fun getMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String?
    ): List<BotCommand> = bot.getMyCommands(scope, languageCode)

    suspend fun setMyCommands(
        commands: List<BotCommand>,
        scope: BotCommandScope? = null,
        languageCode: String?
    ): Boolean = bot.setMyCommands(commands, scope, languageCode)

    suspend fun deleteMyCommands(
        scope: BotCommandScope? = null,
        languageCode: String?
    ): Boolean = bot.deleteMyCommands(scope, languageCode)

    suspend fun setWebhook(
        url: String,
        certificate: File? = null,
        ipAddress: String? = null,
        maxConnections: Int? = null,
        allowedUpdates: List<AllowedUpdate>? = null,
        dropPendingUpdates: Boolean? = null,
        secretToken: String? = null,
    ) = bot.setWebhook(url, certificate, ipAddress, maxConnections, allowedUpdates, dropPendingUpdates, secretToken)

    suspend fun deleteWebhook(dropPendingUpdates: Boolean?) = bot.deleteWebhook(dropPendingUpdates)

    suspend fun getWebhookInfo() = bot.getWebhookInfo()

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        messageThreadId: Long? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        disableWebPagePreview: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null
    ): Message {
        return bot.sendMessage(
            chatId = chatId,
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

    suspend fun sendMessage(
        text: String,
        messageThreadId: Long? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        disableWebPagePreview: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null
    ): Message {
        return bot.sendMessage(
            chatId = chatId,
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
    ) = bot.forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    suspend fun forwardMessage(
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ) = bot.forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    suspend fun copyMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.copyMessage(
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

    suspend fun copyMessage(
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.copyMessage(
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

    suspend fun sendPhoto(
        chatId: Long,
        photo: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendPhoto(
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

    suspend fun sendPhoto(
        photo: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendPhoto(
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

    suspend fun sendAudio(
        chatId: Long,
        audio: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
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
    ) = bot.sendAudio(
        chatId = chatId,
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

    suspend fun sendAudio(
        audio: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
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
    ) = bot.sendAudio(
        chatId = chatId,
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
        document: SendingDocument,
        messageThreadId: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendDocument(
        chatId = chatId,
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

    suspend fun sendDocument(
        document: SendingDocument,
        messageThreadId: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendDocument(
        chatId = chatId,
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
        video: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        streaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVideo(
        chatId = chatId,
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

    suspend fun sendVideo(
        video: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        streaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVideo(
        chatId = chatId,
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
        animation: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendAnimation(
        chatId = chatId,
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

    suspend fun sendAnimation(
        animation: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        width: Long? = null,
        height: Long? = null,
        thumb: File? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendAnimation(
        chatId = chatId,
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
        voice: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVoice(
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

    suspend fun sendVoice(
        voice: SendingDocument,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVoice(
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

    suspend fun sendVideoNote(
        chatId: Long,
        note: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVideoNote(
        chatId = chatId,
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

    suspend fun sendVideoNote(
        note: SendingDocument,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendVideoNote(
        chatId = chatId,
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
        return bot.sendMediaGroup(
            chatId = chatId,
            media = media,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyToMessageId = replyToMessageId,
            allowSendingWithoutReply = allowSendingWithoutReply
        )
    }

    suspend fun sendMediaGroup(
        media: List<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean?
    ): ArrayList<Message> {
        return bot.sendMediaGroup(
            chatId = chatId,
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
    ) = bot.sendLocation(
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

    suspend fun sendLocation(
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
    ) = bot.sendLocation(
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
        return bot.editMessageLiveLocation(
            latitude = latitude,
            longitude = longitude,
            horizontalAccuracy = horizontalAccuracy,
            heading = heading,
            proximityAlertRadius = proximityAlertRadius,
            chatId = chatId,
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
        return bot.stopMessageLiveLocation(chatId, messageId, inlineMessageId, replyMarkup)
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
    ) = bot.sendVenue(
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

    suspend fun sendVenue(
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
    ) = bot.sendVenue(
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
    ) = bot.sendContact(
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

    suspend fun sendContact(
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
    ) = bot.sendContact(
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

    suspend fun sendChatAction(
        chatId: Long,
        action: Action,
        messageThreadId: Long? = null,
    ) = bot.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun sendChatAction(
        action: Action,
        messageThreadId: Long? = null,
    ) = bot.sendChatAction(
        chatId = chatId,
        action = action,
        messageThreadId = messageThreadId
    )

    suspend fun setChatMenuButton(chatId: Long? = null, menuButton: MenuButton?) =
        bot.setChatMenuButton(chatId = chatId, menuButton = menuButton)

    suspend fun getChatMenuButton(chatId: Long? = null) =
        bot.getChatMenuButton(chatId = chatId)

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean?
    ) = bot.setMyDefaultAdministratorRights(rights = rights, forChannels = forChannels)

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean?
    ) = bot.getMyDefaultAdministratorRights(forChannels = forChannels)

    suspend fun getForumTopicIconStickers() = bot.getForumTopicIconStickers()

    suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String?
    ) = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun createForumTopic(
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String?
    ) = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun editForumTopic(
        chatId: Long,
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String?
    ) = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun editForumTopic(
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String?
    ) = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun closeForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = bot.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun closeForumTopic(
        messageThreadId: Long
    ) = bot.closeForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun reopenForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = bot.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun reopenForumTopic(
        messageThreadId: Long
    ) = bot.reopenForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun deleteForumTopic(
        chatId: Long,
        messageThreadId: Long
    ) = bot.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun deleteForumTopic(
        messageThreadId: Long
    ) = bot.deleteForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun unpinAllForumTopicMessages(
        chatId: Long,
        messageThreadId: Long
    ) = bot.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun unpinAllForumTopicMessages(
        messageThreadId: Long
    ) = bot.unpinAllForumTopicMessages(
        chatId = chatId,
        messageThreadId = messageThreadId,
    )

    suspend fun editGeneralForumTopic(
        chatId: Long,
        name: String
    ) = bot.editGeneralForumTopic(
        chatId = chatId,
        name = name,
    )

    suspend fun editGeneralForumTopic(
        name: String
    ) = bot.editGeneralForumTopic(
        chatId = chatId,
        name = name,
    )

    suspend fun closeGeneralForumTopic(
        chatId: Long,
    ) = bot.closeGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun closeGeneralForumTopic() = bot.closeGeneralForumTopic(chatId)

    suspend fun reopenGeneralForumTopic(
        chatId: Long,
    ) = bot.reopenGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun reopenGeneralForumTopic() = bot.reopenGeneralForumTopic(chatId)

    suspend fun hideGeneralForumTopic(
        chatId: Long,
    ) = bot.hideGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun hideGeneralForumTopic() = bot.hideGeneralForumTopic(chatId)

    suspend fun unhideGeneralForumTopic(
        chatId: Long,
    ) = bot.unhideGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun unhideGeneralForumTopic() = bot.unhideGeneralForumTopic(chatId)

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long?
    ) = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun banChatSenderChat(
        chatId: Long,
        senderChatId: Long
    ): Boolean = bot.banChatSenderChat(chatId, senderChatId)

    suspend fun banChatSenderChat(
        senderChatId: Long
    ): Boolean = bot.banChatSenderChat(chatId, senderChatId)

    suspend fun unbanChatSenderChat(
        chatId: Long,
        senderChatId: Long
    ): Boolean = bot.unbanChatSenderChat(chatId, senderChatId)

    suspend fun unbanChatSenderChat(
        senderChatId: Long
    ): Boolean = bot.unbanChatSenderChat(chatId, senderChatId)

    suspend fun getFile(fileId: String) = bot.getFile(fileId)

    suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean?
    ) = bot.banChatMember(chatId, userId, untilDate, revokeMessages)

    suspend fun banChatMember(
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean?
    ) = bot.banChatMember(chatId, userId, untilDate, revokeMessages)

    suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean?
    ) = bot.unbanChatMember(chatId, userId, onlyIfBanned)

    suspend fun unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean?
    ) = bot.unbanChatMember(chatId, userId, onlyIfBanned)

    suspend fun restrictChatMember(
        chatId: Long,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ) = bot.restrictChatMember(
        chatId = chatId,
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate,
    )

    suspend fun restrictChatMember(
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ) = bot.restrictChatMember(
        chatId = chatId,
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
    ): Boolean = bot.promoteChatMember(
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

    suspend fun promoteChatMember(
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
    ): Boolean = bot.promoteChatMember(
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

    suspend fun exportChatInviteLink(chatId: Long) = bot.exportChatInviteLink(chatId)

    suspend fun exportChatInviteLink() = bot.exportChatInviteLink(chatId)

    suspend fun setChatPhoto(
        chatId: Long,
        photo: Any
    ) = bot.setChatPhoto(chatId, photo)

    suspend fun setChatPhoto(
        photo: Any
    ) = bot.setChatPhoto(chatId, photo)

    suspend fun deleteChatPhoto(chatId: Long) = bot.deleteChatPhoto(chatId)

    suspend fun deleteChatPhoto() = bot.deleteChatPhoto(chatId)

    suspend fun setChatTitle(
        chatId: Long,
        title: String
    ) = bot.setChatTitle(chatId, title)

    suspend fun setChatTitle(
        title: String
    ) = bot.setChatTitle(chatId, title)

    suspend fun setChatDescription(
        chatId: Long,
        description: String
    ) = bot.setChatDescription(chatId, description)

    suspend fun setChatDescription(
        description: String
    ) = bot.setChatDescription(chatId, description)

    suspend fun pinChatMessage(
        chatId: Long,
        messageId: Long,
        disableNotification: Boolean?
    ) = bot.pinChatMessage(chatId, messageId, disableNotification)

    suspend fun pinChatMessage(
        messageId: Long,
        disableNotification: Boolean?
    ) = bot.pinChatMessage(chatId, messageId, disableNotification)

    suspend fun unpinChatMessage(
        chatId: Long,
        messageId: Long?
    ) = bot.unpinChatMessage(chatId, messageId)

    suspend fun unpinChatMessage(
        messageId: Long?
    ) = bot.unpinChatMessage(chatId, messageId)

    suspend fun unpinAllChatMessages(chatId: Long): Boolean = bot.unpinAllChatMessages(chatId)

    suspend fun unpinAllChatMessages(): Boolean = bot.unpinAllChatMessages(chatId)

    suspend fun leaveChat(chatId: Long) = bot.leaveChat(chatId)

    suspend fun leaveChat() = bot.leaveChat(chatId)

    suspend fun getChat(chatId: Long) = bot.getChat(chatId)

    suspend fun getChat() = bot.getChat(chatId)

    suspend fun getChatAdministrators(chatId: Long) = bot.getChatAdministrators(chatId)

    suspend fun getChatAdministrators() = bot.getChatAdministrators(chatId)

    suspend fun getChatMemberCount(chatId: Long) = bot.getChatMemberCount(chatId)

    suspend fun getChatMemberCount() = bot.getChatMemberCount(chatId)

    suspend fun getChatMember(
        chatId: Long,
        userId: Long
    ) = bot.getChatMember(chatId, userId)

    suspend fun getChatMember(
        userId: Long
    ) = bot.getChatMember(chatId, userId)

    suspend fun setChatStickerSet(
        chatId: Long,
        stickerSetName: String
    ) = bot.setChatStickerSet(chatId, stickerSetName)

    suspend fun setChatStickerSet(
        stickerSetName: String
    ) = bot.setChatStickerSet(chatId, stickerSetName)

    suspend fun deleteChatStickerSet(chatId: Long) = bot.deleteChatStickerSet(chatId)

    suspend fun deleteChatStickerSet() = bot.deleteChatStickerSet(chatId)

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long?
    ) = bot.answerCallbackQuery(
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
    ) = bot.answerInlineQuery(
        inlineQueryId = inlineQueryId,
        results = results,
        cacheTime = cacheTime,
        isPersonal = isPersonal,
        nextOffset = nextOffset,
        switchPmText = switchPmText,
        switchPmParameter = switchPmParameter
    )

    suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult) =
        bot.answerWebAppQuery(
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
        return bot.editMessageText(
            chatId = chatId,
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
        return bot.editMessageCaption(
            chatId = chatId,
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
        return bot.editMessageMedia(chatId, messageId, inlineMessageId, media, replyMarkup)
    }

    suspend fun editMessageReplyMarkup(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        return bot.editMessageReplyMarkup(chatId, messageId, inlineMessageId, replyMarkup)
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
        return bot.sendSticker(
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

    suspend fun sendSticker(
        sticker: Any,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ): Message {
        return bot.sendSticker(
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

    suspend fun getStickerSet(name: String) = bot.getStickerSet(name)

    suspend fun getCustomEmojiStickers(customEmojiIds: List<String>) =
        bot.getCustomEmojiStickers(customEmojiIds)

    suspend fun uploadStickerFile(
        userId: Long,
        pngSticker: File
    ) = bot.uploadStickerFile(userId, pngSticker)

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        emojis: String,
        pngSticker: Any? = null,
        tgsSticker: File? = null,
        webmSticker: File? = null,
        stickerType: String? = null,
        containsMask: Boolean? = null,
        maskPosition: MaskPosition?
    ): Boolean {
        return bot.createNewStickerSet(
            userId = userId,
            name = name,
            title = title,
            emojis = emojis,
            pngSticker = pngSticker,
            tgsSticker = tgsSticker,
            webmSticker = webmSticker,
            stickerType = stickerType,
            containsMask = containsMask,
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
        return bot.addStickerToSet(
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
    ) = bot.setStickerPositionInSet(sticker, position)

    suspend fun deleteStickerFromSet(sticker: String) = bot.deleteStickerFromSet(sticker)

    suspend fun setStickerSetThumb(name: String, userId: Long, thumb: Any?): Boolean {
        return bot.setStickerSetThumb(name, userId, thumb)
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
    ) = bot.sendGame(
        chatId = chatId,
        gameShortName = gameShortName,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendGame(
        gameShortName: String,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup?
    ) = bot.sendGame(
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
        return bot.setGameScore(userId, score, force, disableEditMessage, chatId, messageId, inlineMessageId)
    }

    suspend fun getGameHighScores(
        userId: Long,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String?
    ): List<GameHighScore> {
        return bot.getGameHighScores(userId, chatId, messageId, inlineMessageId)
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
        protectContent = protectContent,
        disableNotification = disableNotification,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun sendInvoice(
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
        errorMessage: String?
    ) = bot.answerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage)

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?
    ) = bot.answerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage)

    suspend fun setPassportDataErrors(
        userId: Long,
        errors: List<PassportElementError>
    ) = bot.setPassportDataErrors(userId, errors)

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
        if (openPeriod != null && closeDate != null) {
            throw IllegalArgumentException("openPeriod and closeDate can't be used together")
        }

        return bot.sendPoll(
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

    suspend fun stopPoll(chatId: Long, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll =
        bot.stopPoll(chatId, messageId, replyMarkup)

    suspend fun setChatPermissions(
        chatId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ) = bot.setChatPermissions(
        chatId = chatId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
    )

    suspend fun createChatInviteLink(
        chatId: Long,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Long? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = bot.createChatInviteLink(
        chatId,
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
    ): ChatInviteLink = bot.editChatInviteLink(
        chatId,
        inviteLink,
        name,
        expireDate,
        memberLimit,
        createsJoinRequest
    )

    suspend fun revokeChatInviteLink(
        chatId: Long,
        inviteLink: String
    ): ChatInviteLink = bot.revokeChatInviteLink(
        chatId,
        inviteLink
    )

    suspend fun approveChatJoinRequest(
        chatId: Long,
        inviteLink: String
    ): Boolean = bot.approveChatJoinRequest(
        chatId,
        inviteLink
    )

    suspend fun declineChatJoinRequest(
        chatId: Long,
        inviteLink: String
    ): Boolean = bot.declineChatJoinRequest(
        chatId,
        inviteLink
    )

    suspend fun setChatAdministratorCustomTitle(chatId: Long, userId: Long, customTitle: String) =
        bot.setChatAdministratorCustomTitle(chatId, userId, customTitle)

    suspend fun deleteMessage(chatId: Long, messageId: Long): Boolean =
        bot.deleteMessage(chatId, messageId)

    suspend fun sendDice(
        chatId: Long,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard?
    ) = bot.sendDice(
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