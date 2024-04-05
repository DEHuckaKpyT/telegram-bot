package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.PhotoMessageContainer
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class PhotoMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return photo.isNotEmpty()
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageContainer =
        PhotoMessageContainer(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.caption

    override val type: KClass<out MessageContainer> = MessageType.PHOTO

    override val messageType: String = "PHOTO"
}