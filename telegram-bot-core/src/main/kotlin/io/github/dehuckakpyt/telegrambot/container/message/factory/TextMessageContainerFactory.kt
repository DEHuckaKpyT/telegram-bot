package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.TextMessageContainer
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TextMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return text != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageContainer =
        TextMessageContainer(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.text

    override val type: KClass<out MessageContainer> = MessageType.TEXT

    override val messageType: String = "TEXT"
}