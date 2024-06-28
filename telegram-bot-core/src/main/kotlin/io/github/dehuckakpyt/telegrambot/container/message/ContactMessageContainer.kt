package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Contact
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ContactMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val contact: Contact get() = message.contact!!

    override val type: String = "CONTACT"
}