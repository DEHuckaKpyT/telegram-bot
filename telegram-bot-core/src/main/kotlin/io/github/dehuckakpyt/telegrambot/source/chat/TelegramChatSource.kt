package io.github.dehuckakpyt.telegrambot.source.chat

import io.github.dehuckakpyt.telegrambot.model.telegram.Chat


/**
 * @author Denis Matytsin
 */
interface TelegramChatSource {

    public suspend fun save(chat: Chat, available: Boolean = true): Unit

    companion object
}