package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Contact
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ContactMessageContainer(chatId: Long, message: Message, content: String?) :
    MessageContainer(chatId, message, content) {

    val contact: Contact get() = message.contact!!
}