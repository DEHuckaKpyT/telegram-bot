package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Contact
import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ContactMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {

    val contact: Contact get() = message.contact!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return contact != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = ContactMessageArgument(chatId, message, content)

        override val type = MessageType.CONTACT
        override val typeName = "Контакт"
    }
}