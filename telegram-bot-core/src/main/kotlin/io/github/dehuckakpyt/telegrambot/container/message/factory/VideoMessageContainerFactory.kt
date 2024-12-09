package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.VideoMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
internal class VideoMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.video != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        VideoMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = message.caption

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.video!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.VIDEO

    override val messageType: String = "VIDEO"
}
