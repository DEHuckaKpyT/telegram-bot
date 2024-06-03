package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Location
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class LocationMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val location: Location get() = message.location!!

    override val type: String = "LOCATION"
}