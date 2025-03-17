package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.PrivateMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass

class PrivateMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.chat.type == "private"

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        PrivateMessageContainer(message, step, content)

    override fun getMessageText(message: Message) = ""

    override fun getMessageFileIds(message: Message): List<String>? = null

    override val type: KClass<out MessageContainer> = MessageType.PRIVATE
    override val messageType: String = "PRIVATE"
}