package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.LocationMessageContainer
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
internal class LocationMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return location != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageContainer =
        LocationMessageContainer(chatId, message, content)

    override fun getMessageText(message: Message): String {
        return "longitude = ${message.location!!.longitude}, latitude = ${message.location.latitude}"
    }

    override val type: KClass<out MessageContainer> = MessageType.LOCATION

    override val messageType: String = "LOCATION"
}