package io.github.dehuckakpyt.telegrambot.argument.message.factory

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
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