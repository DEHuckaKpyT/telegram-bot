package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.UserChatBoosts


/**
 * Created on 12.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramChatBoostApi {

    suspend fun getUserChatBoosts(
        chatId: String,
        userId: Long,
    ): UserChatBoosts
}