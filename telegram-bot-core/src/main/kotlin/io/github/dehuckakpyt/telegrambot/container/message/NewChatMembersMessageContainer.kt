package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.User


/**
 * @author Denis Matytsin
 */
class NewChatMembersMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val newChatMembers: List<User> get() = message.newChatMembers!!

    override val type: String = "NEW_CHAT_MEMBERS"
}