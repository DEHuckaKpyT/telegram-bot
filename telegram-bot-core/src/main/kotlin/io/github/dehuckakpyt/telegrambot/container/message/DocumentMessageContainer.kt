package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Document
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DocumentMessageContainer(chatId: Long, message: Message, content: String?) :
    MessageContainer(chatId, message, content) {

    val caption: String? get() = message.caption
    val document: Document get() = message.document!!
}