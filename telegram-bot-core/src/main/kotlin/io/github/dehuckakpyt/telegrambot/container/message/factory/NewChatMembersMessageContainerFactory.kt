package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.NewChatMembersMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * @author Denis Matytsin
 */
internal class NewChatMembersMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.newChatMembers != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        NewChatMembersMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String = message.newChatMembers!!.joinToString(separator = "\n") { user ->
        "id: '${user.id}' (username: ${user.username}, lastName: ${user.lastName}, firstName: ${user.firstName})"
    }

    override fun getMessageFileIds(message: Message): List<String>? = null

    override val type: KClass<out MessageContainer> = MessageType.NEW_CHAT_MEMBERS

    override val messageType: String = "NEW_CHAT_MEMBERS"
}