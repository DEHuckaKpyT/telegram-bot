package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class MessageContainer(
    chatId: Long,
    val message: Message,
    content: String?,
) : Container(chatId, content) {

    override val from = message.from!!
    val messageId get() = message.messageId
    val chat get() = message.chat
}