package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.ContactMessageArgument
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
internal class ContactMessageArgumentFactory : MessageArgumentFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return contact != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageArgument =
        ContactMessageArgument(chatId, message, content)

    override val type: KClass<out MessageArgument> = MessageType.CONTACT
}