package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.WebAppDataMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 06.10.2024.
 *
 * @author Denis Matytsin
 */
internal class WebAppDataMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.webAppData != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        WebAppDataMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String = message.webAppData!!.data

    override fun getMessageFileIds(message: Message): List<String>? = null

    override val type: KClass<out MessageContainer> = MessageType.WEB_APP_DATA

    override val messageType: String = "WEB_APP_DATA"
}