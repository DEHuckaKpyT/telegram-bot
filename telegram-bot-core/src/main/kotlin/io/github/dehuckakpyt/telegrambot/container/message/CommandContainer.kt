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

    val commandPathParam: String? get() = commandPathParamRegex.getOrNull(text)
    val commandArgument: String? get() = commandArgumentRegex.getOrNull(text)

    companion object {
        private val commandPathParamRegex = Regex("^/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@[a-zA-Z0-9_]+)?(?: +(.+))?$")

        private fun Regex.getOrNull(text: String): String? = find(text)!!.groupValues.get(1).ifEmpty { null }
    }
}