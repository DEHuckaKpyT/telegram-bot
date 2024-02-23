package io.github.dehuckakpyt.telegrambot.argument

import io.github.dehuckakpyt.telegrambot.model.type.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.type.MaybeInaccessibleMessage
import io.github.dehuckakpyt.telegrambot.model.type.User


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackArgument(
    chatId: Long,
    public val query: CallbackQuery,
    content: String?,
) : Argument(chatId, content) {

    override val from: User get() = query.from
    val message: MaybeInaccessibleMessage? get() = query.message
    val inlineMessageId: String? get() = query.inlineMessageId
}