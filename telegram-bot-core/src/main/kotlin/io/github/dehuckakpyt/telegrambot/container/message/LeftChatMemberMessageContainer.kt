package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.User


/**
 * @author Denis Matytsin
 */
class LeftChatMemberMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val leftChatMember: User get() = message.leftChatMember!!

    override val type: String = "LEFT_CHAT_MEMBER"
}