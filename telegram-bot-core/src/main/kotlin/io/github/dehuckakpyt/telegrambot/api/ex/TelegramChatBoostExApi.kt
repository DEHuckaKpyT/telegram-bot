package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramChatBoostApi
import io.github.dehuckakpyt.telegrambot.model.type.UserChatBoosts


/**
 * Created on 12.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramChatBoostExApi : TelegramChatBoostApi {

    suspend fun getUserChatBoosts(
        chatId: Long,
        userId: Long,
    ): UserChatBoosts = getUserChatBoosts(
        chatId = chatId.toString(),
        userId = userId
    )
}