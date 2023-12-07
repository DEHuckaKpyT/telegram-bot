package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.io.File


/**
 * Created on 02.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TelegramApiHandling : KoinComponent {

    private val bot = get<TelegramBot>()

    //region Telegram methods

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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendMessage(
        text: String,
        messageThreadId: Long? = null,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        disableWebPagePreview: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.forwardMessage(
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.copyMessage(
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
        replyMarkup: ReplyKeyboard? = null,
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
        photo: NamedContent,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendPhoto(
        photo: NamedContent,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        audio: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendAudio(
        audio: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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
        document: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendDocument(
        document: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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
        video: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendVideo(
        video: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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
        animation: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendAnimation(
        animation: NamedContent,
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
        replyMarkup: ReplyKeyboard? = null,
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
        voice: NamedContent,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendVoice(
        voice: NamedContent,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        note: NamedContent,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendVideoNote(
        note: NamedContent,
        messageThreadId: Long? = null,
        duration: Long? = null,
        length: Long? = null,
        thumb: File? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        allowSendingWithoutReply: Boolean? = null,
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

    suspend fun Argument.sendMediaGroup(
        media: List<InputMedia>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendLocation(
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
        replyMarkup: ReplyKeyboard? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendVenue(
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendContact(
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

    suspend fun Argument.sendChatAction(
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
        forChannels: Boolean? = null,
    ) = bot.setMyDefaultAdministratorRights(rights = rights, forChannels = forChannels)

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ) = bot.getMyDefaultAdministratorRights(forChannels = forChannels)

    suspend fun getForumTopicIconStickers() = bot.getForumTopicIconStickers()

    suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ) = bot.createForumTopic(
        chatId = chatId,
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun Argument.createForumTopic(
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
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
        iconCustomEmojiId: String? = null,
    ) = bot.editForumTopic(
        chatId = chatId,
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    suspend fun Argument.editForumTopic(
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
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

    suspend fun Argument.closeForumTopic(
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

    suspend fun Argument.reopenForumTopic(
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

    suspend fun Argument.deleteForumTopic(
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

    suspend fun Argument.unpinAllForumTopicMessages(
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

    suspend fun Argument.editGeneralForumTopic(
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

    suspend fun Argument.closeGeneralForumTopic() = bot.closeGeneralForumTopic(chatId)

    suspend fun reopenGeneralForumTopic(
        chatId: Long,
    ) = bot.reopenGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun Argument.reopenGeneralForumTopic() = bot.reopenGeneralForumTopic(chatId)

    suspend fun hideGeneralForumTopic(
        chatId: Long,
    ) = bot.hideGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun Argument.hideGeneralForumTopic() = bot.hideGeneralForumTopic(chatId)

    suspend fun unhideGeneralForumTopic(
        chatId: Long,
    ) = bot.unhideGeneralForumTopic(
        chatId = chatId,
    )

    suspend fun Argument.unhideGeneralForumTopic() = bot.unhideGeneralForumTopic(chatId)

    suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Long? = null,
        limit: Long? = null,
    ) = bot.getUserProfilePhotos(
        userId = userId,
        offset = offset,
        limit = limit
    )

    suspend fun banChatSenderChat(
        chatId: Long,
        senderString: Long
    ): Boolean = bot.banChatSenderChat(chatId, senderString)

    suspend fun Argument.banChatSenderChat(
        senderString: Long
    ): Boolean = bot.banChatSenderChat(chatId, senderString)

    suspend fun unbanChatSenderChat(
        chatId: Long,
        senderString: Long
    ): Boolean = bot.unbanChatSenderChat(chatId, senderString)

    suspend fun Argument.unbanChatSenderChat(
        senderString: Long
    ): Boolean = bot.unbanChatSenderChat(chatId, senderString)

    suspend fun getFile(fileId: String) = bot.getFile(fileId)

    suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ) = bot.banChatMember(chatId, userId, untilDate, revokeMessages)

    suspend fun Argument.banChatMember(
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ) = bot.banChatMember(chatId, userId, untilDate, revokeMessages)

    suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ) = bot.unbanChatMember(chatId, userId, onlyIfBanned)

    suspend fun Argument.unbanChatMember(
        userId: Long,
        onlyIfBanned: Boolean? = null,
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

    suspend fun Argument.restrictChatMember(
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

    suspend fun Argument.promoteChatMember(
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

    suspend fun Argument.exportChatInviteLink() = bot.exportChatInviteLink(chatId)

    suspend fun setChatPhoto(
        chatId: Long,
        photo: Any
    ) = bot.setChatPhoto(chatId, photo)

    suspend fun Argument.setChatPhoto(
        photo: Any
    ) = bot.setChatPhoto(chatId, photo)

    suspend fun deleteChatPhoto(chatId: Long) = bot.deleteChatPhoto(chatId)

    suspend fun Argument.deleteChatPhoto() = bot.deleteChatPhoto(chatId)

    suspend fun setChatTitle(
        chatId: Long,
        title: String
    ) = bot.setChatTitle(chatId, title)

    suspend fun Argument.setChatTitle(
        title: String
    ) = bot.setChatTitle(chatId, title)

    suspend fun setChatDescription(
        chatId: Long,
        description: String
    ) = bot.setChatDescription(chatId, description)

    suspend fun Argument.setChatDescription(
        description: String
    ) = bot.setChatDescription(chatId, description)

    suspend fun pinChatMessage(
        chatId: Long,
        messageId: Long,
        disableNotification: Boolean? = null,
    ) = bot.pinChatMessage(chatId, messageId, disableNotification)

    suspend fun Argument.pinChatMessage(
        messageId: Long,
        disableNotification: Boolean? = null,
    ) = bot.pinChatMessage(chatId, messageId, disableNotification)

    suspend fun unpinChatMessage(
        chatId: Long,
        messageId: Long? = null,
    ) = bot.unpinChatMessage(chatId, messageId)

    suspend fun Argument.unpinChatMessage(
        messageId: Long? = null,
    ) = bot.unpinChatMessage(chatId, messageId)

    suspend fun unpinAllChatMessages(chatId: Long): Boolean = bot.unpinAllChatMessages(chatId)

    suspend fun Argument.unpinAllChatMessages(): Boolean = bot.unpinAllChatMessages(chatId)

    suspend fun leaveChat(chatId: Long) = bot.leaveChat(chatId)

    suspend fun Argument.leaveChat() = bot.leaveChat(chatId)

    suspend fun getChat(chatId: Long) = bot.getChat(chatId)

    suspend fun Argument.getChat() = bot.getChat(chatId)

    suspend fun getChatAdministrators(chatId: Long) = bot.getChatAdministrators(chatId)

    suspend fun Argument.getChatAdministrators() = bot.getChatAdministrators(chatId)

    suspend fun getChatMemberCount(chatId: Long) = bot.getChatMemberCount(chatId)

    suspend fun Argument.getChatMemberCount() = bot.getChatMemberCount(chatId)

    suspend fun getChatMember(
        chatId: Long,
        userId: Long
    ) = bot.getChatMember(chatId, userId)

    suspend fun Argument.getChatMember(
        userId: Long
    ) = bot.getChatMember(chatId, userId)

    suspend fun setChatStickerSet(
        chatId: Long,
        stickerSetName: String
    ) = bot.setChatStickerSet(chatId, stickerSetName)

    suspend fun Argument.setChatStickerSet(
        stickerSetName: String
    ) = bot.setChatStickerSet(chatId, stickerSetName)

    suspend fun deleteChatStickerSet(chatId: Long) = bot.deleteChatStickerSet(chatId)

    suspend fun Argument.deleteChatStickerSet() = bot.deleteChatStickerSet(chatId)

    suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String? = null,
        showAlert: Boolean? = null,
        url: String? = null,
        cacheTime: Long? = null,
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
        switchPmParameter: String? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message {
        return bot.editMessageMedia(chatId, messageId, inlineMessageId, media, replyMarkup)
    }

    suspend fun editMessageReplyMarkup(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
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
        replyMarkup: ReplyKeyboard? = null,
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

    suspend fun Argument.sendSticker(
        sticker: Any,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null,
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
        maskPosition: MaskPosition? = null,
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
        maskPosition: MaskPosition? = null,
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
        replyMarkup: InlineKeyboardMarkup? = null,
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

    suspend fun Argument.sendGame(
        gameShortName: String,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
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
        messageId: Long? = null, inlineMessageId: String? = null,
    ): Message {
        return bot.setGameScore(userId, score, force, disableEditMessage, chatId, messageId, inlineMessageId)
    }

    suspend fun getGameHighScores(
        userId: Long,
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
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
        protectContent = protectContent,
        disableNotification = disableNotification,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
    )

    suspend fun Argument.sendInvoice(
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
    ) = bot.answerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage)

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String? = null,
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
        replyMarkup: ReplyKeyboard? = null,
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
        replyMarkup: ReplyKeyboard? = null,
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