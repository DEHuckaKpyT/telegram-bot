package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.AudioMessageContainer
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
internal class AudioMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return audio != null
    }

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        AudioMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = message.caption

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.audio!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.AUDIO

    override val messageType: String = "AUDIO"
}