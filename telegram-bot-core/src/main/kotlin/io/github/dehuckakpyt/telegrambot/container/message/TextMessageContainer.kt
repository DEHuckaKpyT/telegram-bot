package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.model.type.MessageEntity


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMessageContainer(chatId: Long, message: Message, content: String?) :
    MessageContainer(chatId, message, content) {

    val text: String get() = message.text!!
    val entities: List<MessageEntity> get() = message.entities
}