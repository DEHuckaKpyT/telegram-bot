package io.github.dehuckakpyt.telegrambot.argument

import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.User


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackArgument(
    chatId: Long,
    val query: CallbackQuery,
    content: String?,
) : Argument(chatId, content) {

    override val from: User get() = query.from
    val message: Message? get() = query.message
    val inlineMessageId: String? get() = query.inlineMessageId
}