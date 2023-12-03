package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Contact
import com.elbekd.bot.types.Message


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