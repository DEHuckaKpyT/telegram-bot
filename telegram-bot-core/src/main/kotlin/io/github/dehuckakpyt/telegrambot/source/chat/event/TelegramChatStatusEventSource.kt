package io.github.dehuckakpyt.telegrambot.source.chat.event

import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated


/**
 * @author Denis Matytsin
 */
public interface TelegramChatStatusEventSource {

    public suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit
}