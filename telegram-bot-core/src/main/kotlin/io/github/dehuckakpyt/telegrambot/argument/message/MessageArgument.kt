package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.Argument


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