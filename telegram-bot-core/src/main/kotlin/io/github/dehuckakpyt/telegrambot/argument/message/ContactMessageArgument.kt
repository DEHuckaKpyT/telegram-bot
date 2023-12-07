package io.github.dehuckakpyt.telegrambot.argument.message

import io.github.dehuckakpyt.telegrambot.model.type.Contact
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ContactMessageArgument(chatId: Long, message: Message, content: String?) :
    MessageArgument(chatId, message, content) {

    val contact: Contact get() = message.contact!!
}