package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.VideoNoteMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
internal class VideoNoteMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.videoNote != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        VideoNoteMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = null

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.videoNote!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.VIDEO_NOTE

    override val messageType: String = "VIDEO_NOTE"
}
