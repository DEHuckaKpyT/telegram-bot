package io.github.dehuckakpyt.telegrambot.data.container

import com.elbekd.bot.types.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMassageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
) : MassageContainer(chatId, message, content) {
    val text get() = message.text!!
}