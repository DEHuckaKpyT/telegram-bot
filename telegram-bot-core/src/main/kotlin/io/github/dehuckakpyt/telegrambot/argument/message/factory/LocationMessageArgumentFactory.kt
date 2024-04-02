package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.LocationMessageArgument
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
internal class LocationMessageArgumentFactory : MessageArgumentFactory {

    override fun matches(message: Message): Boolean = with(message) {
        return location != null
    }

    override fun create(chatId: Long, message: Message, content: String?): MessageArgument =
        LocationMessageArgument(chatId, message, content)

    override fun getMessageText(message: Message): String {
        return "longitude = ${message.location!!.longitude}, latitude = ${message.location.latitude}"
    }

    override val type: KClass<out MessageArgument> = MessageType.LOCATION

    override val messageType: String = "LOCATION"
}