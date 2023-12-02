package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Document
import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DocumentMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {

    val caption: String? get() = message.caption
    val document: Document get() = message.document!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return document != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = DocumentMessageArgument(chatId, message, content)

        override val type = MessageType.DOCUMENT
        override val typeName = "Файл"
    }
}