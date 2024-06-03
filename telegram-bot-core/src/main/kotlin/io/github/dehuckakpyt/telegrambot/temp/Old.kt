package io.github.dehuckakpyt.telegrambot.temp
//
//import io.github.dehuckakpyt.telegrambot.client.TelegramApiClient
//import io.github.dehuckakpyt.telegrambot.ext.*
//import io.github.dehuckakpyt.telegrambot.model.internal.*
//import io.github.dehuckakpyt.telegrambot.model.telegram.*
//import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
//import io.github.dehuckakpyt.telegrambot.model.telegram.input.NamedContentInput
//import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
//import io.ktor.client.request.*
//import io.ktor.client.statement.*

/**
 * Created on 05.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
//class TelegramBotImpl(
//    private val token: String,
//    override val username: String,
//    private val messageSource: MessageSource,
//) : TelegramBot {
//    private val client = TelegramApiClient(token)
//    override fun stop(): Unit = client.close()
//
//    override suspend fun getUpdates(
//        offset: Int?, limit: Int?, timeout: Int?, allowedUpdates: Iterable<AllowedUpdate>?,
//    ): List<Update> = client.postJson("getUpdates", UpdateRequest(offset, limit, timeout, allowedUpdates))
//
//    override suspend fun setWebhook(
//        url: String,
//        certificate: NamedContentInput?,
//        ipAddress: String?,
//        maxConnections: Int?,
//        allowedUpdates: List<AllowedUpdate>?,
//        dropPendingUpdates: Boolean?,
//        secretToken: String?,
//    ): Boolean = client.postMultiPart("setWebhook") {
//        append("url", url)
//        appendContentIfNotNull("certificate", certificate)
//        appendIfNotNull("ip_address", ipAddress)
//        appendIfNotNull("max_connections", maxConnections)
//        appendIfNotNull("allowed_updates", client.toJson(allowedUpdates))
//        appendIfNotNull("drop_pending_updates", dropPendingUpdates)
//        appendIfNotNull("secret_token", secretToken)
//    }
//
//    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean = client.get("deleteWebhook") {
//        parameter("drop_pending_updates", dropPendingUpdates)
//    }
//
//    override suspend fun getWebhookInfo(): WebhookInfo = client.get("getWebhookInfo")
//
//    override suspend fun getMe(): User = client.get("getMe")
//
//    override suspend fun logOut(): Boolean = client.get("logOut")
//
//    override suspend fun close(): Boolean = client.get("close")
//
//    override suspend fun sendMessage(
//        chatId: String,
//        text: String,
//        parseMode: ParseMode?,
//        entities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        linkPreviewOptions: LinkPreviewOptions?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendMessage", SendMessage(
//            chatId = chatId,
//            text = text,
//            parseMode = parseMode,
//            entities = entities,
//            messageThreadId = messageThreadId,
//            businessConnectionId = businessConnectionId,
//            linkPreviewOptions = linkPreviewOptions,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "TEXT", text = text) }
//
//    override suspend fun forwardMessage(
//        chatId: String,
//        fromChatId: String,
//        messageId: Long,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//    ): Message = client.postJson(
//        "forwardMessage", ForwardMessage(
//            chatId = chatId,
//            fromChatId = fromChatId,
//            messageId = messageId,
//            messageThreadId = messageThreadId,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//        )
//    )
//
//    override suspend fun forwardMessages(
//        chatId: String,
//        fromChatId: String,
//        messageIds: Iterable<Long>,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//    ): List<MessageId> = client.postJson(
//        "forwardMessages", ForwardMessages(
//            chatId = chatId,
//            fromChatId = fromChatId,
//            messageIds = messageIds,
//            messageThreadId = messageThreadId,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//        )
//    )
//
//    override suspend fun copyMessage(
//        chatId: String,
//        fromChatId: String,
//        messageId: Long,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): MessageId = client.postJson(
//        "copyMessage", CopyMessage(
//            chatId = chatId,
//            fromChatId = fromChatId,
//            messageId = messageId,
//            caption = caption,
//            parseMode = parseMode,
//            captionEntities = captionEntities,
//            messageThreadId = messageThreadId,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    )
//
//    override suspend fun copyMessages(
//        chatId: String,
//        fromChatId: String,
//        messageIds: Iterable<Long>,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        removeCaption: Boolean?,
//    ): List<MessageId> = client.postJson(
//        "copyMessages", CopyMessages(
//            chatId = chatId,
//            fromChatId = fromChatId,
//            messageIds = messageIds,
//            messageThreadId = messageThreadId,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            removeCaption = removeCaption,
//        )
//    )
//
//    override suspend fun sendPhoto(
//        chatId: String,
//        photo: ContentInput,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendPhoto") {
//        append("chat_id", chatId)
//        appendContent("photo", photo)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "PHOTO", text = caption, fileIds = it.photo.map(PhotoSize::fileId)) }
//
//    override suspend fun sendPhoto(
//        chatId: String,
//        photo: String,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendPhoto") {
//        append("chat_id", chatId)
//        append("photo", photo)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "PHOTO", text = caption, fileIds = it.photo.map(PhotoSize::fileId)) }
//
//    override suspend fun sendAudio(
//        chatId: String,
//        audio: ContentInput,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        performer: String?,
//        title: String?,
//        thumbnail: ContentInput?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendAudio") {
//        append("chat_id", chatId)
//        appendContent("audio", audio)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("performer", performer)
//        appendIfNotNull("title", title)
//        appendThumbnailIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "AUDIO", text = caption, fileIds = listOf(it.audio!!.fileId)) }
//
//    override suspend fun sendAudio(
//        chatId: String,
//        audio: String,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        performer: String?,
//        title: String?,
//        thumbnail: ContentInput?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendAudio") {
//        append("chat_id", chatId)
//        append("audio", audio)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("performer", performer)
//        appendIfNotNull("title", title)
//        appendThumbnailIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "AUDIO", text = caption, fileIds = listOf(it.audio!!.fileId)) }
//
//    override suspend fun sendDocument(
//        chatId: String,
//        document: NamedContentInput,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        disableContentTypeDetection: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendDocument") {
//        append("chat_id", chatId)
//        appendContent("document", document)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "DOCUMENT", text = caption, fileIds = listOf(it.document!!.fileId)) }
//
//    override suspend fun sendDocument(
//        chatId: String,
//        document: String,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        disableContentTypeDetection: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendDocument") {
//        append("chat_id", chatId)
//        append("document", document)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "DOCUMENT", text = caption, fileIds = listOf(it.document!!.fileId)) }
//
//    override suspend fun sendVideo(
//        chatId: String,
//        video: ContentInput,
//        duration: Long?,
//        width: Long?,
//        height: Long?,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        supportsStreaming: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVideo") {
//        append("chat_id", chatId)
//        appendContent("video", video)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("width", width)
//        appendIfNotNull("height", height)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("supports_streaming", supportsStreaming)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VIDEO", text = caption, fileIds = listOf(it.video!!.fileId)) }
//
//    override suspend fun sendVideo(
//        chatId: String,
//        video: String,
//        duration: Long?,
//        width: Long?,
//        height: Long?,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        supportsStreaming: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVideo") {
//        append("chat_id", chatId)
//        append("video", video)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("width", width)
//        appendIfNotNull("height", height)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("supports_streaming", supportsStreaming)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VIDEO", text = caption, fileIds = listOf(it.video!!.fileId)) }
//
//    override suspend fun sendAnimation(
//        chatId: String,
//        animation: ContentInput,
//        duration: Long?,
//        width: Long?,
//        height: Long?,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendAnimation") {
//        append("chat_id", chatId)
//        appendContent("animation", animation)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("width", width)
//        appendIfNotNull("height", height)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "ANIMATION", text = caption, fileIds = listOf(it.animation!!.fileId)) }
//
//    override suspend fun sendAnimation(
//        chatId: String,
//        animation: String,
//        duration: Long?,
//        width: Long?,
//        height: Long?,
//        thumbnail: ContentInput?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        hasSpoiler: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendAnimation") {
//        append("chat_id", chatId)
//        append("animation", animation)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("width", width)
//        appendIfNotNull("height", height)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("has_spoiler", hasSpoiler)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "ANIMATION", text = caption, fileIds = listOf(it.animation!!.fileId)) }
//
//    override suspend fun sendVoice(
//        chatId: String,
//        voice: ContentInput,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVoice") {
//        append("chat_id", chatId)
//        appendContent("voice", voice)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VOICE", text = caption, fileIds = listOf(it.voice!!.fileId)) }
//
//    override suspend fun sendVoice(
//        chatId: String,
//        voice: String,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVoice") {
//        append("chat_id", chatId)
//        append("voice", voice)
//        appendIfNotNull("caption", caption)
//        appendIfNotNull("parse_mode", parseMode?.toString())
//        appendIfNotNull("caption_entities", client.toJson(captionEntities))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VOICE", text = caption, fileIds = listOf(it.voice!!.fileId)) }
//
//    override suspend fun sendVideoNote(
//        chatId: String,
//        videoNote: ContentInput,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        length: Long?,
//        thumbnail: ContentInput?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVideoNote") {
//        append("chat_id", chatId)
//        appendContent("video_note", videoNote)
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("length", length)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VIDEO_NOTE", fileIds = listOf(it.videoNote!!.fileId)) }
//
//    override suspend fun sendVideoNote(
//        chatId: String,
//        videoNote: String,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        duration: Long?,
//        length: Long?,
//        thumbnail: ContentInput?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendVideoNote") {
//        append("chat_id", chatId)
//        append("video_note", videoNote)
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("duration", duration)
//        appendIfNotNull("length", length)
//        appendContentIfNotNull("thumbnail", thumbnail)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VIDEO_NOTE", fileIds = listOf(it.videoNote!!.fileId)) }
//
//    override suspend fun sendMediaGroup(
//        chatId: String,
//        media: Iterable<InputMedia>,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//    ): ArrayList<Message> = client.postMultiPart("sendMediaGroup") {
//        append("chat_id", chatId)
//        append("media", client.toJson(media))
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//
//        media.forEach { input ->
//            appendContentIfNotNull(input.mediaContent)
//            appendContentIfNotNull(input.thumbnailContent)
//        }
//    }
//
//    override suspend fun sendLocation(
//        chatId: String,
//        latitude: Float,
//        longitude: Float,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        horizontalAccuracy: Float?,
//        livePeriod: Long?,
//        heading: Long?,
//        proximityAlertRadius: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendLocation",
//        SendLocation(
//            chatId = chatId,
//            latitude = latitude,
//            longitude = longitude,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            horizontalAccuracy = horizontalAccuracy,
//            livePeriod = livePeriod,
//            heading = heading,
//            proximityAlertRadius = proximityAlertRadius,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "LOCATION", text = "latitude = $latitude, longitude = $longitude") }
//
//    override suspend fun sendVenue(
//        chatId: String,
//        latitude: Float,
//        longitude: Float,
//        title: String,
//        address: String,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        foursquareId: String?,
//        foursquareType: String?,
//        googlePlaceId: String?,
//        googlePlaceType: String?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendVenue",
//        SendVenue(
//            chatId = chatId,
//            latitude = latitude,
//            longitude = longitude,
//            title = title,
//            address = address,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            foursquareId = foursquareId,
//            foursquareType = foursquareType,
//            googlePlaceId = googlePlaceId,
//            googlePlaceType = googlePlaceType,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "VENUE", text = "latitude = $latitude, longitude = $longitude, title = $title, address = $address") }
//
//    override suspend fun sendContact(
//        chatId: String,
//        phoneNumber: String,
//        firstName: String,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        lastName: String?,
//        vcard: String?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendContact",
//        SendContact(
//            chatId = chatId,
//            phone = phoneNumber,
//            firstName = firstName,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            lastName = lastName,
//            vcard = vcard,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "CONTACT", text = "phoneNumber = $phoneNumber, firstName = $firstName") }
//
//    override suspend fun sendPoll(
//        chatId: String,
//        question: String,
//        options: List<InputPollOption>,
//        questionParseMode: String?,
//        questionEntities: List<MessageEntity>?,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        isAnonymous: Boolean?,
//        type: String?,
//        allowsMultipleAnswers: Boolean?,
//        correctOptionId: Long?,
//        explanation: String?,
//        explanationParseMode: String?,
//        explanationEntities: List<MessageEntity>?,
//        openPeriod: Long?,
//        closeDate: Long?,
//        isClosed: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendPoll",
//        SendPoll(
//            chatId = chatId,
//            question = question,
//            options = options,
//            questionParseMode = questionParseMode,
//            questionEntities = questionEntities,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            isAnonymous = isAnonymous,
//            type = type,
//            allowsMultipleAnswers = allowsMultipleAnswers,
//            correctOptionId = correctOptionId,
//            explanation = explanation,
//            explanationParseMode = explanationParseMode,
//            explanationEntities = explanationEntities,
//            openPeriod = openPeriod,
//            closeDate = closeDate,
//            isClosed = isClosed,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "POLL", text = question) }
//
//    override suspend fun sendDice(
//        chatId: String,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        emoji: String?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postJson<Message>(
//        "sendDice",
//        SendDice(
//            chatId = chatId,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            emoji = emoji,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "DICE", text = emoji) }
//
//    override suspend fun sendChatAction(
//        chatId: String,
//        action: Action,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//    ): Boolean = client.postJson(
//        "sendChatAction",
//        SendChatAction(
//            chatId = chatId,
//            action = action,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId
//        )
//    )
//
//    override suspend fun setMessageReaction(
//        chatId: String,
//        messageId: Long,
//        reaction: Iterable<ReactionType>?,
//        isBig: Boolean?,
//    ): Boolean = client.postJson("setMessageReaction", SetMessageReaction(chatId, messageId, reaction, isBig))
//
//    override suspend fun getUserProfilePhotos(userId: Long, offset: Long?, limit: Long?): UserProfilePhotos = client.get("getUserProfilePhotos") {
//        parameter("user_id", userId)
//        parameter("offset", offset)
//        parameter("limit", limit)
//    }
//
//    override suspend fun getFile(fileId: String): File = client.get("getFile") {
//        parameter("file_id", fileId)
//    }
//
//    override suspend fun banChatMember(
//        chatId: String,
//        userId: Long,
//        untilDate: Long?,
//        revokeMessages: Boolean?,
//    ): Boolean = client.postJson("banChatMember", BanChatMember(chatId, userId, untilDate, revokeMessages))
//
//    override suspend fun unbanChatMember(chatId: String, userId: Long, onlyIfBanned: Boolean?): Boolean = client.postJson(
//        "unbanChatMember",
//        UnbanChatMember(chatId, userId, onlyIfBanned)
//    )
//
//    override suspend fun restrictChatMember(
//        chatId: String,
//        userId: Long,
//        permissions: ChatPermissions,
//        useIndependentChatPermissions: Boolean?,
//        untilDate: Long?,
//    ): Boolean = client.postJson(
//        "restrictChatMember",
//        RestrictChatMember(chatId, userId, permissions, useIndependentChatPermissions, untilDate)
//    )
//
//    override suspend fun promoteChatMember(
//        chatId: String,
//        userId: Long,
//        isAnonymous: Boolean?,
//        canManageChat: Boolean?,
//        canDeleteMessages: Boolean?,
//        canManageVideoChats: Boolean?,
//        canRestrictMembers: Boolean?,
//        canPromoteMembers: Boolean?,
//        canChangeInfo: Boolean?,
//        canInviteUsers: Boolean?,
//        canPostMessages: Boolean?,
//        canEditMessages: Boolean?,
//        canPinMessages: Boolean?,
//        canPostStories: Boolean?,
//        canEditStories: Boolean?,
//        canDeleteStories: Boolean?,
//        canManageTopics: Boolean?,
//    ): Boolean = client.postJson(
//        "promoteChatMember",
//        PromoteChatMember(
//            chatId = chatId,
//            userId = userId,
//            isAnonymous = isAnonymous,
//            canManageChat = canManageChat,
//            canDeleteMessages = canDeleteMessages,
//            canManageVideoChats = canManageVideoChats,
//            canRestrictMembers = canRestrictMembers,
//            canPromoteMembers = canPromoteMembers,
//            canChangeInfo = canChangeInfo,
//            canInviteUsers = canInviteUsers,
//            canPostMessages = canPostMessages,
//            canEditMessages = canEditMessages,
//            canPinMessages = canPinMessages,
//            canPostStories = canPostStories,
//            canEditStories = canEditStories,
//            canDeleteStories = canDeleteStories,
//            canManageTopics = canManageTopics
//        )
//    )
//
//    override suspend fun setChatAdministratorCustomTitle(chatId: String, userId: Long, customTitle: String): Boolean = client.postJson(
//        "setChatAdministratorCustomTitle", SetChatAdministratorCustomTitle(chatId, userId, customTitle)
//    )
//
//    override suspend fun banChatSenderChat(chatId: String, senderChatId: Long): Boolean = client.postJson(
//        "banChatSenderChat", BanChatSenderChat(chatId, senderChatId)
//    )
//
//    override suspend fun unbanChatSenderChat(chatId: String, senderChatId: Long): Boolean = client.postJson(
//        "unbanChatSenderChat", UnbanChatSenderChat(chatId, senderChatId)
//    )
//
//    override suspend fun setChatPermissions(
//        chatId: String,
//        permissions: ChatPermissions,
//        useIndependentChatPermissions: Boolean?,
//    ): Boolean = client.postJson("setChatPermissions", SetChatPermissions(chatId, permissions, useIndependentChatPermissions))
//
//    override suspend fun exportChatInviteLink(chatId: String): String = client.postJson(
//        "exportChatInviteLink", ExportChatInviteLink(chatId)
//    )
//
//    override suspend fun createChatInviteLink(
//        chatId: String,
//        name: String?,
//        expireDate: Long?,
//        memberLimit: Long?,
//        createsJoinRequest: Boolean?,
//    ): ChatInviteLink = client.postJson(
//        "createChatInviteLink", CreateChatInviteLink(chatId, name, expireDate, memberLimit, createsJoinRequest)
//    )
//
//    override suspend fun editChatInviteLink(
//        chatId: String,
//        inviteLink: String,
//        name: String?,
//        expireDate: Long?,
//        memberLimit: Long?,
//        createsJoinRequest: Boolean?,
//    ): ChatInviteLink = client.postJson(
//        "editChatInviteLink", EditChatInviteLink(chatId, inviteLink, name, expireDate, memberLimit, createsJoinRequest)
//    )
//
//    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink = client.postJson(
//        "revokeChatInviteLink", RevokeChatInviteLink(chatId, inviteLink)
//    )
//
//    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean = client.postJson(
//        "approveChatJoinRequest", ApproveChatJoinRequest(chatId, userId)
//    )
//
//    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean = client.postJson(
//        "declineChatJoinRequest", DeclineChatJoinRequest(chatId, userId)
//    )
//
//    override suspend fun setChatPhoto(chatId: String, photo: ContentInput): Boolean = client.postMultiPart("setChatPhoto") {
//        append("chat_id", chatId)
//        appendContent("photo", photo)
//    }
//
//    override suspend fun setChatPhoto(chatId: String, photo: String): Boolean = client.postMultiPart("setChatPhoto") {
//        append("chat_id", chatId)
//        append("photo", photo)
//    }
//
//    override suspend fun deleteChatPhoto(chatId: String): Boolean = client.postJson(
//        "deleteChatPhoto", DeleteChatPhoto(chatId)
//    )
//
//    override suspend fun setChatTitle(chatId: String, title: String): Boolean = client.postJson(
//        "setChatTitle", SetChatTitle(chatId, title)
//    )
//
//    override suspend fun setChatDescription(chatId: String, description: String): Boolean = client.postJson(
//        "setChatDescription", SetChatDescription(chatId, description)
//    )
//
//    override suspend fun pinChatMessage(chatId: String, messageId: Long, disableNotification: Boolean?): Boolean = client.postJson(
//        "pinChatMessage", PinChatMessage(chatId, messageId, disableNotification)
//    )
//
//    override suspend fun unpinChatMessage(chatId: String, messageId: Long?): Boolean = client.postJson(
//        "unpinChatMessage", UnpinChatMessage(chatId, messageId)
//    )
//
//    override suspend fun unpinAllChatMessages(chatId: String): Boolean = client.postJson(
//        "unpinAllChatMessages", UnpinAllChatMessages(chatId)
//    )
//
//    override suspend fun leaveChat(chatId: String): Boolean = client.postJson(
//        "leaveChat", LeaveChat(chatId)
//    )
//
//    override suspend fun getChat(chatId: String): ChatFullInfo = client.get("getChat") {
//        parameter("chat_id", chatId)
//    }
//
//    override suspend fun getChatAdministrators(chatId: String): ArrayList<ChatMember> = client.get("getChatAdministrators") {
//        parameter("chat_id", chatId)
//    }
//
//    override suspend fun getChatMemberCount(chatId: String): Long = client.get("getChatMemberCount") {
//        parameter("chat_id", chatId)
//    }
//
//    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember = client.get("getChatMember") {
//        parameter("chat_id", chatId)
//        parameter("user_id", userId)
//    }
//
//    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean = client.postJson(
//        "setChatStickerSet", SetChatStickerSet(chatId, stickerSetName)
//    )
//
//    override suspend fun deleteChatStickerSet(chatId: String): Boolean = client.postJson(
//        "deleteChatStickerSet", DeleteChatStickerSet(chatId)
//    )
//
//    override suspend fun getForumTopicIconStickers(): List<Sticker> = client.get("getForumTopicIconStickers")
//
//    override suspend fun createForumTopic(
//        chatId: String,
//        name: String,
//        iconColor: Int?,
//        iconCustomEmojiId: String?,
//    ): ForumTopic = client.postJson("createForumTopic", CreateForumTopic(chatId, name, iconColor, iconCustomEmojiId))
//
//    override suspend fun editForumTopic(
//        chatId: String,
//        messageThreadId: Long,
//        name: String?,
//        iconCustomEmojiId: String?,
//    ): Boolean = client.postJson("editForumTopic", EditForumTopic(chatId, messageThreadId, name, iconCustomEmojiId))
//
//    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean = client.postJson(
//        "closeForumTopic", CloseForumTopic(chatId, messageThreadId)
//    )
//
//    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean = client.postJson(
//        "reopenForumTopic", ReopenForumTopic(chatId, messageThreadId)
//    )
//
//    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean = client.postJson(
//        "deleteForumTopic", DeleteForumTopic(chatId, messageThreadId)
//    )
//
//    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean = client.postJson(
//        "unpinAllForumTopicMessages", UnpinAllForumTopicMessages(chatId, messageThreadId)
//    )
//
//    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean = client.postJson(
//        "editGeneralForumTopic", EditGeneralForumTopic(chatId, name)
//    )
//
//    override suspend fun closeGeneralForumTopic(chatId: String): Boolean = client.postJson(
//        "closeGeneralForumTopic", CloseGeneralForumTopic(chatId)
//    )
//
//    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean = client.postJson(
//        "reopenGeneralForumTopic", ReopenGeneralForumTopic(chatId)
//    )
//
//    override suspend fun hideGeneralForumTopic(chatId: String): Boolean = client.postJson(
//        "hideGeneralForumTopic", HideGeneralForumTopic(chatId)
//    )
//
//    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean = client.postJson(
//        "unhideGeneralForumTopic", UnhideGeneralForumTopic(chatId)
//    )
//
//    override suspend fun unpinAllGeneralForumTopicMessages(chatId: String): Boolean = client.postJson(
//        "unpinAllGeneralForumTopicMessages", UnpinAllGeneralForumTopicMessages(chatId)
//    )
//
//    override suspend fun answerCallbackQuery(
//        callbackQueryId: String,
//        text: String?,
//        showAlert: Boolean?,
//        url: String?,
//        cacheTime: Long?,
//    ): Boolean = client.postJson("answerCallbackQuery", AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))
//
//    override suspend fun getUserChatBoosts(
//        chatId: String,
//        userId: Long,
//    ): UserChatBoosts = client.get("getUserChatBoosts") {
//        parameter("chat_id", chatId)
//        parameter("user_id", userId)
//    }
//
//    override suspend fun getBusinessConnection(businessConnectionId: String): BusinessConnection = client.get("getBusinessConnection") {
//        parameter("business_connection_id", businessConnectionId)
//    }
//
//    override suspend fun setMyCommands(
//        commands: List<BotCommand>, scope: BotCommandScope?, languageCode: String?,
//    ): Boolean = client.postJson("setMyCommands", SetMyCommands(commands, scope, languageCode))
//
//    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
//        client.postJson("deleteMyCommands", DeleteMyCommands(scope, languageCode))
//
//    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?): List<BotCommand> =
//        client.postJson("getMyCommands", GetMyCommands(scope, languageCode))
//
//    override suspend fun setMyName(name: String?, languageCode: String?): Boolean =
//        client.postJson("setMyName", SetMyName(name, languageCode))
//
//    override suspend fun getMyName(languageCode: String?): BotName = client.get("getMyName") {
//        parameter("language_code", languageCode)
//    }
//
//    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean = client.postJson(
//        "setMyDescription", SetMyDescription(description, languageCode)
//    )
//
//    override suspend fun getMyDescription(languageCode: String?): BotDescription = client.get("getMyDescription") {
//        parameter("language_code", languageCode)
//    }
//
//    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?): Boolean = client.postJson(
//        "setMyShortDescription", SetMyShortDescription(shortDescription, languageCode)
//    )
//
//    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription = client.get("getMyShortDescription") {
//        parameter("language_code", languageCode)
//    }
//
//    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean = client.postJson(
//        "setChatMenuButton", SetChatMenuButton(chatId, menuButton)
//    )
//
//    override suspend fun getChatMenuButton(chatId: Long?): MenuButton = client.get("getChatMenuButton") {
//        parameter("chat_id", chatId)
//    }
//
//    override suspend fun setMyDefaultAdministratorRights(
//        rights: ChatAdministratorRights?,
//        forChannels: Boolean?,
//    ): Boolean = client.postJson("setMyDefaultAdministratorRights", SetMyDefaultAdministratorRights(rights, forChannels))
//
//    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?): ChatAdministratorRights = client.get("getMyDefaultAdministratorRights") {
//        parameter("for_channels", forChannels)
//    }
//
//    override suspend fun editMessageText(
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        text: String,
//        parseMode: ParseMode?,
//        entities: List<MessageEntity>?,
//        linkPreviewOptions: LinkPreviewOptions?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "editMessageText",
//        EditMessageText(
//            chatId = chatId,
//            messageId = messageId,
//            inlineMessageId = inlineMessageId,
//            text = text,
//            parseMode = parseMode,
//            entities = entities,
//            linkPreviewOptions = linkPreviewOptions,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "EDIT_TEXT", text = text) }
//
//    override suspend fun editMessageCaption(
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        caption: String?,
//        parseMode: ParseMode?,
//        captionEntities: List<MessageEntity>?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "editMessageCaption",
//        EditMessageCaption(
//            chatId = chatId,
//            messageId = messageId,
//            inlineMessageId = inlineMessageId,
//            caption = caption,
//            parseMode = parseMode,
//            captionEntities = captionEntities,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "EDIT_CAPTION", text = caption) }
//
//    override suspend fun editMessageMedia(
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        media: InputMedia,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postMultiPart<Message>("editMessageMedia") {
//        appendIfNotNull("chat_id", chatId)
//        appendIfNotNull("message_id", messageId)
//        appendIfNotNull("inline_message_id", inlineMessageId)
//        append("media", client.toJson(media))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "EDIT_MEDIA", text = media.media) }
//
//    override suspend fun editMessageLiveLocation(
//        latitude: Float,
//        longitude: Float,
//        livePeriod: Int?,
//        horizontalAccuracy: Float?,
//        heading: Long?,
//        proximityAlertRadius: Long?,
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "editMessageLiveLocation", EditMessageLiveLocation(
//            chatId = chatId,
//            messageId = messageId,
//            inlineMessageId = inlineMessageId,
//            latitude = latitude,
//            longitude = longitude,
//            livePeriod = livePeriod,
//            horizontalAccuracy = horizontalAccuracy,
//            heading = heading,
//            proximityAlertRadius = proximityAlertRadius,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "EDIT_LIVE_LOCATION", text = "latitude = $latitude, longitude = $longitude") }
//
//    override suspend fun stopMessageLiveLocation(
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "stopMessageLiveLocation",
//        StopMessageLiveLocation(
//            chatId = chatId,
//            messageId = messageId,
//            inlineMessageId = inlineMessageId,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "STOP_LIVE_LOCATION") }
//
//    override suspend fun editMessageReplyMarkup(
//        chatId: String?,
//        messageId: Long?,
//        inlineMessageId: String?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "editMessageReplyMarkup",
//        EditMessageReplyMarkup(
//            chatId,
//            messageId,
//            inlineMessageId,
//            replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "EDIT_REPLY_MARKUP") }
//
//    override suspend fun stopPoll(chatId: String, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll = client.postJson(
//        "stopPoll", StopPoll(chatId, messageId, replyMarkup)
//    )
//
//    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean = client.postJson(
//        "deleteMessage", DeleteMessage(chatId, messageId)
//    )
//
//    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean = client.postJson(
//        "deleteMessages", DeleteMessages(chatId, messageIds)
//    )
//
//    override suspend fun sendSticker(
//        chatId: String,
//        sticker: ContentInput,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        emoji: String?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: ReplyKeyboard?,
//    ): Message = client.postMultiPart<Message>("sendSticker") {
//        append("chat_id", chatId)
//        appendContent("sticker", sticker)
//        appendIfNotNull("business_connection_id", businessConnectionId)
//        appendIfNotNull("message_thread_id", messageThreadId)
//        appendIfNotNull("emoji", emoji)
//        appendIfNotNull("disable_notification", disableNotification)
//        appendIfNotNull("protect_content", protectContent)
//        appendIfNotNull("reply_parameters", client.toJson(replyParameters))
//        appendIfNotNull("reply_markup", client.toJson(replyMarkup))
//    }.also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "STICKER", text = emoji) }
//
//    override suspend fun getStickerSet(name: String): StickerSet = client.get("getStickerSet") {
//        parameter("name", name)
//    }
//
//    override suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker> = client.postJson(
//        "getCustomEmojiStickers", GetCustomEmojiStickers(customEmojiIds)
//    )
//
//    override suspend fun uploadStickerFile(userId: Long, sticker: ContentInput, stickerFormat: String): File = client.postMultiPart("uploadStickerFile") {
//        append("user_id", userId)
//        appendContent("sticker", sticker)
//        append("sticker_format", stickerFormat)
//    }
//
//    override suspend fun createNewStickerSet(
//        userId: Long,
//        name: String,
//        title: String,
//        stickers: Iterable<InputSticker>,
//        stickerType: String?,
//        needsRepainting: Boolean?,
//    ): Boolean = client.postMultiPart("createNewStickerSet") {
//        append("user_id", userId)
//        append("name", name)
//        append("title", title)
//        append("stickers", client.toJson(stickers))
//        appendIfNotNull("sticker_type", stickerType)
//        appendIfNotNull("needs_repainting", needsRepainting)
//
//        stickers.forEach { sticker ->
//            appendContentIfNotNull(sticker.stickerContent)
//        }
//    }
//
//    override suspend fun addStickerToSet(
//        userId: Long,
//        name: String,
//        sticker: InputSticker,
//    ): Boolean = client.postMultiPart("addStickerToSet") {
//        append("user_id", userId)
//        append("name", name)
//        append("sticker", client.toJson(sticker))
//
//        appendContentIfNotNull(sticker.stickerContent)
//    }
//
//    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean = client.postJson(
//        "setStickerPositionInSet", SetStickerPositionInSet(sticker, position)
//    )
//
//    override suspend fun deleteStickerFromSet(sticker: String): Boolean = client.postJson(
//        "deleteStickerFromSet", DeleteStickerFromSet(sticker)
//    )
//
//    override suspend fun replaceStickerInSet(
//        userId: Long,
//        name: String,
//        oldSticker: String,
//        sticker: InputSticker,
//    ): Boolean = client.postMultiPart("replaceStickerInSet") {
//        append("user_id", userId)
//        append("name", name)
//        append("old_sticker", oldSticker)
//        append("sticker", client.toJson(sticker))
//
//        appendContentIfNotNull(sticker.stickerContent)
//    }
//
//    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean = client.postJson(
//        "setStickerEmojiList", SetStickerEmojiList(sticker, emojiList)
//    )
//
//    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean = client.postJson(
//        "setStickerKeywords", SetStickerKeywords(sticker, keywords)
//    )
//
//    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?): Boolean = client.postJson(
//        "setStickerMaskPosition", SetStickerMaskPosition(sticker, maskPosition)
//    )
//
//    override suspend fun setStickerSetTitle(sticker: String, title: String): Boolean = client.postJson(
//        "setStickerSetTitle", SetStickerSetTitle(sticker, title)
//    )
//
//    override suspend fun setStickerSetThumbnail(
//        name: String,
//        userId: Long,
//        format: String,
//        thumbnail: ContentInput?,
//    ): Boolean = client.postMultiPart("setStickerSetThumbnail") {
//        append("name", name)
//        append("user_id", userId)
//        append("format", format)
//
//        appendContentIfNotNull("thumbnail", thumbnail)
//    }
//
//    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?): Boolean = client.postJson(
//        "setCustomEmojiStickerSetThumbnail", SetCustomEmojiStickerSetThumbnail(name, customEmojiId)
//    )
//
//    override suspend fun deleteStickerSet(name: String): Boolean = client.postJson(
//        "deleteStickerSet", DeleteStickerSet(name)
//    )
//
//    override suspend fun answerInlineQuery(
//        inlineQueryId: String,
//        results: List<InlineQueryResult>,
//        cacheTime: Int?,
//        isPersonal: Boolean?,
//        nextOffset: String?,
//        button: InlineQueryResultsButton?,
//    ): Boolean = client.postJson("answerInlineQuery", AnswerInlineQuery(inlineQueryId, results, cacheTime, isPersonal, nextOffset, button))
//
//    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): SentWebAppMessage = client.postJson(
//        "answerWebAppQuery", AnswerWebAppQuery(webAppQueryId, result)
//    )
//
//    override suspend fun sendInvoice(
//        chatId: String,
//        title: String,
//        description: String,
//        payload: String,
//        providerToken: String,
//        currency: String,
//        prices: List<LabeledPrice>,
//        messageThreadId: Long?,
//        maxTipAmount: Int?,
//        suggestedTipAmount: List<Int>?,
//        startParameter: String?,
//        providerData: String?,
//        photoUrl: String?,
//        photoSize: Int?,
//        photoWidth: Int?,
//        photoHeight: Int?,
//        needName: Boolean?,
//        needPhoneNumber: Boolean?,
//        needEmail: Boolean?,
//        needShippingAddress: Boolean?,
//        sendPhoneNumberToProvider: Boolean?,
//        sendEmailToProvider: Boolean?,
//        isFlexible: Boolean?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "sendInvoice",
//        SendInvoice(
//            chatId = chatId,
//            title = title,
//            description = description,
//            payload = payload,
//            providerToken = providerToken,
//            currency = currency,
//            prices = prices,
//            messageThreadId = messageThreadId,
//            maxTipAmount = maxTipAmount,
//            suggestedTipAmount = suggestedTipAmount,
//            startParameter = startParameter,
//            providerData = providerData,
//            photoUrl = photoUrl,
//            photoSize = photoSize,
//            photoWidth = photoWidth,
//            photoHeight = photoHeight,
//            needName = needName,
//            needPhoneNumber = needPhoneNumber,
//            needEmail = needEmail,
//            needShippingAddress = needShippingAddress,
//            sendPhoneNumberToProvider = sendPhoneNumberToProvider,
//            sendEmailToProvider = sendEmailToProvider,
//            isFlexible = isFlexible,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "INVOICE", text = title) }
//
//    override suspend fun createInvoiceLink(
//        title: String,
//        description: String,
//        payload: String,
//        providerToken: String,
//        currency: String,
//        prices: List<LabeledPrice>,
//        maxTipAmount: Int?,
//        suggestedTipAmount: List<Int>?,
//        providerData: String?,
//        photoUrl: String?,
//        photoSize: Int?,
//        photoWidth: Int?,
//        photoHeight: Int?,
//        needName: Boolean?,
//        needPhoneNumber: Boolean?,
//        needEmail: Boolean?,
//        needShippingAddress: Boolean?,
//        sendPhoneNumberToProvider: Boolean?,
//        sendEmailToProvider: Boolean?,
//        isFlexible: Boolean?,
//    ): String = client.postJson(
//        "createInvoiceLink",
//        CreateInvoiceLink(
//            title = title,
//            description = description,
//            payload = payload,
//            providerToken = providerToken,
//            currency = currency,
//            prices = prices,
//            maxTipAmount = maxTipAmount,
//            suggestedTipAmount = suggestedTipAmount,
//            providerData = providerData,
//            photoUrl = photoUrl,
//            photoSize = photoSize,
//            photoWidth = photoWidth,
//            photoHeight = photoHeight,
//            needName = needName,
//            needPhoneNumber = needPhoneNumber,
//            needEmail = needEmail,
//            needShippingAddress = needShippingAddress,
//            sendPhoneNumberToProvider = sendPhoneNumberToProvider,
//            sendEmailToProvider = sendEmailToProvider,
//            isFlexible = isFlexible
//        )
//    )
//
//    override suspend fun answerShippingQuery(
//        shippingQueryId: String,
//        ok: Boolean,
//        shippingOptions: List<ShippingOption>?,
//        errorMessage: String?,
//    ): Boolean = client.postJson("answerShippingQuery", AnswerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage))
//
//    override suspend fun answerPreCheckoutQuery(
//        preCheckoutQueryId: String,
//        ok: Boolean,
//        errorMessage: String?,
//    ): Boolean = client.postJson("answerPreCheckoutQuery", AnswerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage))
//
//    override suspend fun setPassportDataErrors(userId: Long, errors: List<PassportElementError>): Boolean = client.postJson(
//        "setPassportDataErrors", SetPassportDataErrors(userId, errors)
//    )
//
//    override suspend fun sendGame(
//        chatId: Long,
//        gameShortName: String,
//        businessConnectionId: String?,
//        messageThreadId: Long?,
//        disableNotification: Boolean?,
//        protectContent: Boolean?,
//        replyParameters: ReplyParameters?,
//        replyMarkup: InlineKeyboardMarkup?,
//    ): Message = client.postJson<Message>(
//        "sendGame",
//        SendGame(
//            chatId = chatId,
//            gameShortName = gameShortName,
//            businessConnectionId = businessConnectionId,
//            messageThreadId = messageThreadId,
//            disableNotification = disableNotification,
//            protectContent = protectContent,
//            replyParameters = replyParameters,
//            replyMarkup = replyMarkup
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "GAME", text = gameShortName) }
//
//    override suspend fun setGameScore(
//        userId: Long,
//        score: Long,
//        force: Boolean?,
//        disableEditMessage: Boolean?,
//        chatId: Long?,
//        messageId: Long?,
//        inlineMessageId: String?,
//    ): Message = client.postJson<Message>(
//        "setGameScore",
//        SetGameScore(
//            userId = userId,
//            score = score,
//            force = force,
//            disableEditMessage = disableEditMessage,
//            chatId = chatId,
//            messageId = messageId,
//            inlineMessageId = inlineMessageId
//        )
//    ).also { messageSource.save(it.chatId, it.from!!.id, true, it.messageId, type = "GAME_SCORE", text = "userId = $userId, score = $score") }
//
//    override suspend fun getGameHighScores(
//        userId: Long,
//        chatId: Long?,
//        messageId: Long?,
//        inlineMessageId: String?,
//    ): List<GameHighScore> = client.get("getGameHighScores") {
//        parameter("user_id", userId)
//        parameter("chat_id", chatId)
//        parameter("message_id", messageId)
//        parameter("inline_message_id", inlineMessageId)
//    }
//
//    //region helpful features
//    override suspend fun download(filePath: String): HttpResponse {
//        return client.client.get("https://api.telegram.org/file/bot$token/${filePath}")
//    }
//    //endregion helpful features
//}