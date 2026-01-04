package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat


/**
 * @author Denis Matytsin
 */
internal class EmptyTelegramChatSource : TelegramChatSource<TelegramChat> {
    override suspend fun save(chat: Chat, available: Boolean): Unit {}
}