package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.ContactMessageContainer
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
internal class ContactMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return contact != null
    }

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        ContactMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String {
        return "phoneNumber = ${message.contact!!.phoneNumber}, firstName = ${message.contact.firstName}"
    }

    override fun getMessageFileIds(message: Message): List<String>? = null

    override val type: KClass<out MessageContainer> = MessageType.CONTACT

    override val messageType: String = "CONTACT"
}