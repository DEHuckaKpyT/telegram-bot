package io.github.dehuckakpyt.telegrambot.ext

import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message


/**
 * Created on 23.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val Message.chatId: Long
    get() = chat.id
val CallbackQuery.chatId: Long
    get() = message?.chat?.id ?: from.id

