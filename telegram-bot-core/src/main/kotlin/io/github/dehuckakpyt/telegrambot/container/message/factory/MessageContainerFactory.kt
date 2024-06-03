package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal interface MessageContainerFactory {

    fun matches(message: Message): Boolean

    fun create(message: Message, step: String?, content: String?): MessageContainer

    fun getMessageText(message: Message): String?

    fun getMessageFileIds(message: Message): List<String>?

    val type: KClass<out MessageContainer>

    val messageType: String
}