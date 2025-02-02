package io.github.dehuckakpyt.telegrambot.ext.source.chat

import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMemberUpdated
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource


/**
 * @author Denis Matytsin
 */
internal suspend fun TelegramChatSource.save(myChatMember: ChatMemberUpdated): Unit {
    val available = myChatMember.newChatMember.status.let { status -> status != "kicked" && status != "left" }
    save(myChatMember.chat, available)
}