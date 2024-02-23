package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageType
import io.github.dehuckakpyt.telegrambot.argument.message.VoiceMessageArgument
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class VoiceMessageArgumentFactory : MessageArgumentFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return voice != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageArgument =
        VoiceMessageArgument(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.caption

    override val type: KClass<out MessageArgument> = MessageType.VOICE

    override val messageType: String = "VOICE"
}