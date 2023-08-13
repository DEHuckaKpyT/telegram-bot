package io.github.dehuckakpyt.telegrambot.data.container

import com.elbekd.bot.types.CallbackQuery


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackMassageContainer(
    chatId: Long,
    private val query: CallbackQuery,
    content: String?,
) : Container(chatId, content) {

    val from get() = query.from
    val message get() = query.message
    val inlineMessageId get() = query.inlineMessageId
}