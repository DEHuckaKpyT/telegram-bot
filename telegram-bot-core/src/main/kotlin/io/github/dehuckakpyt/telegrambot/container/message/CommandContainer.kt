package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CommandContainer(message: Message, step: String?) :
    TextMessageContainer(message, step, content = null) {

    val commandPathParam: String? get() = commandPathParamRegex.getOrNull(text)
    val commandArgument: String? get() = commandArgumentRegex.getOrNull(text)

    override val type: String = "COMMAND"

    companion object {
        private val commandPathParamRegex = Regex("^/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@[a-zA-Z0-9_]+)?(?: +(.+))?$")

        private fun Regex.getOrNull(text: String): String? = find(text)!!.groupValues.get(1).ifEmpty { null }
    }
}