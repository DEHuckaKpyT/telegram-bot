package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramStickerApi {
    suspend fun sendSticker(
        chatId: String,
        sticker: NamedContent,
        messageThreadId: Long? = null,
        emoji: String?,
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
        sticker: NamedContent,
        stickerFormat: String
    ): File

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Collection<Any>,
        stickerFormat: String,
        stickerType: String? = null,
        needsRepainting: Boolean? = null,
    ): Boolean

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: Any
    ): Boolean

    suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int
    ): Boolean

    suspend fun deleteStickerFromSet(sticker: String): Boolean

    suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: Any? = null
    ): Boolean
}