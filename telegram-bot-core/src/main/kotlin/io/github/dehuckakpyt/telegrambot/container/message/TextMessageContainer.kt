package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val text: String get() = message.text!!
    val entities: List<MessageEntity>? get() = message.entities

    override val type: String = "TEXT"
}