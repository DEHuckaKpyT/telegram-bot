package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.DocumentMessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
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

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        DocumentMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = message.caption

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.document!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.DOCUMENT

    override val messageType: String = "DOCUMENT"
}