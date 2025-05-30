package io.github.dehuckakpyt.telegrambot.source.chat.event

import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated


/**
 * @author Denis Matytsin
 */
internal class EmptyTelegramChatStatusEventSource : TelegramChatStatusEventSource {
    override suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit {}
}