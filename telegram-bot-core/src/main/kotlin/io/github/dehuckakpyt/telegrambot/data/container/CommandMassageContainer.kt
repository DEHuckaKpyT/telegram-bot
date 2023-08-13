package io.github.dehuckakpyt.telegrambot.data.container

import com.elbekd.bot.types.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CommandMassageContainer(
    chatId: Long,
    message: Message,
) : TextMassageContainer(chatId, message, null) {

    val commandPathParam get() = commandPathParamRegex.find(text)?.groupValues?.get(1)
    val commandArgument get() = commandArgumentRegex.find(text)?.groupValues?.get(1)

    companion object {
        private val commandPathParamRegex = Regex("^/[a-zA-Z]+(?:_[a-zA-Z]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@([a-zA-Z0-9_]+))?(?: (.+))?")
    }
}