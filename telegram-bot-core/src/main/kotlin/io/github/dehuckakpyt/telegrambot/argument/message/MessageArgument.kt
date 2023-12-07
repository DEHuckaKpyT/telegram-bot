package io.github.dehuckakpyt.telegrambot.argument.message

import io.github.dehuckakpyt.telegrambot.argument.Argument
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class MessageArgument(
    chatId: Long,
    val message: Message,
    content: String?,
) : Argument(chatId, content) {

    override val from = message.from!!
    val messageId get() = message.messageId
    val chat get() = message.chat
}