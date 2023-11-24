package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TextMessageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    contentConverter: ContentConverter,
    bot: TelegramBot,
) : MessageContainer(chatId, message, content, chainSource, contentConverter, bot) {
    val text get() = message.text!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return text != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            contentConverter: ContentConverter,
            bot: TelegramBot
        ): MessageContainer = TextMessageContainer(chatId, message, content, chainSource, contentConverter, bot)

        override val type = TEXT
        override val typeName = "Текстовое сообщение"
    }
}