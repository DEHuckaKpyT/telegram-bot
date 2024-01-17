package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramUpdatingMessagesApi
import io.github.dehuckakpyt.telegrambot.model.type.*


/**
 * Created on 18.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramUpdatingMessagesExApi : TelegramUpdatingMessagesApi {

    suspend fun editMessageText(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageText(
        chatId = chatId?.toString(),
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageText(
        chatId: String,
        messageId: Long,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageText(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageText(
        chatId: Long,
        messageId: Long,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageText(
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = null,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageText(
        inlineMessageId: String,
        text: String,
        parseMode: ParseMode? = null,
        entities: List<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageText(
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageCaption(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageCaption(
        chatId = chatId?.toString(),
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageCaption(
        chatId: String,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageCaption(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageCaption(
        chatId: Long,
        messageId: Long,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageCaption(
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = null,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageCaption(
        inlineMessageId: String,
        caption: String? = null,
        parseMode: ParseMode? = null,
        captionEntities: List<MessageEntity>? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageCaption(
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageMedia(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageMedia(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageMedia(
        chatId: String,
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageMedia(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageMedia(
        chatId: Long,
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageMedia(
        chatId = chatId.toString(),
        messageId = messageId,
        inlineMessageId = null,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageMedia(
        inlineMessageId: String,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageMedia(
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        media = media,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageReplyMarkup(
        chatId: Long? = null,
        messageId: Long? = null,
        inlineMessageId: String? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageReplyMarkup(
        chatId: String,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageReplyMarkup(
        chatId: Long,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageReplyMarkup(
        chatId = chatId,
        messageId = messageId,
        inlineMessageId = null,
        replyMarkup = replyMarkup
    )

    suspend fun editMessageReplyMarkup(
        inlineMessageId: String,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageReplyMarkup(
        chatId = null as String?,
        messageId = null,
        inlineMessageId = inlineMessageId,
        replyMarkup = replyMarkup
    )

    suspend fun stopPoll(
        chatId: Long,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll = stopPoll(
        chatId = chatId.toString(),
        messageId = messageId,
        replyMarkup = replyMarkup
    )

    suspend fun deleteMessage(
        chatId: Long,
        messageId: Long,
    ): Boolean = deleteMessage(
        chatId = chatId.toString(),
        messageId = messageId
    )

    suspend fun deleteMessages(
        chatId: Long,
        messageIds: Iterable<Long>,
    ): Boolean = deleteMessages(
        chatId = chatId.toString(),
        messageIds = messageIds
    )
}