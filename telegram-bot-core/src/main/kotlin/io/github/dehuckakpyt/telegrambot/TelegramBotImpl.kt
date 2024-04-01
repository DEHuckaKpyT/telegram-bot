package io.github.dehuckakpyt.telegrambot

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import io.github.dehuckakpyt.telegrambot.exception.api.TelegramBotApiException
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.model.internal.*
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.TelegramResponse
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.Content
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.NamedContent
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.ContentDisposition
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat


/**
 * Created on 05.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBotImpl(
    private val token: String,
    override val username: String,
    private val messageSource: MessageSource,
) : TelegramBot {

    //region make requests
    private val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            register(Json, JacksonConverter(MAPPER))
        }
        engine {
            socketTimeout = 0
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.telegram.org"
                path("/bot$token/")
            }
        }
    }

    private suspend inline fun <reified T : Any> get(method: String): T = handleRequest(client.get(method))

    private suspend inline fun <reified T> get(method: String, crossinline block: HttpRequestBuilder.() -> Unit): T =
        withContext(Dispatchers.IO) {
            handleRequest(client.get(method, block))
        }

    private suspend inline fun <reified T : Any> postMultiPart(method: String, noinline block: FormBuilder.() -> Unit): T =
        withContext(Dispatchers.IO) {
            handleRequest(client.post(method) {
                setBody(MultiPartFormDataContent(formData(block)))
            })
        }

    private suspend inline fun <reified T : Any, reified R : Any> postJson(method: String, body: T): R {
        val response = withContext(Dispatchers.IO) {
            client.post(method) {
                contentType(Json)
                setBody(body)
            }
        }
        val telegramResponse = response.body<TelegramResponse<R>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse, body)

        return telegramResponse.result!!
    }

    private suspend inline fun <reified T : Any> handleRequest(response: HttpResponse): T {
        val telegramResponse = response.body<TelegramResponse<T>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse)

        return telegramResponse.result!!
    }

    private fun throwException(response: HttpResponse, telegramResponse: TelegramResponse<*>) =
        throwException(response, telegramResponse, null)

    private inline fun <reified T : Any> throwException(response: HttpResponse, telegramResponse: TelegramResponse<*>, body: T? = null) {
        throw TelegramBotApiException(
            """Request to Telegram Error. 
        Request
        Method: ${response.request.method.value}
        Content-type: ${response.request.contentType()?.contentType}
        Url: ${response.request.url}
        Body: ${body?.toJson()}
        
        Response
        Code: ${telegramResponse.errorCode}. 
        Description: ${telegramResponse.description}"""
        )
    }

    private fun FormBuilder.appendIfNotNull(key: String, value: String?) {
        value ?: return
        append(key, value)
    }

    private fun FormBuilder.appendIfNotNull(key: String, value: Int?) {
        value ?: return
        append(key, value)
    }

    private fun FormBuilder.appendIfNotNull(key: String, value: Long?) {
        value ?: return
        append(key, value)
    }

    private fun FormBuilder.appendIfNotNull(key: String, value: Boolean?) {
        value ?: return
        append(key, value.toString())
    }

    private fun FormBuilder.appendThumbnailIfNotNull(key: String, value: Content?) {
        value ?: return
        append("attach://$key", value.byteArray, headersOf(ContentDisposition, "filename=\"${value.name}\""))
    }

    private fun FormBuilder.appendContentIfNotNull(key: String, value: Content?) {
        value ?: return
        append(key, value.byteArray, headersOf(ContentDisposition, "filename=\"${value.name}\""))
    }

    private fun FormBuilder.appendContentIfNotNull(value: NamedContent?) {
        value ?: return
        append(value.name, value.byteArray, headersOf(ContentDisposition, "filename=\"${value.name}\""))
    }

    private fun FormBuilder.appendContent(key: String, value: Content) {
        append(key, value.byteArray, headersOf(ContentDisposition, "filename=\"${value.name}\""))
    }

    private fun Any.toJson(): String = MAPPER.writeValueAsString(this)

    override fun stop(): Unit = client.close()

    //endregion make requests

    override suspend fun getUpdates(
        offset: Int?, limit: Int?, timeout: Int?, allowedUpdates: Iterable<AllowedUpdate>?,
    ): List<Update> = postJson("getUpdates", UpdateRequest(offset, limit, timeout, allowedUpdates))

    override suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?,
    ): Boolean = postMultiPart("setWebhook") {
        append("url", url)
        appendContentIfNotNull("certificate", certificate)
        appendIfNotNull("ip_address", ipAddress)
        appendIfNotNull("max_connections", maxConnections)
        appendIfNotNull("allowed_updates", allowedUpdates?.toJson())
        appendIfNotNull("drop_pending_updates", dropPendingUpdates)
        appendIfNotNull("secret_token", secretToken)
    }

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean = get("deleteWebhook") {
        parameter("drop_pending_updates", dropPendingUpdates)
    }

    override suspend fun getWebhookInfo(): WebhookInfo = get("getWebhookInfo")

    override suspend fun getMe(): User = get("getMe")

    override suspend fun logOut(): Boolean = get("logOut")

    override suspend fun close(): Boolean = get("close")

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        messageThreadId: Long?,
        linkPreviewOptions: LinkPreviewOptions?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendMessage, Message>(
        "sendMessage", SendMessage(
            chatId = chatId,
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
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "TEXT", text = text) }

    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): Message = postJson(
        "forwardMessage", ForwardMessage(
            chatId = chatId,
            fromChatId = fromChatId,
            messageId = messageId,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
        )
    )

    override suspend fun forwardMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
    ): List<MessageId> = postJson(
        "forwardMessages", ForwardMessages(
            chatId = chatId,
            fromChatId = fromChatId,
            messageIds = messageIds,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
        )
    )

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
    ): MessageId = postJson(
        "copyMessage", CopyMessage(
            chatId = chatId,
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
    )

    override suspend fun copyMessages(
        chatId: String,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        removeCaption: Boolean?,
    ): List<MessageId> = postJson(
        "copyMessages", CopyMessages(
            chatId = chatId,
            fromChatId = fromChatId,
            messageIds = messageIds,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            removeCaption = removeCaption,
        )
    )

    override suspend fun sendPhoto(
        chatId: String,
        photo: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendPhoto") {
        append("chat_id", chatId)
        appendContent("photo", photo)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "PHOTO", text = caption) }

    override suspend fun sendPhoto(
        chatId: String,
        photo: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendPhoto") {
        append("chat_id", chatId)
        append("photo", photo)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "PHOTO", text = caption) }

    override suspend fun sendAudio(
        chatId: String,
        audio: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendAudio") {
        append("chat_id", chatId)
        appendContent("audio", audio)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("performer", performer)
        appendIfNotNull("title", title)
        appendThumbnailIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "AUDIO", text = caption) }

    override suspend fun sendAudio(
        chatId: String,
        audio: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendAudio") {
        append("chat_id", chatId)
        append("audio", audio)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("performer", performer)
        appendIfNotNull("title", title)
        appendThumbnailIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "AUDIO", text = caption) }

    override suspend fun sendDocument(
        chatId: String,
        document: NamedContent,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendDocument") {
        append("chat_id", chatId)
        appendContent("document", document)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "DOCUMENT", text = caption) }

    override suspend fun sendDocument(
        chatId: String,
        document: String,
        thumbnail: Content?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendDocument") {
        append("chat_id", chatId)
        append("document", document)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("disable_content_type_detection", disableContentTypeDetection)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "DOCUMENT", text = caption) }

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
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVideo") {
        append("chat_id", chatId)
        appendContent("video", video)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("supports_streaming", supportsStreaming)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VIDEO", text = caption) }

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
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        supportsStreaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVideo") {
        append("chat_id", chatId)
        append("video", video)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("supports_streaming", supportsStreaming)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VIDEO", text = caption) }

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
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendAnimation") {
        append("chat_id", chatId)
        appendContent("animation", animation)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "ANIMATION", text = caption) }

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
        messageThreadId: Long?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendAnimation") {
        append("chat_id", chatId)
        append("animation", animation)
        appendIfNotNull("duration", duration)
        appendIfNotNull("width", width)
        appendIfNotNull("height", height)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("has_spoiler", hasSpoiler)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "ANIMATION", text = caption) }

    override suspend fun sendVoice(
        chatId: String,
        voice: Content,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVoice") {
        append("chat_id", chatId)
        appendContent("voice", voice)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VOICE", text = caption) }

    override suspend fun sendVoice(
        chatId: String,
        voice: String,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        messageThreadId: Long?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVoice") {
        append("chat_id", chatId)
        append("voice", voice)
        appendIfNotNull("caption", caption)
        appendIfNotNull("parse_mode", parseMode?.toString())
        appendIfNotNull("caption_entities", captionEntities?.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VOICE", text = caption) }

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: Content,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVideoNote") {
        append("chat_id", chatId)
        appendContent("video_note", videoNote)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("length", length)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VIDEO_NOTE") }

    override suspend fun sendVideoNote(
        chatId: String,
        videoNote: String,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumbnail: Content?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendVideoNote") {
        append("chat_id", chatId)
        append("video_note", videoNote)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("duration", duration)
        appendIfNotNull("length", length)
        appendContentIfNotNull("thumbnail", thumbnail)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VIDEO_NOTE") }

    override suspend fun sendMediaGroup(
        chatId: String,
        media: Iterable<InputMedia>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
    ): ArrayList<Message> = postMultiPart("sendMediaGroup") {
        append("chat_id", chatId)
        append("media", media.toJson())
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())

        media.forEach { input ->
            appendContentIfNotNull(input.mediaContent)
            appendContentIfNotNull(input.thumbnailContent)
        }
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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendLocation, Message>(
        "sendLocation",
        SendLocation(
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
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "LOCATION", text = "latitude = $latitude, longitude = $longitude") }

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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendVenue, Message>(
        "sendVenue",
        SendVenue(
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
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "VENUE", text = "latitude = $latitude, longitude = $longitude, title = $title, address = $address") }

    override suspend fun sendContact(
        chatId: String,
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long?,
        lastName: String?,
        vcard: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendContact, Message>(
        "sendContact",
        SendContact(
            chatId = chatId,
            phone = phoneNumber,
            firstName = firstName,
            messageThreadId = messageThreadId,
            lastName = lastName,
            vcard = vcard,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "CONTACT", text = "phoneNumber = $phoneNumber, firstName = $firstName") }

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
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendPoll, Message>(
        "sendPoll",
        SendPoll(
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
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "POLL", text = question) }

    override suspend fun sendDice(
        chatId: String,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postJson<SendDice, Message>(
        "sendDice",
        SendDice(
            chatId = chatId,
            messageThreadId = messageThreadId,
            emoji = emoji,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "DICE", text = emoji) }

    override suspend fun sendChatAction(chatId: String, action: Action, messageThreadId: Long?): Boolean = postJson(
        "sendChatAction", SendChatAction(chatId, action, messageThreadId)
    )

    override suspend fun setMessageReaction(
        chatId: String,
        messageId: Long,
        reaction: Iterable<ReactionType>?,
        isBig: Boolean?,
    ): Boolean = postJson("setMessageReaction", SetMessageReaction(chatId, messageId, reaction, isBig))

    override suspend fun getUserProfilePhotos(userId: Long, offset: Long?, limit: Long?): UserProfilePhotos = get("getUserProfilePhotos") {
        parameter("user_id", userId)
        parameter("offset", offset)
        parameter("limit", limit)
    }

    override suspend fun getFile(fileId: String): File = get("getFile") {
        parameter("file_id", fileId)
    }

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?,
    ): Boolean = postJson("banChatMember", BanChatMember(chatId, userId, untilDate, revokeMessages))

    override suspend fun unbanChatMember(chatId: String, userId: Long, onlyIfBanned: Boolean?): Boolean = postJson(
        "unbanChatMember",
        UnbanChatMember(chatId, userId, onlyIfBanned)
    )

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?,
    ): Boolean = postJson(
        "restrictChatMember",
        RestrictChatMember(chatId, userId, permissions, useIndependentChatPermissions, untilDate)
    )

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
    ): Boolean = postJson(
        "promoteChatMember",
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
            canPostMessages = canPostMessages,
            canEditMessages = canEditMessages,
            canPinMessages = canPinMessages,
            canPostStories = canPostStories,
            canEditStories = canEditStories,
            canDeleteStories = canDeleteStories,
            canManageTopics = canManageTopics
        )
    )

    override suspend fun setChatAdministratorCustomTitle(chatId: String, userId: Long, customTitle: String): Boolean = postJson(
        "setChatAdministratorCustomTitle", SetChatAdministratorCustomTitle(chatId, userId, customTitle)
    )

    override suspend fun banChatSenderChat(chatId: String, senderChatId: Long): Boolean = postJson(
        "banChatSenderChat", BanChatSenderChat(chatId, senderChatId)
    )

    override suspend fun unbanChatSenderChat(chatId: String, senderChatId: Long): Boolean = postJson(
        "unbanChatSenderChat", UnbanChatSenderChat(chatId, senderChatId)
    )

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
    ): Boolean = postJson("setChatPermissions", SetChatPermissions(chatId, permissions, useIndependentChatPermissions))

    override suspend fun exportChatInviteLink(chatId: String): String = postJson(
        "exportChatInviteLink", ExportChatInviteLink(chatId)
    )

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = postJson(
        "createChatInviteLink", CreateChatInviteLink(chatId, name, expireDate, memberLimit, createsJoinRequest)
    )

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?,
    ): ChatInviteLink = postJson(
        "editChatInviteLink", EditChatInviteLink(chatId, inviteLink, name, expireDate, memberLimit, createsJoinRequest)
    )

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink = postJson(
        "revokeChatInviteLink", RevokeChatInviteLink(chatId, inviteLink)
    )

    override suspend fun approveChatJoinRequest(chatId: String, userId: Long): Boolean = postJson(
        "approveChatJoinRequest", ApproveChatJoinRequest(chatId, userId)
    )

    override suspend fun declineChatJoinRequest(chatId: String, userId: Long): Boolean = postJson(
        "declineChatJoinRequest", DeclineChatJoinRequest(chatId, userId)
    )

    override suspend fun setChatPhoto(chatId: String, photo: Content): Boolean = postMultiPart("setChatPhoto") {
        append("chat_id", chatId)
        appendContent("photo", photo)
    }

    override suspend fun setChatPhoto(chatId: String, photo: String): Boolean = postMultiPart("setChatPhoto") {
        append("chat_id", chatId)
        append("photo", photo)
    }

    override suspend fun deleteChatPhoto(chatId: String): Boolean = postJson(
        "deleteChatPhoto", DeleteChatPhoto(chatId)
    )

    override suspend fun setChatTitle(chatId: String, title: String): Boolean = postJson(
        "setChatTitle", SetChatTitle(chatId, title)
    )

    override suspend fun setChatDescription(chatId: String, description: String): Boolean = postJson(
        "setChatDescription", SetChatDescription(chatId, description)
    )

    override suspend fun pinChatMessage(chatId: String, messageId: Long, disableNotification: Boolean?): Boolean = postJson(
        "pinChatMessage", PinChatMessage(chatId, messageId, disableNotification)
    )

    override suspend fun unpinChatMessage(chatId: String, messageId: Long?): Boolean = postJson(
        "unpinChatMessage", UnpinChatMessage(chatId, messageId)
    )

    override suspend fun unpinAllChatMessages(chatId: String): Boolean = postJson(
        "unpinAllChatMessages", UnpinAllChatMessages(chatId)
    )

    override suspend fun leaveChat(chatId: String): Boolean = postJson(
        "leaveChat", LeaveChat(chatId)
    )

    override suspend fun getChat(chatId: String): Chat = get("getChat") {
        parameter("chat_id", chatId)
    }

    override suspend fun getChatAdministrators(chatId: String): ArrayList<ChatMember> = get("getChatAdministrators") {
        parameter("chat_id", chatId)
    }

    override suspend fun getChatMemberCount(chatId: String): Long = get("getChatMemberCount") {
        parameter("chat_id", chatId)
    }

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember = get("getChatMember") {
        parameter("chat_id", chatId)
        parameter("user_id", userId)
    }

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean = postJson(
        "setChatStickerSet", SetChatStickerSet(chatId, stickerSetName)
    )

    override suspend fun deleteChatStickerSet(chatId: String): Boolean = postJson(
        "deleteChatStickerSet", DeleteChatStickerSet(chatId)
    )

    override suspend fun getForumTopicIconStickers(): List<Sticker> = get("getForumTopicIconStickers")

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?,
    ): ForumTopic = postJson("createForumTopic", CreateForumTopic(chatId, name, iconColor, iconCustomEmojiId))

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?,
    ): Boolean = postJson("editForumTopic", EditForumTopic(chatId, messageThreadId, name, iconCustomEmojiId))

    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean = postJson(
        "closeForumTopic", CloseForumTopic(chatId, messageThreadId)
    )

    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean = postJson(
        "reopenForumTopic", ReopenForumTopic(chatId, messageThreadId)
    )

    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean = postJson(
        "deleteForumTopic", DeleteForumTopic(chatId, messageThreadId)
    )

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean = postJson(
        "unpinAllForumTopicMessages", UnpinAllForumTopicMessages(chatId, messageThreadId)
    )

    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean = postJson(
        "editGeneralForumTopic", EditGeneralForumTopic(chatId, name)
    )

    override suspend fun closeGeneralForumTopic(chatId: String): Boolean = postJson(
        "closeGeneralForumTopic", CloseGeneralForumTopic(chatId)
    )

    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean = postJson(
        "reopenGeneralForumTopic", ReopenGeneralForumTopic(chatId)
    )

    override suspend fun hideGeneralForumTopic(chatId: String): Boolean = postJson(
        "hideGeneralForumTopic", HideGeneralForumTopic(chatId)
    )

    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean = postJson(
        "unhideGeneralForumTopic", UnhideGeneralForumTopic(chatId)
    )

    override suspend fun unpinAllGeneralForumTopicMessages(chatId: String): Boolean = postJson(
        "unpinAllGeneralForumTopicMessages", UnpinAllGeneralForumTopicMessages(chatId)
    )

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Long?,
    ): Boolean = postJson("answerCallbackQuery", AnswerCallbackQuery(callbackQueryId, text, showAlert, url, cacheTime))

    override suspend fun getUserChatBoosts(
        chatId: String,
        userId: Long,
    ): UserChatBoosts = get("getUserChatBoosts") {
        parameter("chat_id", chatId)
        parameter("user_id", userId)
    }

    override suspend fun setMyCommands(
        commands: List<BotCommand>, scope: BotCommandScope?, languageCode: String?,
    ): Boolean = postJson("setMyCommands", SetMyCommands(commands, scope, languageCode))

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
        postJson("deleteMyCommands", DeleteMyCommands(scope, languageCode))

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?): List<BotCommand> =
        postJson("getMyCommands", GetMyCommands(scope, languageCode))

    override suspend fun setMyName(name: String?, languageCode: String?): Boolean =
        postJson("setMyName", SetMyName(name, languageCode))

    override suspend fun getMyName(languageCode: String?): BotName = get("getMyName") {
        parameter("language_code", languageCode)
    }

    override suspend fun setMyDescription(description: String?, languageCode: String?): Boolean = postJson(
        "setMyDescription", SetMyDescription(description, languageCode)
    )

    override suspend fun getMyDescription(languageCode: String?): BotDescription = get("getMyDescription") {
        parameter("language_code", languageCode)
    }

    override suspend fun setMyShortDescription(shortDescription: String?, languageCode: String?): Boolean = postJson(
        "setMyShortDescription", SetMyShortDescription(shortDescription, languageCode)
    )

    override suspend fun getMyShortDescription(languageCode: String?): BotShortDescription = get("getMyShortDescription") {
        parameter("language_code", languageCode)
    }

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean = postJson(
        "setChatMenuButton", SetChatMenuButton(chatId, menuButton)
    )

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton = get("getChatMenuButton") {
        parameter("chat_id", chatId)
    }

    override suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?,
    ): Boolean = postJson("setMyDefaultAdministratorRights", SetMyDefaultAdministratorRights(rights, forChannels))

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?): ChatAdministratorRights = get("getMyDefaultAdministratorRights") {
        parameter("for_channels", forChannels)
    }

    override suspend fun editMessageText(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        linkPreviewOptions: LinkPreviewOptions?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postJson<EditMessageText, Message>(
        "editMessageText",
        EditMessageText(
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            text = text,
            parseMode = parseMode,
            entities = entities,
            linkPreviewOptions = linkPreviewOptions,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "EDIT_TEXT", text = text) }

    override suspend fun editMessageCaption(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postJson<EditMessageCaption, Message>(
        "editMessageCaption",
        EditMessageCaption(
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            caption = caption,
            parseMode = parseMode,
            captionEntities = captionEntities,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "EDIT_CAPTION", text = caption) }

    override suspend fun editMessageMedia(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postMultiPart<Message>("editMessageMedia") {
        appendIfNotNull("chat_id", chatId)
        appendIfNotNull("message_id", messageId)
        appendIfNotNull("inline_message_id", inlineMessageId)
        append("media", media.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "EDIT_MEDIA", media.media) }

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
    ): Message = postJson<EditMessageLiveLocation, Message>(
        "editMessageLiveLocation", EditMessageLiveLocation(
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            latitude = latitude,
            longitude = longitude,
            horizontalAccuracy = horizontalAccuracy,
            heading = heading,
            proximityAlertRadius = proximityAlertRadius,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "EDIT_LIVE_LOCATION", text = "latitude = $latitude, longitude = $longitude") }

    override suspend fun stopMessageLiveLocation(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postJson<StopMessageLiveLocation, Message>(
        "stopMessageLiveLocation",
        StopMessageLiveLocation(
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "STOP_LIVE_LOCATION") }

    override suspend fun editMessageReplyMarkup(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postJson<EditMessageReplyMarkup, Message>(
        "editMessageReplyMarkup",
        EditMessageReplyMarkup(
            chatId,
            messageId,
            inlineMessageId,
            replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "EDIT_REPLY_MARKUP") }

    override suspend fun stopPoll(chatId: String, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll = postJson(
        "stopPoll", StopPoll(chatId, messageId, replyMarkup)
    )

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean = postJson(
        "deleteMessage", DeleteMessage(chatId, messageId)
    )

    override suspend fun deleteMessages(chatId: String, messageIds: Iterable<Long>): Boolean = postJson(
        "deleteMessages", DeleteMessages(chatId, messageIds)
    )

    override suspend fun sendSticker(
        chatId: String,
        sticker: Content,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: ReplyKeyboard?,
    ): Message = postMultiPart<Message>("sendSticker") {
        append("chat_id", chatId)
        appendContent("sticker", sticker)
        appendIfNotNull("message_thread_id", messageThreadId)
        appendIfNotNull("emoji", emoji)
        appendIfNotNull("disable_notification", disableNotification)
        appendIfNotNull("protect_content", protectContent)
        appendIfNotNull("reply_parameters", replyParameters?.toJson())
        appendIfNotNull("reply_markup", replyMarkup?.toJson())
    }.also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "STICKER", text = emoji) }

    override suspend fun getStickerSet(name: String): StickerSet = get("getStickerSet") {
        parameter("name", name)
    }

    override suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker> = postJson(
        "getCustomEmojiStickers", GetCustomEmojiStickers(customEmojiIds)
    )

    override suspend fun uploadStickerFile(userId: Long, sticker: Content, stickerFormat: String): File = postMultiPart("uploadStickerFile") {
        append("user_id", userId)
        appendContent("sticker", sticker)
        append("sticker_format", stickerFormat)
    }

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerFormat: String,
        stickerType: String?,
        needsRepainting: Boolean?,
    ): Boolean = postMultiPart("createNewStickerSet") {
        append("user_id", userId)
        append("name", name)
        append("title", title)
        append("stickers", stickers.toJson())
        append("sticker_format", stickerFormat)
        appendIfNotNull("sticker_type", stickerType)
        appendIfNotNull("needs_repainting", needsRepainting)

        stickers.forEach { sticker ->
            appendContentIfNotNull(sticker.stickerContent)
        }
    }

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean = postMultiPart("addStickerToSet") {
        append("user_id", userId)
        append("name", name)
        append("sticker", sticker.toJson())

        appendContentIfNotNull(sticker.stickerContent)
    }

    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean = postJson(
        "setStickerPositionInSet", SetStickerPositionInSet(sticker, position)
    )

    override suspend fun deleteStickerFromSet(sticker: String): Boolean = postJson(
        "deleteStickerFromSet", DeleteStickerFromSet(sticker)
    )

    override suspend fun setStickerEmojiList(sticker: String, emojiList: Iterable<String>): Boolean = postJson(
        "setStickerEmojiList", SetStickerEmojiList(sticker, emojiList)
    )

    override suspend fun setStickerKeywords(sticker: String, keywords: Iterable<String>?): Boolean = postJson(
        "setStickerKeywords", SetStickerKeywords(sticker, keywords)
    )

    override suspend fun setStickerMaskPosition(sticker: String, maskPosition: MaskPosition?): Boolean = postJson(
        "setStickerMaskPosition", SetStickerMaskPosition(sticker, maskPosition)
    )

    override suspend fun setStickerSetTitle(sticker: String, title: String): Boolean = postJson(
        "setStickerSetTitle", SetStickerSetTitle(sticker, title)
    )

    override suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: Content?,
    ): Boolean = postMultiPart("setStickerSetThumbnail") {
        append("name", name)
        append("user_id", userId)

        appendContentIfNotNull("thumbnail", thumbnail)
    }

    override suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String?): Boolean = postJson(
        "setCustomEmojiStickerSetThumbnail", SetCustomEmojiStickerSetThumbnail(name, customEmojiId)
    )

    override suspend fun deleteStickerSet(name: String): Boolean = postJson(
        "deleteStickerSet", DeleteStickerSet(name)
    )

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        button: InlineQueryResultsButton?,
    ): Boolean = postJson("answerInlineQuery", AnswerInlineQuery(inlineQueryId, results, cacheTime, isPersonal, nextOffset, button))

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): SentWebAppMessage = postJson(
        "answerWebAppQuery", AnswerWebAppQuery(webAppQueryId, result)
    )

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
    ): Message = postJson<SendInvoice, Message>(
        "sendInvoice",
        SendInvoice(
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
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "INVOICE", text = title) }

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
    ): String = postJson(
        "createInvoiceLink",
        CreateInvoiceLink(
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
    )

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?,
    ): Boolean = postJson("answerShippingQuery", AnswerShippingQuery(shippingQueryId, ok, shippingOptions, errorMessage))

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?,
    ): Boolean = postJson("answerPreCheckoutQuery", AnswerPreCheckoutQuery(preCheckoutQueryId, ok, errorMessage))

    override suspend fun setPassportDataErrors(userId: Long, errors: List<PassportElementError>): Boolean = postJson(
        "setPassportDataErrors", SetPassportDataErrors(userId, errors)
    )

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyParameters: ReplyParameters?,
        replyMarkup: InlineKeyboardMarkup?,
    ): Message = postJson<SendGame, Message>(
        "sendGame",
        SendGame(
            chatId = chatId,
            gameShortName = gameShortName,
            messageThreadId = messageThreadId,
            disableNotification = disableNotification,
            protectContent = protectContent,
            replyParameters = replyParameters,
            replyMarkup = replyMarkup
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "GAME", text = gameShortName) }

    override suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): Message = postJson<SetGameScore, Message>(
        "setGameScore",
        SetGameScore(
            userId = userId,
            score = score,
            force = force,
            disableEditMessage = disableEditMessage,
            chatId = chatId,
            messageId = messageId,
            inlineMessageId = inlineMessageId
        )
    ).also { messageSource.save(it.chatId, it.from!!.id, it.messageId, type = "GAME_SCORE", text = "userId = $userId, score = $score") }

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?,
    ): List<GameHighScore> = get("getGameHighScores") {
        parameter("user_id", userId)
        parameter("chat_id", chatId)
        parameter("message_id", messageId)
        parameter("inline_message_id", inlineMessageId)
    }

    //region helpful features

    override suspend fun download(filePath: String): HttpResponse {
        return client.get("https://api.telegram.org/file/bot$token/${filePath}")
    }

    override suspend fun downloadById(fileId: String): HttpResponse {
        val fileInfo = getFile(fileId)
        val filePath = fileInfo.filePath ?: throw IllegalStateException("Failed download file. FilePath is null for file $fileInfo.")

        return download(filePath)
    }

    //endregion helpful features

    companion object {
        private val MAPPER = jacksonMapperBuilder().apply {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }.build().apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

            registerModule(JavaTimeModule())

            registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
        }
    }
}