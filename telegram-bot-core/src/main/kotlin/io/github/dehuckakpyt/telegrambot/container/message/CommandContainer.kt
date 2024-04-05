package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CommandContainer(chatId: Long, message: Message) :
    TextMessageContainer(chatId, message, content = null) {

    val commandPathParam: String? get() = commandPathParamRegex.find(text)?.groupValues?.get(1)
    val commandArgument: String? get() = commandArgumentRegex.find(text)?.groupValues?.get(1)

    companion object {
        private val commandPathParamRegex = Regex("^/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@[a-zA-Z0-9_]+)?(?: (.+))?")
    }
}