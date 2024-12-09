package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.VoiceMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class VoiceMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.voice != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        VoiceMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = message.caption

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.voice!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.VOICE

    override val messageType: String = "VOICE"
}