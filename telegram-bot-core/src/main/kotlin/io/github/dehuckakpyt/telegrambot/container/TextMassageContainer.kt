package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


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
    chainSource: ChainSource,
    bot: TelegramBot,
) : MassageContainer(chatId, message, content, chainSource, bot) {
    val text get() = message.text!!

    companion object : MessageContainerFactory {
        override fun condition(message: Message): Boolean = with(message) {
            return text != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            bot: TelegramBot
        ): MassageContainer = TextMassageContainer(chatId, message, content, chainSource, bot)

        override val type = TEXT
        override val typeName = "Текстовое сообщение"
    }
}