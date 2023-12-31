package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.MessageType
import io.github.dehuckakpyt.telegrambot.argument.message.TextMessageArgument
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TextMessageArgumentFactory : MessageArgumentFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return text != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageArgument =
        TextMessageArgument(chatId, message, content)

    override fun getMessageText(message: Message): String? = message.text

    override val type: KClass<out MessageArgument> = MessageType.TEXT

    override val messageType: String = "TEXT"
}