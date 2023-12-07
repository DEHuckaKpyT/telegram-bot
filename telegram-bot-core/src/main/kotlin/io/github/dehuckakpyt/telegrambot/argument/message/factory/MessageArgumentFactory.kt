package io.github.dehuckakpyt.telegrambot.argument.message.factory

import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import io.github.dehuckakpyt.telegrambot.model.type.Message
import kotlin.reflect.KClass


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal interface MessageArgumentFactory {

    fun matches(message: Message): Boolean

    fun create(chatId: Long, message: Message, content: String?): MessageArgument

    val type: KClass<out MessageArgument>
}