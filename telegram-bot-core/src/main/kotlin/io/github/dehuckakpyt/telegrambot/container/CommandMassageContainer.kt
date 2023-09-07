package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CommandMassageContainer(
    chatId: Long,
    message: Message,
    chainSource: ChainSource,
    bot: TelegramBot,
) : TextMassageContainer(chatId, message, content = null, chainSource, bot) {

    val commandPathParam get() = commandPathParamRegex.find(text)?.groupValues?.get(1)
    val commandArgument get() = commandArgumentRegex.find(text)?.groupValues?.get(1)

    companion object {
        private val commandPathParamRegex = Regex("^/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@([a-zA-Z0-9_]+))?(?: (.+))?")

        private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*)(?:__[a-zA-Z0-9-_]+)?(?:@([a-zA-Z_]+))?")
        fun fetchCommand(input: String?, usernameExpected: String): String? {
            input ?: return null

            val find = commandRegex.find(input) ?: return null
            val groups = find.groups

            val command = groups[1]?.value ?: return null

            val usernameActual = groups[2]?.value
            if (usernameActual != null && usernameActual != usernameExpected) return null

            return command
        }
    }
}