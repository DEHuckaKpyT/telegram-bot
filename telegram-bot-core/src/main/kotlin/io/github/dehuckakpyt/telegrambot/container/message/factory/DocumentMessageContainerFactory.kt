package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.DocumentMessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class DocumentMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return document != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageContainer =
        DocumentMessageContainer(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.caption

    override val type: KClass<out MessageContainer> = MessageType.DOCUMENT

    override val messageType: String = "DOCUMENT"
}