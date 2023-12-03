package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import com.elbekd.bot.types.MessageEntity


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMessageArgument(chatId: Long, message: Message, content: String?) :
    MessageArgument(chatId, message, content) {

    val text: String get() = message.text!!
    val entities: List<MessageEntity> get() = message.entities
}