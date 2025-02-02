package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.LeftChatMemberMessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * @author Denis Matytsin
 */
internal class LeftChatMemberMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.leftChatMember != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        LeftChatMemberMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String = with(message.leftChatMember!!) {
        "id: '$id' (username: $username, lastName: $lastName, firstName: $firstName)"
    }

    override fun getMessageFileIds(message: Message): List<String>? = null

    override val type: KClass<out MessageContainer> = MessageType.LEFT_CHAT_MEMBER

    override val messageType: String = "LEFT_CHAT_MEMBER"
}