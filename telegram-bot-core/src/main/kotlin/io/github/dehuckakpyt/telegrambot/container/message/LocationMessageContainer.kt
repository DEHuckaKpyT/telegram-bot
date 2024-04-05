package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Location
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class LocationMessageContainer(chatId: Long, message: Message, content: String?) :
    MessageContainer(chatId, message, content) {

    val location: Location get() = message.location!!
}