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
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message

    suspend fun getStickerSet(name: String): StickerSet

    suspend fun getCustomEmojiStickers(customEmojiIds: List<String>): List<Sticker>

    suspend fun uploadStickerFile(
        userId: Long,
        sticker: NamedContent,
        stickerFormat: String,
    ): File

    suspend fun createNewStickerSet(
        userId: Long,
        name: String,
        title: String,
        stickers: Iterable<InputSticker>,
        stickerFormat: String,
        stickerType: String? = null,
        needsRepainting: Boolean? = null,
    ): Boolean

    suspend fun addStickerToSet(
        userId: Long,
        name: String,
        sticker: InputSticker,
    ): Boolean

    suspend fun setStickerPositionInSet(
        sticker: String,
        position: Int,
    ): Boolean

    suspend fun deleteStickerFromSet(sticker: String): Boolean

    suspend fun setStickerEmojiList(
        sticker: String,
        emojiList: Iterable<String>,
    ): Boolean

    suspend fun setStickerKeywords(
        sticker: String,
        keywords: Iterable<String>? = null,
    ): Boolean

    suspend fun setStickerMaskPosition(
        sticker: String,
        maskPosition: MaskPosition? = null,
    ): Boolean

    suspend fun setStickerSetTitle(
        sticker: String,
        title: String,
    ): Boolean

    suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        thumbnail: NamedContent? = null,
    ): Boolean

    suspend fun setCustomEmojiStickerSetThumbnail(
        name: String,
        customEmojiId: String? = null,
    ): Boolean

    suspend fun deleteStickerSet(name: String): Boolean
}