package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {
    val text: String get() = message.text!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return text != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = TextMessageArgument(chatId, message, content)

        override val type = MessageType.TEXT
        override val typeName = "Текстовое сообщение"
    }
}