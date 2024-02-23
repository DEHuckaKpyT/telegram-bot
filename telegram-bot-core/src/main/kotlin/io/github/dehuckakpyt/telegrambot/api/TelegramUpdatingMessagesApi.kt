package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.*


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramUpdatingMessagesApi {

    suspend fun editMessageText(
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message

    suspend fun editMessageCaption(
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message

    suspend fun editMessageMedia(
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message

    suspend fun editMessageReplyMarkup(
        chatId: String? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message

    suspend fun stopPoll(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll

    suspend fun deleteMessage(
        chatId: String,
        messageId: Long,
    ): Boolean

    suspend fun deleteMessages(
        chatId: String,
        messageIds: Iterable<Long>,
    ): Boolean
}