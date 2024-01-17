package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramStickerApi
import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.model.type.ReplyKeyboard
import io.github.dehuckakpyt.telegrambot.model.type.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.type.supplement.NamedContent


/**
 * Created on 19.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramStickerExApi : TelegramStickerApi {
    suspend fun sendSticker(
        chatId: Long,
        sticker: NamedContent,
        messageThreadId: Long? = null,
        emoji: String?,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyKeyboard? = null,
    ): Message = sendSticker(
        chatId = chatId.toString(),
        sticker = sticker,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
}