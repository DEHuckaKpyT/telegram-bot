package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.VoiceMessageContainer
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class VoiceMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return voice != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageContainer =
        VoiceMessageContainer(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.caption

    override val type: KClass<out MessageContainer> = MessageType.VOICE

    override val messageType: String = "VOICE"
}