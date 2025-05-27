package io.github.dehuckakpyt.telegrambot

import io.github.dehuckakpyt.telegrambot.api.client.TelegramApiClient
import io.github.dehuckakpyt.telegrambot.event.managing.TelegramBotEventManager
import io.github.dehuckakpyt.telegrambot.ext.appendContent
import io.github.dehuckakpyt.telegrambot.ext.appendContentIfNotNull
import io.github.dehuckakpyt.telegrambot.ext.appendIfNotNull
import io.github.dehuckakpyt.telegrambot.ext.toJson
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.internal.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Created on 15.02.2025.
 *
 * @author KScript
 */
public class TelegramBotImpl(
    token: String,
    username: String? = null,
    clientConfiguration: (HttpClientConfig<ApacheEngineConfig>.() -> Unit)? = null,
    private val eventManager: TelegramBotEventManager,
) : TelegramBot {
    override val client: TelegramApiClient = TelegramApiClient(token, clientConfiguration)
    override val username: String = username ?: runBlocking(Dispatchers.Default) { getMe() }.username!!

    override suspend fun getUpdates(
        offset: Long?,
        limit: Int?,
        timeout: Int?,
        allowedUpdates: Iterable<String>?,
    ): List<Update> = client.postJson<List<Update>>("getUpdates",
        GetUpdates(
            offset = offset,
            limit = limit,
            timeout = timeout,
            allowedUpdates = allowedUpdates
        )
    ).afterMethod("getUpdates") {
        put("offset", offset)
        put("limit", limit)
        put("timeout", timeout)
        put("allowedUpdates", allowedUpdates)
    }

    override suspend fun setWebhook(
        url: String,
        certificate: ContentInput?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: Iterable<String>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ): Boolean = client.postMultiPart<Boolean>("setWebhook") {
        append("url", url)
        appendContentIfNotNull("certificate", certificate)
        appendIfNotNull("ip_address", ipAddress)
        appendIfNotNull("max_connections", maxConnections)
        appendIfNotNull("allowed_updates", client.toJson(allowedUpdates))
        appendIfNotNull("drop_pending_updates", dropPendingUpdates)
        appendIfNotNull("secret_token", secretToken)
    }.afterMethod("setWebhook") {
        put("url", url)
        put("certificate", certificate)
        put("ipAddress", ipAddress)
        put("maxConnections", maxConnections)
        put("allowedUpdates", allowedUpdates)
        put("dropPendingUpdates", dropPendingUpdates)
        put("secretToken", secretToken)
    }

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean =
        client.postJson<Boolean>("deleteWebhook",
            DeleteWebhook(
                dropPendingUpdates = dropPendingUpdates
            )
        ).afterMethod("deleteWebhook") {
            put("dropPendingUpdates", dropPendingUpdates)
        }

    override suspend fun getWebhookInfo(): WebhookInfo = client.get<WebhookInfo>("getWebhookInfo")
        .afterMethod("getWebhookInfo")

    override suspend fun getMe(): User = client.get<User>("getMe")
        .afterMethod("getMe")

    override suspend fun logOut(): Boolean = client.get<Boolean>("logOut")
        .afterMethod("logOut")

    override suspend fun close(): Boolean = client.get<Boolean>("close")
        .afterMethod("close")

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendMessage",
        SendMessage(
            chatId = chatId,
            text = text,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId,
            parseMode = parseMode,
            entities = entities,
            linkPreviewOptions = linkPreviewOptions,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendMessage") {
        put("chatId", chatId)
        put("text", text)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("parseMode", parseMode)
        put("entities", entities)
        put("linkPreviewOptions", linkPreviewOptions)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        videoStartTimestamp: Int?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): Message = client.postJson<Message>("forwardMessage",
        ForwardMessage(
            chatId = chatId,
            fromChatId = fromChatId,
            messageId = messageId,
            messageThreadId = messageThreadId,
            videoStartTimestamp = videoStartTimestamp,
            disableNotification = disableNotification,
            protectContent = protectContent
        )
    ).afterMethod("forwardMessage") {
        put("chatId", chatId)
        put("fromChatId", fromChatId)
        put("messageId", messageId)
        put("messageThreadId", messageThreadId)
        put("videoStartTimestamp", videoStartTimestamp)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
    }

    override suspend fun forwardMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): List<MessageId> = client.postJson<List<MessageId>>("forwardMessages",
        ForwardMessages(
            chatId = chatId,
            fromChatId = fromChatId,
            messageIds = messageIds,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent
        )
    ).afterMethod("forwardMessages") {
        put("chatId", chatId)
        put("fromChatId", fromChatId)
        put("messageIds", messageIds)
        put("messageThreadId", messageThreadId)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
    }

    override suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        videoStartTimestamp: Int?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): MessageId = client.postJson<MessageId>("copyMessage",
        CopyMessage(
            chatId = chatId,
            fromChatId = fromChatId,
            messageId = messageId,
            messageThreadId = messageThreadId,
            videoStartTimestamp = videoStartTimestamp,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            showCaptionAboveMedia = showCaptionAboveMedia,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("copyMessage") {
        put("chatId", chatId)
        put("fromChatId", fromChatId)
        put("messageId", messageId)
        put("messageThreadId", messageThreadId)
        put("videoStartTimestamp", videoStartTimestamp)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun copyMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = client.postJson<List<MessageId>>("copyMessages",
        CopyMessages(
            chatId = chatId,
            fromChatId = fromChatId,
            messageIds = messageIds,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            removeCaption = removeCaption
        )
    ).afterMethod("copyMessages") {
        put("chatId", chatId)
        put("fromChatId", fromChatId)
        put("messageIds", messageIds)
        put("messageThreadId", messageThreadId)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("removeCaption", removeCaption)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendPhoto") {
        append("chat_id", chatId)
        appendContent("photo", photo)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendPhoto") {
        put("chatId", chatId)
        put("photo", photo)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("hasSpoiler", hasSpoiler)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

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
        thumbnail: Input?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendAudio") {
        append("chat_id", chatId)
        appendContent("audio", audio)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("duration", duration)
        appendIfNotNull("performer", performer)
        appendIfNotNull("title", title)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendAudio") {
        put("chatId", chatId)
        put("audio", audio)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("duration", duration)
        put("performer", performer)
        put("title", title)
        put("thumbnail", thumbnail)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendDocument(
        chatId: String,
        document: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        thumbnail: Input?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendDocument") {
        append("chat_id", chatId)
        appendContent("document", document)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendDocument") {
        put("chatId", chatId)
        put("document", document)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("thumbnail", thumbnail)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("disableContentTypeDetection", disableContentTypeDetection)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendVideo(
        chatId: String,
        video: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        width: Int?,
        height: Int?,
        thumbnail: Input?,
        cover: Input?,
        startTimestamp: Int?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVideo") {
        append("chat_id", chatId)
        appendContent("video", video)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendContentIfNotNull("cover", cover)
        appendIfNotNull("start_timestamp", startTimestamp)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("supports_streaming", supportsStreaming)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendVideo") {
        put("chatId", chatId)
        put("video", video)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("duration", duration)
        put("width", width)
        put("height", height)
        put("thumbnail", thumbnail)
        put("cover", cover)
        put("startTimestamp", startTimestamp)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("hasSpoiler", hasSpoiler)
        put("supportsStreaming", supportsStreaming)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendAnimation(
        chatId: String,
        animation: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        width: Int?,
        height: Int?,
        thumbnail: Input?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendAnimation") {
        append("chat_id", chatId)
        appendContent("animation", animation)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendAnimation") {
        put("chatId", chatId)
        put("animation", animation)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("duration", duration)
        put("width", width)
        put("height", height)
        put("thumbnail", thumbnail)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("hasSpoiler", hasSpoiler)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVoice") {
        append("chat_id", chatId)
        appendContent("voice", voice)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("duration", duration)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendVoice") {
        put("chatId", chatId)
        put("voice", voice)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("duration", duration)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        duration: Int?,
        length: Int?,
        thumbnail: Input?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendVideoNote") {
        append("chat_id", chatId)
        appendContent("video_note", videoNote)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("length", length)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendVideoNote") {
        put("chatId", chatId)
        put("videoNote", videoNote)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("duration", duration)
        put("length", length)
        put("thumbnail", thumbnail)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendPaidMedia(
        chatId: String,
        starCount: Int,
        media: Iterable<InputPaidMedia>,
        businessConnectionId: String?,
        payload: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendPaidMedia") {
        append("chat_id", chatId)
        append("star_count", starCount)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("payload", payload)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode)
        appendIfNotNull("caption_entities", client.toJson(captionEntities))
        appendIfNotNull("show_caption_above_media", showCaptionAboveMedia)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))

        media.forEach { media ->
            appendContent(media.media)
        }

        media.forEach { media ->
            appendContentIfNotNull(media.thumbnail)
        }

        media.forEach { media ->
            appendContentIfNotNull(media.cover)
        }
    }.afterMethod("sendPaidMedia") {
        put("chatId", chatId)
        put("starCount", starCount)
        put("media", media)
        put("businessConnectionId", businessConnectionId)
        put("payload", payload)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
    ): List<Message> = client.postMultiPart<List<Message>>("sendMediaGroup") {
        append("chat_id", chatId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))

        media.forEach { media ->
            appendContent(media.media)
        }

        media.forEach { media ->
            appendContentIfNotNull(media.thumbnail)
        }

        media.forEach { media ->
            appendContentIfNotNull(media.cover)
        }
    }.afterMethod("sendMediaGroup") {
        put("chatId", chatId)
        put("media", media)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendLocation",
        SendLocation(
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
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendLocation") {
        put("chatId", chatId)
        put("latitude", latitude)
        put("longitude", longitude)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("horizontalAccuracy", horizontalAccuracy)
        put("livePeriod", livePeriod)
        put("heading", heading)
        put("proximityAlertRadius", proximityAlertRadius)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendVenue",
        SendVenue(
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
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendVenue") {
        put("chatId", chatId)
        put("latitude", latitude)
        put("longitude", longitude)
        put("title", title)
        put("address", address)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("foursquareId", foursquareId)
        put("foursquareType", foursquareType)
        put("googlePlaceId", googlePlaceId)
        put("googlePlaceType", googlePlaceType)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendContact",
        SendContact(
            chatId = chatId,
            phoneNumber = phoneNumber,
            firstName = firstName,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId,
            lastName = lastName,
            vcard = vcard,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendContact") {
        put("chatId", chatId)
        put("phoneNumber", phoneNumber)
        put("firstName", firstName)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("lastName", lastName)
        put("vcard", vcard)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendPoll",
        SendPoll(
            chatId = chatId,
            question = question,
            options = options,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId,
            questionParseMode = questionParseMode,
            questionEntities = questionEntities,
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
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendPoll") {
        put("chatId", chatId)
        put("question", question)
        put("options", options)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("questionParseMode", questionParseMode)
        put("questionEntities", questionEntities)
        put("isAnonymous", isAnonymous)
        put("type", type)
        put("allowsMultipleAnswers", allowsMultipleAnswers)
        put("correctOptionId", correctOptionId)
        put("explanation", explanation)
        put("explanationParseMode", explanationParseMode)
        put("explanationEntities", explanationEntities)
        put("openPeriod", openPeriod)
        put("closeDate", closeDate)
        put("isClosed", isClosed)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendDice(
        chatId: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postJson<Message>("sendDice",
        SendDice(
            chatId = chatId,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId,
            emoji = emoji,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendDice") {
        put("chatId", chatId)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("emoji", emoji)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun sendChatAction(
        chatId: String,
        action: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
    ): Boolean = client.postJson<Boolean>("sendChatAction",
        SendChatAction(
            chatId = chatId,
            action = action,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId
        )
    ).afterMethod("sendChatAction") {
        put("chatId", chatId)
        put("action", action)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
    }

    override suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>?,
        isBig: Boolean?,
    ): Boolean = client.postJson<Boolean>("setMessageReaction",
        SetMessageReaction(
            chatId = chatId,
            messageId = messageId,
            reaction = reaction,
            isBig = isBig
        )
    ).afterMethod("setMessageReaction") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("reaction", reaction)
        put("isBig", isBig)
    }

    override suspend fun getUserProfilePhotos(
        userId: Long,
        offset: Int?,
        limit: Int?,
    ): UserProfilePhotos = client.postJson<UserProfilePhotos>("getUserProfilePhotos",
        GetUserProfilePhotos(
            userId = userId,
            offset = offset,
            limit = limit
        )
    ).afterMethod("getUserProfilePhotos") {
        put("userId", userId)
        put("offset", offset)
        put("limit", limit)
    }

    override suspend fun setUserEmojiStatus(
        userId: Long,
        emojiStatusCustomEmojiId: String?,
        emojiStatusExpirationDate: Long?,
    ): Boolean = client.postJson<Boolean>("setUserEmojiStatus",
        SetUserEmojiStatus(
            userId = userId,
            emojiStatusCustomEmojiId = emojiStatusCustomEmojiId,
            emojiStatusExpirationDate = emojiStatusExpirationDate
        )
    ).afterMethod("setUserEmojiStatus") {
        put("userId", userId)
        put("emojiStatusCustomEmojiId", emojiStatusCustomEmojiId)
        put("emojiStatusExpirationDate", emojiStatusExpirationDate)
    }

    override suspend fun getFile(fileId: String): File = client.postJson<File>("getFile",
        GetFile(
            fileId = fileId
        )
    ).afterMethod("getFile") {
        put("fileId", fileId)
    }

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = client.postJson<Boolean>("banChatMember",
        BanChatMember(
            chatId = chatId,
            userId = userId,
            untilDate = untilDate,
            revokeMessages = revokeMessages
        )
    ).afterMethod("banChatMember") {
        put("chatId", chatId)
        put("userId", userId)
        put("untilDate", untilDate)
        put("revokeMessages", revokeMessages)
    }

    override suspend fun unbanChatMember(
        chatId: String,
        userId: Long,
        onlyIfBanned: Boolean?,
    ): Boolean = client.postJson<Boolean>("unbanChatMember",
        UnbanChatMember(
            chatId = chatId,
            userId = userId,
            onlyIfBanned = onlyIfBanned
        )
    ).afterMethod("unbanChatMember") {
        put("chatId", chatId)
        put("userId", userId)
        put("onlyIfBanned", onlyIfBanned)
    }

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ): Boolean = client.postJson<Boolean>("restrictChatMember",
        RestrictChatMember(
            chatId = chatId,
            userId = userId,
            permissions = permissions,
            useIndependentChatPermissions = useIndependentChatPermissions,
            untilDate = untilDate
        )
    ).afterMethod("restrictChatMember") {
        put("chatId", chatId)
        put("userId", userId)
        put("permissions", permissions)
        put("useIndependentChatPermissions", useIndependentChatPermissions)
        put("untilDate", untilDate)
    }

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
    ): Boolean = client.postJson<Boolean>("promoteChatMember",
        PromoteChatMember(
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
            canPostStories = canPostStories,
            canEditStories = canEditStories,
            canDeleteStories = canDeleteStories,
            canPostMessages = canPostMessages,
            canEditMessages = canEditMessages,
            canPinMessages = canPinMessages,
            canManageTopics = canManageTopics
        )
    ).afterMethod("promoteChatMember") {
        put("chatId", chatId)
        put("userId", userId)
        put("isAnonymous", isAnonymous)
        put("canManageChat", canManageChat)
        put("canDeleteMessages", canDeleteMessages)
        put("canManageVideoChats", canManageVideoChats)
        put("canRestrictMembers", canRestrictMembers)
        put("canPromoteMembers", canPromoteMembers)
        put("canChangeInfo", canChangeInfo)
        put("canInviteUsers", canInviteUsers)
        put("canPostStories", canPostStories)
        put("canEditStories", canEditStories)
        put("canDeleteStories", canDeleteStories)
        put("canPostMessages", canPostMessages)
        put("canEditMessages", canEditMessages)
        put("canPinMessages", canPinMessages)
        put("canManageTopics", canManageTopics)
    }

    override suspend fun setChatAdministratorCustomTitle(
        chatId: String,
        userId: Long,
        customTitle: String,
    ): Boolean = client.postJson<Boolean>("setChatAdministratorCustomTitle",
        SetChatAdministratorCustomTitle(
            chatId = chatId,
            userId = userId,
            customTitle = customTitle
        )
    ).afterMethod("setChatAdministratorCustomTitle") {
        put("chatId", chatId)
        put("userId", userId)
        put("customTitle", customTitle)
    }

    override suspend fun banChatSenderChat(chatId: String, senderChatId: Long): Boolean =
        client.postJson<Boolean>("banChatSenderChat",
            BanChatSenderChat(
                chatId = chatId,
                senderChatId = senderChatId
            )
        ).afterMethod("banChatSenderChat") {
            put("chatId", chatId)
            put("senderChatId", senderChatId)
        }

    override suspend fun unbanChatSenderChat(chatId: String, senderChatId: Long): Boolean =
        client.postJson<Boolean>("unbanChatSenderChat",
            UnbanChatSenderChat(
                chatId = chatId,
                senderChatId = senderChatId
            )
        ).afterMethod("unbanChatSenderChat") {
            put("chatId", chatId)
            put("senderChatId", senderChatId)
        }

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ): Boolean = client.postJson<Boolean>("setChatPermissions",
        SetChatPermissions(
            chatId = chatId,
            permissions = permissions,
            useIndependentChatPermissions = useIndependentChatPermissions
        )
    ).afterMethod("setChatPermissions") {
        put("chatId", chatId)
        put("permissions", permissions)
        put("useIndependentChatPermissions", useIndependentChatPermissions)
    }

    override suspend fun exportChatInviteLink(chatId: String): String =
        client.postJson<String>("exportChatInviteLink",
            ExportChatInviteLink(
                chatId = chatId
            )
        ).afterMethod("exportChatInviteLink") {
            put("chatId", chatId)
        }

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.postJson<ChatInviteLink>("createChatInviteLink",
        CreateChatInviteLink(
            chatId = chatId,
            name = name,
            expireDate = expireDate,
            memberLimit = memberLimit,
            createsJoinRequest = createsJoinRequest
        )
    ).afterMethod("createChatInviteLink") {
        put("chatId", chatId)
        put("name", name)
        put("expireDate", expireDate)
        put("memberLimit", memberLimit)
        put("createsJoinRequest", createsJoinRequest)
    }

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Int?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = client.postJson<ChatInviteLink>("editChatInviteLink",
        EditChatInviteLink(
            chatId = chatId,
            inviteLink = inviteLink,
            name = name,
            expireDate = expireDate,
            memberLimit = memberLimit,
            createsJoinRequest = createsJoinRequest
        )
    ).afterMethod("editChatInviteLink") {
        put("chatId", chatId)
        put("inviteLink", inviteLink)
        put("name", name)
        put("expireDate", expireDate)
        put("memberLimit", memberLimit)
        put("createsJoinRequest", createsJoinRequest)
    }

    override suspend fun createChatSubscriptionInviteLink(
        chatId: String,
        subscriptionPeriod: Int,
        subscriptionPrice: Int,
        name: String?,
    ): ChatInviteLink = client.postJson<ChatInviteLink>("createChatSubscriptionInviteLink",
        CreateChatSubscriptionInviteLink(
            chatId = chatId,
            subscriptionPeriod = subscriptionPeriod,
            subscriptionPrice = subscriptionPrice,
            name = name
        )
    ).afterMethod("createChatSubscriptionInviteLink") {
        put("chatId", chatId)
        put("subscriptionPeriod", subscriptionPeriod)
        put("subscriptionPrice", subscriptionPrice)
        put("name", name)
    }

    override suspend fun editChatSubscriptionInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
    ): ChatInviteLink = client.postJson<ChatInviteLink>("editChatSubscriptionInviteLink",
        EditChatSubscriptionInviteLink(
            chatId = chatId,
            inviteLink = inviteLink,
            name = name
        )
    ).afterMethod("editChatSubscriptionInviteLink") {
        put("chatId", chatId)
        put("inviteLink", inviteLink)
        put("name", name)
    }

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink =
        client.postJson<ChatInviteLink>("revokeChatInviteLink",
            RevokeChatInviteLink(
                chatId = chatId,
                inviteLink = inviteLink
            )
        ).afterMethod("revokeChatInviteLink") {
            put("chatId", chatId)
            put("inviteLink", inviteLink)
        }

    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean =
        client.postJson<Boolean>("approveChatJoinRequest",
            ApproveChatJoinRequest(
                chatId = chatId,
                userId = userId
            )
        ).afterMethod("approveChatJoinRequest") {
            put("chatId", chatId)
            put("userId", userId)
        }

    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean =
        client.postJson<Boolean>("declineChatJoinRequest",
            DeclineChatJoinRequest(
                chatId = chatId,
                userId = userId
            )
        ).afterMethod("declineChatJoinRequest") {
            put("chatId", chatId)
            put("userId", userId)
        }

    override suspend fun setChatPhoto(chatId: String, photo: Input): Boolean =
        client.postMultiPart<Boolean>("setChatPhoto") {
            append("chat_id", chatId)
            appendContent("photo", photo)
        }.afterMethod("setChatPhoto") {
            put("chatId", chatId)
            put("photo", photo)
        }

    override suspend fun deleteChatPhoto(chatId: String): Boolean =
        client.postJson<Boolean>("deleteChatPhoto",
            DeleteChatPhoto(
                chatId = chatId
            )
        ).afterMethod("deleteChatPhoto") {
            put("chatId", chatId)
        }

    override suspend fun setChatTitle(chatId: String, title: String): Boolean =
        client.postJson<Boolean>("setChatTitle",
            SetChatTitle(
                chatId = chatId,
                title = title
            )
        ).afterMethod("setChatTitle") {
            put("chatId", chatId)
            put("title", title)
        }

    override suspend fun setChatDescription(chatId: String, description: String?): Boolean =
        client.postJson<Boolean>("setChatDescription",
            SetChatDescription(
                chatId = chatId,
                description = description
            )
        ).afterMethod("setChatDescription") {
            put("chatId", chatId)
            put("description", description)
        }

    override suspend fun pinChatMessage(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        disableNotification: Boolean?,
    ): Boolean = client.postJson<Boolean>("pinChatMessage",
        PinChatMessage(
            chatId = chatId,
            messageId = messageId,
            businessConnectionId = businessConnectionId,
            disableNotification = disableNotification
        )
    ).afterMethod("pinChatMessage") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("businessConnectionId", businessConnectionId)
        put("disableNotification", disableNotification)
    }

    override suspend fun unpinChatMessage(
        chatId: String,
        businessConnectionId: String?,
        messageId: Long?,
    ): Boolean = client.postJson<Boolean>("unpinChatMessage",
        UnpinChatMessage(
            chatId = chatId,
            businessConnectionId = businessConnectionId,
            messageId = messageId
        )
    ).afterMethod("unpinChatMessage") {
        put("chatId", chatId)
        put("businessConnectionId", businessConnectionId)
        put("messageId", messageId)
    }

    override suspend fun unpinAllChatMessages(chatId: String): Boolean =
        client.postJson<Boolean>("unpinAllChatMessages",
            UnpinAllChatMessages(
                chatId = chatId
            )
        ).afterMethod("unpinAllChatMessages") {
            put("chatId", chatId)
        }

    override suspend fun leaveChat(chatId: String): Boolean = client.postJson<Boolean>("leaveChat",
        LeaveChat(
            chatId = chatId
        )
    ).afterMethod("leaveChat") {
        put("chatId", chatId)
    }

    override suspend fun getChat(chatId: String): ChatFullInfo =
        client.postJson<ChatFullInfo>("getChat",
            GetChat(
                chatId = chatId
            )
        ).afterMethod("getChat") {
            put("chatId", chatId)
        }

    override suspend fun getChatAdministrators(chatId: String): List<ChatMember> =
        client.postJson<List<ChatMember>>("getChatAdministrators",
            GetChatAdministrators(
                chatId = chatId
            )
        ).afterMethod("getChatAdministrators") {
            put("chatId", chatId)
        }

    override suspend fun getChatMemberCount(chatId: String): Int =
        client.postJson<Int>("getChatMemberCount",
            GetChatMemberCount(
                chatId = chatId
            )
        ).afterMethod("getChatMemberCount") {
            put("chatId", chatId)
        }

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember =
        client.postJson<ChatMember>("getChatMember",
            GetChatMember(
                chatId = chatId,
                userId = userId
            )
        ).afterMethod("getChatMember") {
            put("chatId", chatId)
            put("userId", userId)
        }

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean =
        client.postJson<Boolean>("setChatStickerSet",
            SetChatStickerSet(
                chatId = chatId,
                stickerSetName = stickerSetName
            )
        ).afterMethod("setChatStickerSet") {
            put("chatId", chatId)
            put("stickerSetName", stickerSetName)
        }

    override suspend fun deleteChatStickerSet(chatId: String): Boolean =
        client.postJson<Boolean>("deleteChatStickerSet",
            DeleteChatStickerSet(
                chatId = chatId
            )
        ).afterMethod("deleteChatStickerSet") {
            put("chatId", chatId)
        }

    override suspend fun getForumTopicIconStickers(): List<Sticker> =
        client.get<List<Sticker>>("getForumTopicIconStickers")
            .afterMethod("getForumTopicIconStickers")

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = client.postJson<ForumTopic>("createForumTopic",
        CreateForumTopic(
            chatId = chatId,
            name = name,
            iconColor = iconColor,
            iconCustomEmojiId = iconCustomEmojiId
        )
    ).afterMethod("createForumTopic") {
        put("chatId", chatId)
        put("name", name)
        put("iconColor", iconColor)
        put("iconCustomEmojiId", iconCustomEmojiId)
    }

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
    ): Boolean = client.postJson<Boolean>("editForumTopic",
        EditForumTopic(
            chatId = chatId,
            messageThreadId = messageThreadId,
            name = name,
            iconCustomEmojiId = iconCustomEmojiId
        )
    ).afterMethod("editForumTopic") {
        put("chatId", chatId)
        put("messageThreadId", messageThreadId)
        put("name", name)
        put("iconCustomEmojiId", iconCustomEmojiId)
    }

    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean =
        client.postJson<Boolean>("closeForumTopic",
            CloseForumTopic(
                chatId = chatId,
                messageThreadId = messageThreadId
            )
        ).afterMethod("closeForumTopic") {
            put("chatId", chatId)
            put("messageThreadId", messageThreadId)
        }

    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean =
        client.postJson<Boolean>("reopenForumTopic",
            ReopenForumTopic(
                chatId = chatId,
                messageThreadId = messageThreadId
            )
        ).afterMethod("reopenForumTopic") {
            put("chatId", chatId)
            put("messageThreadId", messageThreadId)
        }

    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean =
        client.postJson<Boolean>("deleteForumTopic",
            DeleteForumTopic(
                chatId = chatId,
                messageThreadId = messageThreadId
            )
        ).afterMethod("deleteForumTopic") {
            put("chatId", chatId)
            put("messageThreadId", messageThreadId)
        }

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean = client.postJson<Boolean>("unpinAllForumTopicMessages",
        UnpinAllForumTopicMessages(
            chatId = chatId,
            messageThreadId = messageThreadId
        )
    ).afterMethod("unpinAllForumTopicMessages") {
        put("chatId", chatId)
        put("messageThreadId", messageThreadId)
    }

    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean =
        client.postJson<Boolean>("editGeneralForumTopic",
            EditGeneralForumTopic(
                chatId = chatId,
                name = name
            )
        ).afterMethod("editGeneralForumTopic") {
            put("chatId", chatId)
            put("name", name)
        }

    override suspend fun closeGeneralForumTopic(chatId: String): Boolean =
        client.postJson<Boolean>("closeGeneralForumTopic",
            CloseGeneralForumTopic(
                chatId = chatId
            )
        ).afterMethod("closeGeneralForumTopic") {
            put("chatId", chatId)
        }

    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean =
        client.postJson<Boolean>("reopenGeneralForumTopic",
            ReopenGeneralForumTopic(
                chatId = chatId
            )
        ).afterMethod("reopenGeneralForumTopic") {
            put("chatId", chatId)
        }

    override suspend fun hideGeneralForumTopic(chatId: String): Boolean =
        client.postJson<Boolean>("hideGeneralForumTopic",
            HideGeneralForumTopic(
                chatId = chatId
            )
        ).afterMethod("hideGeneralForumTopic") {
            put("chatId", chatId)
        }

    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean =
        client.postJson<Boolean>("unhideGeneralForumTopic",
            UnhideGeneralForumTopic(
                chatId = chatId
            )
        ).afterMethod("unhideGeneralForumTopic") {
            put("chatId", chatId)
        }

    override suspend fun unpinAllGeneralForumTopicMessages(chatId: String): Boolean =
        client.postJson<Boolean>("unpinAllGeneralForumTopicMessages",
            UnpinAllGeneralForumTopicMessages(
                chatId = chatId
            )
        ).afterMethod("unpinAllGeneralForumTopicMessages") {
            put("chatId", chatId)
        }

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Int?,
    ): Boolean = client.postJson<Boolean>("answerCallbackQuery",
        AnswerCallbackQuery(
            callbackQueryId = callbackQueryId,
            text = text,
            showAlert = showAlert,
            url = url,
            cacheTime = cacheTime
        )
    ).afterMethod("answerCallbackQuery") {
        put("callbackQueryId", callbackQueryId)
        put("text", text)
        put("showAlert", showAlert)
        put("url", url)
        put("cacheTime", cacheTime)
    }

    override suspend fun getUserChatBoosts(chatId: String, userId: Long): UserChatBoosts =
        client.postJson<UserChatBoosts>("getUserChatBoosts",
            GetUserChatBoosts(
                chatId = chatId,
                userId = userId
            )
        ).afterMethod("getUserChatBoosts") {
            put("chatId", chatId)
            put("userId", userId)
        }

    override suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection =
        client.postJson<BusinessConnection>("getBusinessConnection",
            GetBusinessConnection(
                businessConnectionId = businessConnectionId
            )
        ).afterMethod("getBusinessConnection") {
            put("businessConnectionId", businessConnectionId)
        }

    override suspend fun setMyCommands(
        commands: Iterable<BotCommand>,
        scope: BotCommandScope?,
        languageCode: String?,
    ): Boolean = client.postJson<Boolean>("setMyCommands",
        SetMyCommands(
            commands = commands,
            scope = scope,
            languageCode = languageCode
        )
    ).afterMethod("setMyCommands") {
        put("commands", commands)
        put("scope", scope)
        put("languageCode", languageCode)
    }

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
        client.postJson<Boolean>("deleteMyCommands",
            DeleteMyCommands(
                scope = scope,
                languageCode = languageCode
            )
        ).afterMethod("deleteMyCommands") {
            put("scope", scope)
            put("languageCode", languageCode)
        }

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?):
            List<BotCommand> = client.postJson<List<BotCommand>>("getMyCommands",
        GetMyCommands(
            scope = scope,
            languageCode = languageCode
        )
    ).afterMethod("getMyCommands") {
        put("scope", scope)
        put("languageCode", languageCode)
    }

    override suspend fun setMyName(name: String?, languageCode: String?): Boolean =
        client.postJson<Boolean>("setMyName",
            SetMyName(
                name = name,
                languageCode = languageCode
            )
        ).afterMethod("setMyName") {
            put("name", name)
            put("languageCode", languageCode)
        }

    override suspend fun getMyName(languageCode: String?): BotName =
        client.postJson<BotName>("getMyName",
            GetMyName(
                languageCode = languageCode
            )
        ).afterMethod("getMyName") {
            put("languageCode", languageCode)
        }

    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean =
        client.postJson<Boolean>("setMyDescription",
            SetMyDescription(
                description = description,
                languageCode = languageCode
            )
        ).afterMethod("setMyDescription") {
            put("description", description)
            put("languageCode", languageCode)
        }

    override suspend fun getMyDescription(languageCode: String?): BotDescription =
        client.postJson<BotDescription>("getMyDescription",
            GetMyDescription(
                languageCode = languageCode
            )
        ).afterMethod("getMyDescription") {
            put("languageCode", languageCode)
        }

    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?):
            Boolean = client.postJson<Boolean>("setMyShortDescription",
        SetMyShortDescription(
            shortDescription = shortDescription,
            languageCode = languageCode
        )
    ).afterMethod("setMyShortDescription") {
        put("shortDescription", shortDescription)
        put("languageCode", languageCode)
    }

    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription =
        client.postJson<BotShortDescription>("getMyShortDescription",
            GetMyShortDescription(
                languageCode = languageCode
            )
        ).afterMethod("getMyShortDescription") {
            put("languageCode", languageCode)
        }

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean =
        client.postJson<Boolean>("setChatMenuButton",
            SetChatMenuButton(
                chatId = chatId,
                menuButton = menuButton
            )
        ).afterMethod("setChatMenuButton") {
            put("chatId", chatId)
            put("menuButton", menuButton)
        }

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton =
        client.postJson<MenuButton>("getChatMenuButton",
            GetChatMenuButton(
                chatId = chatId
            )
        ).afterMethod("getChatMenuButton") {
            put("chatId", chatId)
        }

    override suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?,
    ): Boolean =
        client.postJson<Boolean>("setMyDefaultAdministratorRights",
            SetMyDefaultAdministratorRights(
                rights = rights,
                forChannels = forChannels
            )
        ).afterMethod("setMyDefaultAdministratorRights") {
            put("rights", rights)
            put("forChannels", forChannels)
        }

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?):
            ChatAdministratorRights =
        client.postJson<ChatAdministratorRights>("getMyDefaultAdministratorRights",
            GetMyDefaultAdministratorRights(
                forChannels = forChannels
            )
        ).afterMethod("getMyDefaultAdministratorRights") {
            put("forChannels", forChannels)
        }

    override suspend fun editMessageText(
        chatId: String,
        messageId: Long,
        text: String,
        businessConnectionId: String?,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageText",
        EditMessageTextByChatIdAndMessageId(
            chatId = chatId,
            messageId = messageId,
            text = text,
            businessConnectionId = businessConnectionId,
            parseMode = parseMode,
            entities = entities,
            linkPreviewOptions = linkPreviewOptions,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageText") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("text", text)
        put("businessConnectionId", businessConnectionId)
        put("parseMode", parseMode)
        put("entities", entities)
        put("linkPreviewOptions", linkPreviewOptions)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageCaption(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageCaption",
        EditMessageCaptionByChatIdAndMessageId(
            chatId = chatId,
            messageId = messageId,
            businessConnectionId = businessConnectionId,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            showCaptionAboveMedia = showCaptionAboveMedia,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageCaption") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("businessConnectionId", businessConnectionId)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageMedia(
        chatId: String,
        messageId: Long,
        media: InputMedia,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postMultiPart<Message>("editMessageMedia") {
        append("chat_id", chatId)
        append("message_id", messageId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))

        appendContent(media.media)

        appendContentIfNotNull(media.thumbnail)

        appendContentIfNotNull(media.cover)
    }.afterMethod("editMessageMedia") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("media", media)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageLiveLocation(
        chatId: String,
        messageId: Long,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String?,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageLiveLocation",
        EditMessageLiveLocationByChatIdAndMessageId(
            chatId = chatId,
            messageId = messageId,
            latitude = latitude,
            longitude = longitude,
            businessConnectionId = businessConnectionId,
            livePeriod = livePeriod,
            horizontalAccuracy = horizontalAccuracy,
            heading = heading,
            proximityAlertRadius = proximityAlertRadius,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageLiveLocation") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("latitude", latitude)
        put("longitude", longitude)
        put("businessConnectionId", businessConnectionId)
        put("livePeriod", livePeriod)
        put("horizontalAccuracy", horizontalAccuracy)
        put("heading", heading)
        put("proximityAlertRadius", proximityAlertRadius)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun stopMessageLiveLocation(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("stopMessageLiveLocation",
        StopMessageLiveLocationByChatIdAndMessageId(
            chatId = chatId,
            messageId = messageId,
            businessConnectionId = businessConnectionId,
            replyMarkup = replyMarkup
        )
    ).afterMethod("stopMessageLiveLocation") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageReplyMarkup(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("editMessageReplyMarkup",
        EditMessageReplyMarkupByChatIdAndMessageId(
            chatId = chatId,
            messageId = messageId,
            businessConnectionId = businessConnectionId,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageReplyMarkup") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun stopPoll(
        chatId: String,
        messageId: Long,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Poll = client.postJson<Poll>("stopPoll",
        StopPoll(
            chatId = chatId,
            messageId = messageId,
            businessConnectionId = businessConnectionId,
            replyMarkup = replyMarkup
        )
    ).afterMethod("stopPoll") {
        put("chatId", chatId)
        put("messageId", messageId)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean =
        client.postJson<Boolean>("deleteMessage",
            DeleteMessage(
                chatId = chatId,
                messageId = messageId
            )
        ).afterMethod("deleteMessage") {
            put("chatId", chatId)
            put("messageId", messageId)
        }

    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean =
        client.postJson<Boolean>("deleteMessages",
            DeleteMessages(
                chatId = chatId,
                messageIds = messageIds
            )
        ).afterMethod("deleteMessages") {
            put("chatId", chatId)
            put("messageIds", messageIds)
        }

    override suspend fun sendSticker(
        chatId: String,
        sticker: Input,
        businessConnectionId: String?,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyMarkup?,
    ): Message = client.postMultiPart<Message>("sendSticker") {
        append("chat_id", chatId)
        appendContent("sticker", sticker)
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("emoji", emoji)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("allow_paid_broadcast", allowPaidBroadcast)
        appendIfNotNull("message_effect_id", messageEffectId)
        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
    }.afterMethod("sendSticker") {
        put("chatId", chatId)
        put("sticker", sticker)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("emoji", emoji)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun getStickerSet(name: String): StickerSet =
        client.postJson<StickerSet>("getStickerSet",
            GetStickerSet(
                name = name
            )
        ).afterMethod("getStickerSet") {
            put("name", name)
        }

    override suspend fun getCustomEmojiStickers(customEmojiIds: Iterable<String>): List<Sticker> =
        client.postJson<List<Sticker>>("getCustomEmojiStickers",
            GetCustomEmojiStickers(
                customEmojiIds = customEmojiIds
            )
        ).afterMethod("getCustomEmojiStickers") {
            put("customEmojiIds", customEmojiIds)
        }

    override suspend fun uploadStickerFile(
        userId: Long,
        sticker: Input,
        stickerFormat: String,
    ): File = client.postMultiPart<File>("uploadStickerFile") {
        append("user_id", userId)
        appendContent("sticker", sticker)
        append("sticker_format", stickerFormat)
    }.afterMethod("uploadStickerFile") {
        put("userId", userId)
        put("sticker", sticker)
        put("stickerFormat", stickerFormat)
    }

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerType: String?,
        needsRepainting: Boolean?,
    ): Boolean = client.postMultiPart<Boolean>("createNewStickerSet") {
        append("user_id", userId)
        append("name", name)
        append("title", title)
        append("stickers", client.toJson(stickers))
        appendIfNotNull("sticker_type", stickerType)
        appendIfNotNull("needs_repainting", needsRepainting)

        stickers.forEach { stickers ->
            appendContent(stickers.sticker)
        }
    }.afterMethod("createNewStickerSet") {
        put("userId", userId)
        put("name", name)
        put("title", title)
        put("stickers", stickers)
        put("stickerType", stickerType)
        put("needsRepainting", needsRepainting)
    }

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = client.postMultiPart<Boolean>("addStickerToSet") {
        append("user_id", userId)
        append("name", name)
        append("sticker", client.toJson(sticker))

        appendContent(sticker.sticker)
    }.afterMethod("addStickerToSet") {
        put("userId", userId)
        put("name", name)
        put("sticker", sticker)
    }

    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean =
        client.postJson<Boolean>("setStickerPositionInSet",
            SetStickerPositionInSet(
                sticker = sticker,
                position = position
            )
        ).afterMethod("setStickerPositionInSet") {
            put("sticker", sticker)
            put("position", position)
        }

    override suspend fun deleteStickerFromSet(sticker: String): Boolean =
        client.postJson<Boolean>("deleteStickerFromSet",
            DeleteStickerFromSet(
                sticker = sticker
            )
        ).afterMethod("deleteStickerFromSet") {
            put("sticker", sticker)
        }

    override suspend fun replaceStickerInSet(
        userId: Long,
        name: String,
        oldSticker: String,
        sticker: InputSticker,
    ): Boolean = client.postMultiPart<Boolean>("replaceStickerInSet") {
        append("user_id", userId)
        append("name", name)
        append("old_sticker", oldSticker)
        append("sticker", client.toJson(sticker))

        appendContent(sticker.sticker)
    }.afterMethod("replaceStickerInSet") {
        put("userId", userId)
        put("name", name)
        put("oldSticker", oldSticker)
        put("sticker", sticker)
    }

    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean = client.postJson<Boolean>("setStickerEmojiList",
        SetStickerEmojiList(
            sticker = sticker,
            emojiList = emojiList
        )
    ).afterMethod("setStickerEmojiList") {
        put("sticker", sticker)
        put("emojiList", emojiList)
    }

    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean =
        client.postJson<Boolean>("setStickerKeywords",
            SetStickerKeywords(
                sticker = sticker,
                keywords = keywords
            )
        ).afterMethod("setStickerKeywords") {
            put("sticker", sticker)
            put("keywords", keywords)
        }

    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?):
            Boolean = client.postJson<Boolean>("setStickerMaskPosition",
        SetStickerMaskPosition(
            sticker = sticker,
            maskPosition = maskPosition
        )
    ).afterMethod("setStickerMaskPosition") {
        put("sticker", sticker)
        put("maskPosition", maskPosition)
    }

    override suspend fun setStickerSetTitle(name: String, title: String): Boolean =
        client.postJson<Boolean>("setStickerSetTitle",
            SetStickerSetTitle(
                name = name,
                title = title
            )
        ).afterMethod("setStickerSetTitle") {
            put("name", name)
            put("title", title)
        }

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: Input?,
    ): Boolean = client.postMultiPart<Boolean>("setStickerSetThumbnail") {
        append("name", name)
        append("user_id", userId)
        append("format", format)
        appendContentIfNotNull("thumbnail", thumbnail)
    }.afterMethod("setStickerSetThumbnail") {
        put("name", name)
        put("userId", userId)
        put("format", format)
        put("thumbnail", thumbnail)
    }

    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?):
            Boolean = client.postJson<Boolean>("setCustomEmojiStickerSetThumbnail",
        SetCustomEmojiStickerSetThumbnail(
            name = name,
            customEmojiId = customEmojiId
        )
    ).afterMethod("setCustomEmojiStickerSetThumbnail") {
        put("name", name)
        put("customEmojiId", customEmojiId)
    }

    override suspend fun deleteStickerSet(name: String): Boolean =
        client.postJson<Boolean>("deleteStickerSet",
            DeleteStickerSet(
                name = name
            )
        ).afterMethod("deleteStickerSet") {
            put("name", name)
        }

    override suspend fun getAvailableGifts(): Gifts = client.get<Gifts>("getAvailableGifts")
        .afterMethod("getAvailableGifts")

    override suspend fun sendGift(
        giftId: String,
        userId: Long?,
        chatId: String?,
        payForUpgrade: Boolean?,
        text: String?,
        textParseMode: String?,
        textEntities: Iterable<MessageEntity>?,
    ): Boolean = client.postJson<Boolean>("sendGift",
        SendGift(
            giftId = giftId,
            userId = userId,
            chatId = chatId,
            payForUpgrade = payForUpgrade,
            text = text,
            textParseMode = textParseMode,
            textEntities = textEntities
        )
    ).afterMethod("sendGift") {
        put("giftId", giftId)
        put("userId", userId)
        put("chatId", chatId)
        put("payForUpgrade", payForUpgrade)
        put("text", text)
        put("textParseMode", textParseMode)
        put("textEntities", textEntities)
    }

    override suspend fun verifyUser(userId: Long, customDescription: String?): Boolean =
        client.postJson<Boolean>("verifyUser",
            VerifyUser(
                userId = userId,
                customDescription = customDescription
            )
        ).afterMethod("verifyUser") {
            put("userId", userId)
            put("customDescription", customDescription)
        }

    override suspend fun verifyChat(chatId: String, customDescription: String?): Boolean =
        client.postJson<Boolean>("verifyChat",
            VerifyChat(
                chatId = chatId,
                customDescription = customDescription
            )
        ).afterMethod("verifyChat") {
            put("chatId", chatId)
            put("customDescription", customDescription)
        }

    override suspend fun removeUserVerification(userId: Long): Boolean =
        client.postJson<Boolean>("removeUserVerification",
            RemoveUserVerification(
                userId = userId
            )
        ).afterMethod("removeUserVerification") {
            put("userId", userId)
        }

    override suspend fun removeChatVerification(chatId: String): Boolean =
        client.postJson<Boolean>("removeChatVerification",
            RemoveChatVerification(
                chatId = chatId
            )
        ).afterMethod("removeChatVerification") {
            put("chatId", chatId)
        }

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: Iterable<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
    ): Boolean = client.postJson<Boolean>("answerInlineQuery",
        AnswerInlineQuery(
            inlineQueryId = inlineQueryId,
            results = results,
            cacheTime = cacheTime,
            isPersonal = isPersonal,
            nextOffset = nextOffset,
            button = button
        )
    ).afterMethod("answerInlineQuery") {
        put("inlineQueryId", inlineQueryId)
        put("results", results)
        put("cacheTime", cacheTime)
        put("isPersonal", isPersonal)
        put("nextOffset", nextOffset)
        put("button", button)
    }

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult):
            SentWebAppMessage = client.postJson<SentWebAppMessage>("answerWebAppQuery",
        AnswerWebAppQuery(
            webAppQueryId = webAppQueryId,
            result = result
        )
    ).afterMethod("answerWebAppQuery") {
        put("webAppQueryId", webAppQueryId)
        put("result", result)
    }

    override suspend fun savePreparedInlineMessage(
        userId: Long,
        result: InlineQueryResult,
        allowUserChats: Boolean?,
        allowBotChats: Boolean?,
        allowGroupChats: Boolean?,
        allowChannelChats: Boolean?,
    ): PreparedInlineMessage = client.postJson<PreparedInlineMessage>("savePreparedInlineMessage",
        SavePreparedInlineMessage(
            userId = userId,
            result = result,
            allowUserChats = allowUserChats,
            allowBotChats = allowBotChats,
            allowGroupChats = allowGroupChats,
            allowChannelChats = allowChannelChats
        )
    ).afterMethod("savePreparedInlineMessage") {
        put("userId", userId)
        put("result", result)
        put("allowUserChats", allowUserChats)
        put("allowBotChats", allowBotChats)
        put("allowGroupChats", allowGroupChats)
        put("allowChannelChats", allowChannelChats)
    }

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
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("sendInvoice",
        SendInvoice(
            chatId = chatId,
            title = title,
            description = description,
            payload = payload,
            currency = currency,
            prices = prices,
            messageThreadId = messageThreadId,
            providerToken = providerToken,
            maxTipAmount = maxTipAmount,
            suggestedTipAmounts = suggestedTipAmounts,
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
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendInvoice") {
        put("chatId", chatId)
        put("title", title)
        put("description", description)
        put("payload", payload)
        put("currency", currency)
        put("prices", prices)
        put("messageThreadId", messageThreadId)
        put("providerToken", providerToken)
        put("maxTipAmount", maxTipAmount)
        put("suggestedTipAmounts", suggestedTipAmounts)
        put("startParameter", startParameter)
        put("providerData", providerData)
        put("photoUrl", photoUrl)
        put("photoSize", photoSize)
        put("photoWidth", photoWidth)
        put("photoHeight", photoHeight)
        put("needName", needName)
        put("needPhoneNumber", needPhoneNumber)
        put("needEmail", needEmail)
        put("needShippingAddress", needShippingAddress)
        put("sendPhoneNumberToProvider", sendPhoneNumberToProvider)
        put("sendEmailToProvider", sendEmailToProvider)
        put("isFlexible", isFlexible)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun createInvoiceLink(
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: Iterable<LabeledPrice>,
        businessConnectionId: String?,
        providerToken: String?,
        subscriptionPeriod: Int?,
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
    ): String = client.postJson<String>("createInvoiceLink",
        CreateInvoiceLink(
            title = title,
            description = description,
            payload = payload,
            currency = currency,
            prices = prices,
            businessConnectionId = businessConnectionId,
            providerToken = providerToken,
            subscriptionPeriod = subscriptionPeriod,
            maxTipAmount = maxTipAmount,
            suggestedTipAmounts = suggestedTipAmounts,
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
    ).afterMethod("createInvoiceLink") {
        put("title", title)
        put("description", description)
        put("payload", payload)
        put("currency", currency)
        put("prices", prices)
        put("businessConnectionId", businessConnectionId)
        put("providerToken", providerToken)
        put("subscriptionPeriod", subscriptionPeriod)
        put("maxTipAmount", maxTipAmount)
        put("suggestedTipAmounts", suggestedTipAmounts)
        put("providerData", providerData)
        put("photoUrl", photoUrl)
        put("photoSize", photoSize)
        put("photoWidth", photoWidth)
        put("photoHeight", photoHeight)
        put("needName", needName)
        put("needPhoneNumber", needPhoneNumber)
        put("needEmail", needEmail)
        put("needShippingAddress", needShippingAddress)
        put("sendPhoneNumberToProvider", sendPhoneNumberToProvider)
        put("sendEmailToProvider", sendEmailToProvider)
        put("isFlexible", isFlexible)
    }

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: Iterable<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = client.postJson<Boolean>("answerShippingQuery",
        AnswerShippingQuery(
            shippingQueryId = shippingQueryId,
            ok = ok,
            shippingOptions = shippingOptions,
            errorMessage = errorMessage
        )
    ).afterMethod("answerShippingQuery") {
        put("shippingQueryId", shippingQueryId)
        put("ok", ok)
        put("shippingOptions", shippingOptions)
        put("errorMessage", errorMessage)
    }

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = client.postJson<Boolean>("answerPreCheckoutQuery",
        AnswerPreCheckoutQuery(
            preCheckoutQueryId = preCheckoutQueryId,
            ok = ok,
            errorMessage = errorMessage
        )
    ).afterMethod("answerPreCheckoutQuery") {
        put("preCheckoutQueryId", preCheckoutQueryId)
        put("ok", ok)
        put("errorMessage", errorMessage)
    }

    override suspend fun getStarTransactions(offset: Int?, limit: Int?): StarTransactions =
        client.postJson<StarTransactions>("getStarTransactions",
            GetStarTransactions(
                offset = offset,
                limit = limit
            )
        ).afterMethod("getStarTransactions") {
            put("offset", offset)
            put("limit", limit)
        }

    override suspend fun refundStarPayment(userId: Long, telegramPaymentChargeId: String): Boolean =
        client.postJson<Boolean>("refundStarPayment",
            RefundStarPayment(
                userId = userId,
                telegramPaymentChargeId = telegramPaymentChargeId
            )
        ).afterMethod("refundStarPayment") {
            put("userId", userId)
            put("telegramPaymentChargeId", telegramPaymentChargeId)
        }

    override suspend fun editUserStarSubscription(
        userId: Long,
        telegramPaymentChargeId: String,
        isCanceled: Boolean,
    ): Boolean = client.postJson<Boolean>("editUserStarSubscription",
        EditUserStarSubscription(
            userId = userId,
            telegramPaymentChargeId = telegramPaymentChargeId,
            isCanceled = isCanceled
        )
    ).afterMethod("editUserStarSubscription") {
        put("userId", userId)
        put("telegramPaymentChargeId", telegramPaymentChargeId)
        put("isCanceled", isCanceled)
    }

    override suspend fun setPassportDataErrors(
        userId: Long,
        errors: Iterable<PassportElementError>,
    ): Boolean =
        client.postJson<Boolean>("setPassportDataErrors",
            SetPassportDataErrors(
                userId = userId,
                errors = errors
            )
        ).afterMethod("setPassportDataErrors") {
            put("userId", userId)
            put("errors", errors)
        }

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        businessConnectionId: String?,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        allowPaidBroadcast: Boolean?,
        messageEffectId: String?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = client.postJson<Message>("sendGame",
        SendGame(
            chatId = chatId,
            gameShortName = gameShortName,
            businessConnectionId = businessConnectionId,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            allowPaidBroadcast = allowPaidBroadcast,
            messageEffectId = messageEffectId,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).afterMethod("sendGame") {
        put("chatId", chatId)
        put("gameShortName", gameShortName)
        put("businessConnectionId", businessConnectionId)
        put("messageThreadId", messageThreadId)
        put("disableNotification", disableNotification)
        put("protectContent", protectContent)
        put("allowPaidBroadcast", allowPaidBroadcast)
        put("messageEffectId", messageEffectId)
        put("replyParameters", replyParameters)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        chatId: Long,
        messageId: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Message = client.postJson<Message>("setGameScore",
        SetGameScoreByChatIdAndMessageId(
            userId = userId,
            score = score,
            chatId = chatId,
            messageId = messageId,
            force = force,
            disableEditMessage = disableEditMessage
        )
    ).afterMethod("setGameScore") {
        put("userId", userId)
        put("score", score)
        put("chatId", chatId)
        put("messageId", messageId)
        put("force", force)
        put("disableEditMessage", disableEditMessage)
    }

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = client.postJson<List<GameHighScore>>("getGameHighScores",
        GetGameHighScores(
            userId = userId,
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId
        )
    ).afterMethod("getGameHighScores") {
        put("userId", userId)
        put("chatId", chatId)
        put("messageId", messageId)
        put("inlineMessageId", inlineMessageId)
    }

    override suspend fun editMessageText(
        inlineMessageId: String,
        text: String,
        businessConnectionId: String?,
        parseMode: String?,
        entities: Iterable<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson<Boolean>("editMessageText",
        EditMessageTextByInlineMessageId(
            inlineMessageId = inlineMessageId,
            text = text,
            businessConnectionId = businessConnectionId,
            parseMode = parseMode,
            entities = entities,
            linkPreviewOptions = linkPreviewOptions,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageText") {
        put("inlineMessageId", inlineMessageId)
        put("text", text)
        put("businessConnectionId", businessConnectionId)
        put("parseMode", parseMode)
        put("entities", entities)
        put("linkPreviewOptions", linkPreviewOptions)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageCaption(
        inlineMessageId: String,
        businessConnectionId: String?,
        caption: String?,
        parseMode: String?,
        captionEntities: Iterable<MessageEntity>?,
        showCaptionAboveMedia: Boolean?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson<Boolean>("editMessageCaption",
        EditMessageCaptionByInlineMessageId(
            inlineMessageId = inlineMessageId,
            businessConnectionId = businessConnectionId,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            showCaptionAboveMedia = showCaptionAboveMedia,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageCaption") {
        put("inlineMessageId", inlineMessageId)
        put("businessConnectionId", businessConnectionId)
        put("caption", caption)
        put("parseMode", parseMode)
        put("captionEntities", captionEntities)
        put("showCaptionAboveMedia", showCaptionAboveMedia)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageMedia(
        inlineMessageId: String,
        media: InputMedia,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postMultiPart<Boolean>("editMessageMedia") {
        append("inline_message_id", inlineMessageId)
        append("media", client.toJson(media))
        appendIfNotNull("business_connection_id", businessConnectionId)
        appendIfNotNull("reply_markup", client.toJson(replyMarkup))

        appendContent(media.media)

        appendContentIfNotNull(media.thumbnail)

        appendContentIfNotNull(media.cover)
    }.afterMethod("editMessageMedia") {
        put("inlineMessageId", inlineMessageId)
        put("media", media)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageLiveLocation(
        inlineMessageId: String,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String?,
        livePeriod: Int?,
        horizontalAccuracy: Double?,
        heading: Int?,
        proximityAlertRadius: Int?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson<Boolean>("editMessageLiveLocation",
        EditMessageLiveLocationByInlineMessageId(
            inlineMessageId = inlineMessageId,
            latitude = latitude,
            longitude = longitude,
            businessConnectionId = businessConnectionId,
            livePeriod = livePeriod,
            horizontalAccuracy = horizontalAccuracy,
            heading = heading,
            proximityAlertRadius = proximityAlertRadius,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageLiveLocation") {
        put("inlineMessageId", inlineMessageId)
        put("latitude", latitude)
        put("longitude", longitude)
        put("businessConnectionId", businessConnectionId)
        put("livePeriod", livePeriod)
        put("horizontalAccuracy", horizontalAccuracy)
        put("heading", heading)
        put("proximityAlertRadius", proximityAlertRadius)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun stopMessageLiveLocation(
        inlineMessageId: String,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson<Boolean>("stopMessageLiveLocation",
        StopMessageLiveLocationByInlineMessageId(
            inlineMessageId = inlineMessageId,
            businessConnectionId = businessConnectionId,
            replyMarkup = replyMarkup
        )
    ).afterMethod("stopMessageLiveLocation") {
        put("inlineMessageId", inlineMessageId)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun editMessageReplyMarkup(
        inlineMessageId: String,
        businessConnectionId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Boolean = client.postJson<Boolean>("editMessageReplyMarkup",
        EditMessageReplyMarkupByInlineMessageId(
            inlineMessageId = inlineMessageId,
            businessConnectionId = businessConnectionId,
            replyMarkup = replyMarkup
        )
    ).afterMethod("editMessageReplyMarkup") {
        put("inlineMessageId", inlineMessageId)
        put("businessConnectionId", businessConnectionId)
        put("replyMarkup", replyMarkup)
    }

    override suspend fun setGameScore(
        userId: Long,
        score: Int,
        inlineMessageId: String,
        force: Boolean?,
        disableEditMessage: Boolean?,
    ): Boolean = client.postJson<Boolean>("setGameScore",
        SetGameScoreByInlineMessageId(
            userId = userId,
            score = score,
            inlineMessageId = inlineMessageId,
            force = force,
            disableEditMessage = disableEditMessage
        )
    ).afterMethod("setGameScore") {
        put("userId", userId)
        put("score", score)
        put("inlineMessageId", inlineMessageId)
        put("force", force)
        put("disableEditMessage", disableEditMessage)
    }

    private suspend inline fun <T> T.afterMethod(
        methodName: String,
        crossinline
        collectArguments: MutableMap<String, Any?>.() -> Unit = {},
    ): T {
        eventManager.sendAfterMethodEvent(methodName, this, collectArguments)

        return this
    }
}
