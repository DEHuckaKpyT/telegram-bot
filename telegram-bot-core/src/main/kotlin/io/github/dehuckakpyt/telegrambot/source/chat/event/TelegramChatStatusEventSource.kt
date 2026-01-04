package io.github.dehuckakpyt.telegrambot.source.chat.event

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated


/**
 * @author Denis Matytsin
 */
public interface TelegramChatStatusEventSource<EntityT : TelegramChatStatusEvent> {

    public suspend fun save(chatMemberUpdated: ChatMemberUpdated): Unit

    companion object
}