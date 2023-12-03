package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Document
import com.elbekd.bot.types.Message


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DocumentMessageArgument(chatId: Long, message: Message, content: String?) :
    MessageArgument(chatId, message, content) {

    val caption: String? get() = message.caption
    val document: Document get() = message.document!!
}