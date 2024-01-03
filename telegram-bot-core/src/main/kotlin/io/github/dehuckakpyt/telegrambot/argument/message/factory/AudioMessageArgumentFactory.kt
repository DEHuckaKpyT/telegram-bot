package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.AudioMessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageType
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class AudioMessageArgumentFactory : MessageArgumentFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return audio != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageArgument =
        AudioMessageArgument(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.caption

    override val type: KClass<out MessageArgument> = MessageType.AUDIO

    override val messageType: String = "AUDIO"
}