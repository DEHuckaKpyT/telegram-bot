package io.github.dehuckakpyt.telegrambot.api

import com.dehucka.microservice.ext.mapperConfig
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.dehuckakpyt.telegrambot.exception.api.TelegramBotApiException
import io.github.dehuckakpyt.telegrambot.model.internal.*
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent
import io.github.dehuckakpyt.telegrambot.model.type.supplement.TelegramResponse
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
import kotlinx.coroutines.isActive
import java.io.File


/**
 * Created on 05.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramApiClient(
    token: String,
) : TelegramApi {

    //region Make requests
    private val mapper = ObjectMapper().apply {
        mapperConfig()
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            register(Json, JacksonConverter(mapper))
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

    val isActive = client.isActive

    private suspend inline fun <reified T : Any> get(method: String): T = handleRequest(client.get(method))

    private suspend inline fun <reified T> get(method: String, block: HttpRequestBuilder.() -> Unit): T =
        handleRequest(client.get(method, block))

    private suspend inline fun <reified T : Any> postForm(method: String, noinline block: FormBuilder.() -> Unit): T =
        handleRequest(client.post(method) { formData(block) })

    private suspend inline fun <reified T : Any, reified R : Any> postJson(method: String, body: T): R {
        val response = client.post(method) {
            contentType(Json)
            setBody(body)
        }
        val telegramResponse = response.body<TelegramResponse<R>>()

        if (!telegramResponse.ok) throwException(response, telegramResponse, body)

        return telegramResponse.result!!
    }

    private suspend inline fun <reified T : Any> handleRequest(response: HttpResponse): T {
        val telegramResponse = response.body<TelegramResponse<T>>()

        if (telegramResponse.ok) throwException(response, telegramResponse)

        return telegramResponse.result!!
    }

    private fun throwException(response: HttpResponse, telegramResponse: TelegramResponse<*>) =
        throwException(response, telegramResponse, null)

    private inline fun <reified T : Any> throwException(
        response: HttpResponse, telegramResponse: TelegramResponse<*>, body: T? = null
    ) {
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

    private fun FormBuilder.appendIfNotNull(key: String, value: Boolean?) {
        value ?: return
        append(key, value.toString())
    }

    private fun FormBuilder.appendIfNotNull(key: String, value: NamedContent?) {
        value ?: return
        append(key, value.byteArray, headersOf(ContentDisposition, "filename=\"${value.filaName}\""))
    }

    private fun Any.toJson(): String {
        return mapper.writeValueAsString(this)
    }

    fun stop() = client.close()

    //endregion Make requests

    override suspend fun getMe(): User = get("getMe")

    override suspend fun logOut(): Boolean = get("logOut")

    override suspend fun close(): Boolean = get("close")

    override suspend fun getUpdates(
        offset: Int?, limit: Int?, timeout: Int?, allowedUpdates: List<AllowedUpdate>?
    ): List<UpdateResponse> = postJson("getUpdates", UpdateRequest(offset, limit, timeout, allowedUpdates))

    override suspend fun setWebhook(
        url: String,
        certificate: NamedContent?,
        ipAddress: String?,
        maxConnections: Int?,
        allowedUpdates: List<AllowedUpdate>?,
        dropPendingUpdates: Boolean?,
        secretToken: String?
    ): Boolean = postForm("setWebhook") {
        append("url", url)
        appendIfNotNull("certificate", certificate)
        appendIfNotNull("ipAddress", ipAddress)
        appendIfNotNull("maxConnections", maxConnections)
        appendIfNotNull("allowedUpdates", allowedUpdates?.toJson())
        appendIfNotNull("dropPendingUpdates", dropPendingUpdates)
        appendIfNotNull("secretToken", secretToken)
    }

    override suspend fun deleteWebhook(dropPendingUpdates: Boolean?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getWebhookInfo(): WebhookInfo {
        TODO("Not yet implemented")
    }

    override suspend fun getMyCommands(scope: BotCommandScope?, languageCode: String?): List<BotCommand> =
        postJson("getMyCommands", GetMyCommands(scope, languageCode))

    override suspend fun setMyCommands(
        commands: List<BotCommand>, scope: BotCommandScope?, languageCode: String?
    ): Boolean = postJson("setMyCommands", SetMyCommands(commands, scope, languageCode))

    override suspend fun deleteMyCommands(scope: BotCommandScope?, languageCode: String?): Boolean =
        postJson("deleteMyCommands", DeleteMyCommands(scope, languageCode))

    override suspend fun sendMessage(
        chatId: String,
        text: String,
        messageThreadId: Long?,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message = postJson(
        "sendMessage", SendMessage(
            chatId,
            text,
            messageThreadId,
            parseMode,
            entities,
            disableWebPagePreview,
            disableNotification,
            protectContent,
            replyToMessageId
        )
    )


    override suspend fun forwardMessage(
        chatId: String,
        fromChatId: String,
        msgId: Long,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun copyMessage(
        chatId: String,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): MessageId {
        TODO("Not yet implemented")
    }

    override suspend fun sendPhoto(
        chatId: String,
        photo: NamedContent,
        messageThreadId: Long?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendAudio(
        chatId: String,
        audio: NamedContent,
        messageThreadId: Long?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        duration: Long?,
        performer: String?,
        title: String?,
        thumb: File?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendDocument(
        chatId: String,
        document: NamedContent,
        messageThreadId: Long?,
        thumb: File?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        disableContentTypeDetection: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendVideo(
        chatId: String,
        video: NamedContent,
        messageThreadId: Long?,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumb: File?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        streaming: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendAnimation(
        chatId: String,
        animation: NamedContent,
        messageThreadId: Long?,
        duration: Long?,
        width: Long?,
        height: Long?,
        thumb: File?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        hasSpoiler: Boolean?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendVoice(
        chatId: String,
        voice: NamedContent,
        messageThreadId: Long?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        duration: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendVideoNote(
        chatId: String,
        note: NamedContent,
        messageThreadId: Long?,
        duration: Long?,
        length: Long?,
        thumb: File?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendMediaGroup(
        chatId: String,
        media: List<InputMedia>,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?
    ): ArrayList<Message> {
        TODO("Not yet implemented")
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
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun editMessageLiveLocation(
        latitude: Float,
        longitude: Float,
        horizontalAccuracy: Float?,
        heading: Long?,
        proximityAlertRadius: Long?,
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun stopMessageLiveLocation(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

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
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendContact(
        chatId: String,
        phoneNumber: String,
        firstName: String,
        messageThreadId: Long?,
        lastName: String?,
        vcard: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

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
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendDice(
        chatId: String,
        messageThreadId: Long?,
        emoji: String?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun sendChatAction(chatId: String, action: Action, messageThreadId: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun banChatSenderChat(chatId: String, senderString: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unbanChatSenderChat(chatId: String, senderString: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfilePhotos(userId: Long, offset: Long?, limit: Long?): UserProfilePhotos {
        TODO("Not yet implemented")
    }

    override suspend fun getFile(fileId: String): io.github.dehuckakpyt.telegrambot.model.type.File {
        TODO("Not yet implemented")
    }

    override suspend fun banChatMember(
        chatId: String,
        userId: Long,
        untilDate: Long?,
        revokeMessages: Boolean?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unbanChatMember(chatId: String, userId: Long, onlyIfBanned: Boolean?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun restrictChatMember(
        chatId: String,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?,
        untilDate: Long?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun promoteChatMember(
        chatId: String,
        userId: Long,
        isAnonymous: Boolean?,
        canManageChat: Boolean?,
        canPostMessages: Boolean?,
        canEditMessages: Boolean?,
        canDeleteMessages: Boolean?,
        canManageVideoChats: Boolean?,
        canRestrictMembers: Boolean?,
        canPromoteMembers: Boolean?,
        canChangeInfo: Boolean?,
        canInviteUsers: Boolean?,
        canPinMessages: Boolean?,
        canManageTopics: Boolean?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setChatAdministratorCustomTitle(chatId: String, userId: Long, customTitle: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setChatPermissions(
        chatId: String,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createChatInviteLink(
        chatId: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?
    ): ChatInviteLink {
        TODO("Not yet implemented")
    }

    override suspend fun editChatInviteLink(
        chatId: String,
        inviteLink: String,
        name: String?,
        expireDate: Long?,
        memberLimit: Long?,
        createsJoinRequest: Boolean?
    ): ChatInviteLink {
        TODO("Not yet implemented")
    }

    override suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): ChatInviteLink {
        TODO("Not yet implemented")
    }

    override suspend fun approveChatJoinRequest(chatId: String, inviteLink: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun declineChatJoinRequest(chatId: String, inviteLink: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun exportChatInviteLink(chatId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun setChatPhoto(chatId: String, photo: Any): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChatPhoto(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setChatTitle(chatId: String, title: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setChatDescription(chatId: String, description: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun pinChatMessage(chatId: String, messageId: Long, disableNotification: Boolean?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unpinChatMessage(chatId: String, messageId: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unpinAllChatMessages(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun leaveChat(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(chatId: String): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun getChatAdministrators(chatId: String): ArrayList<ChatMember> {
        TODO("Not yet implemented")
    }

    override suspend fun getChatMemberCount(chatId: String): Long {
        TODO("Not yet implemented")
    }

    override suspend fun getChatMember(chatId: String, userId: Long): ChatMember {
        TODO("Not yet implemented")
    }

    override suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChatStickerSet(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun answerCallbackQuery(
        callbackQueryId: String,
        text: String?,
        showAlert: Boolean?,
        url: String?,
        cacheTime: Long?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun answerInlineQuery(
        inlineQueryId: String,
        results: List<InlineQueryResult>,
        cacheTime: Int?,
        isPersonal: Boolean?,
        nextOffset: String?,
        switchPmText: String?,
        switchPmParameter: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun answerWebAppQuery(webAppQueryId: String, result: InlineQueryResult): SentWebAppMessage {
        TODO("Not yet implemented")
    }

    override suspend fun editMessageText(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        text: String,
        parseMode: ParseMode?,
        entities: List<MessageEntity>?,
        disableWebPagePreview: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun editMessageCaption(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        caption: String?,
        parseMode: ParseMode?,
        captionEntities: List<MessageEntity>?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun editMessageMedia(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun editMessageReplyMarkup(
        chatId: String?,
        messageId: Long?,
        inlineMessageId: String?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun stopPoll(chatId: String, messageId: Long, replyMarkup: InlineKeyboardMarkup?): Poll {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(chatId: String, messageId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun sendSticker(
        chatId: String,
        sticker: Any,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: ReplyKeyboard?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun getStickerSet(name: String): StickerSet {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadStickerFile(
        userId: Long,
        pngSticker: File
    ): io.github.dehuckakpyt.telegrambot.model.type.File {
        TODO("Not yet implemented")
    }

    override suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        emojis: String,
        pngSticker: Any?,
        tgsSticker: File?,
        webmSticker: File?,
        stickerType: String?,
        maskPosition: MaskPosition?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addStickerToSet(
        userId: Long,
        name: String,
        emojis: String,
        pngSticker: Any?,
        tgsSticker: File?,
        webmSticker: File?,
        maskPosition: MaskPosition?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setStickerPositionInSet(sticker: String, position: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStickerFromSet(sticker: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setStickerSetThumb(name: String, userId: Long, thumb: Any?): Boolean {
        TODO("Not yet implemented")
    }

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
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

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
        isFlexible: Boolean?
    ): String {
        TODO("Not yet implemented")
    }

    override suspend fun answerShippingQuery(
        shippingQueryId: String,
        ok: Boolean,
        shippingOptions: List<ShippingOption>?,
        errorMessage: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setPassportDataErrors(userId: Long, errors: List<PassportElementError>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun sendGame(
        chatId: Long,
        gameShortName: String,
        messageThreadId: Long?,
        disableNotification: Boolean?,
        protectContent: Boolean?,
        replyToMessageId: Long?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: InlineKeyboardMarkup?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun setGameScore(
        userId: Long,
        score: Long,
        force: Boolean?,
        disableEditMessage: Boolean?,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?
    ): Message {
        TODO("Not yet implemented")
    }

    override suspend fun getGameHighScores(
        userId: Long,
        chatId: Long?,
        messageId: Long?,
        inlineMessageId: String?
    ): List<GameHighScore> {
        TODO("Not yet implemented")
    }

    override suspend fun setChatMenuButton(chatId: Long?, menuButton: MenuButton?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getChatMenuButton(chatId: Long?): MenuButton {
        TODO("Not yet implemented")
    }

    override suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights?,
        forChannels: Boolean?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getMyDefaultAdministratorRights(forChannels: Boolean?): ChatAdministratorRights {
        TODO("Not yet implemented")
    }

    override suspend fun getForumTopicIconStickers(): List<Sticker> {
        TODO("Not yet implemented")
    }

    override suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int?,
        iconCustomEmojiId: String?
    ): ForumTopic {
        TODO("Not yet implemented")
    }

    override suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String?,
        iconCustomEmojiId: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun closeForumTopic(chatId: String, messageThreadId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun reopenForumTopic(chatId: String, messageThreadId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteForumTopic(chatId: String, messageThreadId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun editGeneralForumTopic(chatId: String, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun closeGeneralForumTopic(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun reopenGeneralForumTopic(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun hideGeneralForumTopic(chatId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun unhideGeneralForumTopic(chatId: String): Boolean {
        TODO("Not yet implemented")
    }
}