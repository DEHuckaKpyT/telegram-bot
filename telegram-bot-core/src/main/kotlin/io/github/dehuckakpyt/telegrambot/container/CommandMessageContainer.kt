package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CommandMessageContainer(
    chatId: Long,
    message: Message,
    chainSource: ChainSource,
    contentConverter: ContentConverter,
    bot: TelegramBot,
) : TextMessageContainer(chatId, message, content = null, chainSource, contentConverter, bot) {

    val commandPathParam get() = commandPathParamRegex.find(text)?.groupValues?.get(1)
    val commandArgument get() = commandArgumentRegex.find(text)?.groupValues?.get(1)

    companion object : KoinComponent {
        private val username = get<String>(named("username"))

        private val commandPathParamRegex = Regex("^/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*(?:__([a-zA-Z0-9-_]+))?")
        private val commandArgumentRegex = Regex("^/[a-zA-Z0-9_]+(?:@[a-zA-Z0-9_]+)?(?: (.+))?")

        private val commandRegex = Regex("^(/[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*)(?:__[a-zA-Z0-9-_]+)?(?:@([a-zA-Z_]+))?")

        fun fetchCommand(input: String?): String? {
            input ?: return null

            val find = commandRegex.find(input) ?: return null
            val groups = find.groups

            val command = groups[1]?.value ?: return null

            val usernameActual = groups[2]?.value
            if (usernameActual != null && usernameActual != username) return null

            return command
        }
    }
}