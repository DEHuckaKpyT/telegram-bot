package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat


/**
 * @author Denis Matytsin
 */
interface TelegramChatSource<EntityT : TelegramChat> {

    public suspend fun save(chat: Chat, available: Boolean = true): Unit

    companion object
}