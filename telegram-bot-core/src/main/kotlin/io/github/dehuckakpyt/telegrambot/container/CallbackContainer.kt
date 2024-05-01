package io.github.dehuckakpyt.telegrambot.container

import io.github.dehuckakpyt.telegrambot.model.type.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.type.Chat
import io.github.dehuckakpyt.telegrambot.model.type.MaybeInaccessibleMessage
import io.github.dehuckakpyt.telegrambot.model.type.User


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackContainer(
    public val query: CallbackQuery,
    step: String?,
    content: String?,
) : GeneralContainer(step, content) {

    override val chat: Chat get() = query.message!!.chat
    override val from: User get() = query.from
    override val type: String = "CALLBACK"

    val message: MaybeInaccessibleMessage get() = query.message!!
    val inlineMessageId: String? get() = query.inlineMessageId
}