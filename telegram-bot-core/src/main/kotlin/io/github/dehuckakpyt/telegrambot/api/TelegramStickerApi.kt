package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.*


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramStickerApi {
    suspend fun sendSticker(
        chatId: String,
        sticker: Any,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyToMessageId: Long? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: ReplyKeyboard? = null
    ): Message

    suspend fun getStickerSet(name: String): StickerSet

    suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker>

    suspend fun uploadStickerFile(
        userId: Long,
        pngSticker: java.io.File
    ): File

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        emojis: String,
        pngSticker: Any? = null,
        tgsSticker: java.io.File? = null,
        webmSticker: java.io.File? = null,
        stickerType: String? = null,
        maskPosition: MaskPosition? = null
    ): Boolean

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        emojis: String,
        pngSticker: Any? = null,
        tgsSticker: java.io.File? = null,
        webmSticker: java.io.File? = null,
        maskPosition: MaskPosition? = null
    ): Boolean

    suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int
    ): Boolean

    suspend fun deleteStickerFromSet(sticker: String): Boolean

    suspend fun setStickerSetThumb(
        name: String,
        userId: Long,
        thumb: Any? = null
    ): Boolean
}