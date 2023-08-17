package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 16.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class DocumentMessageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    bot: TelegramBot,
) : MassageContainer(chatId, message, content, chainSource, bot) {

    val caption get() = message.caption
    val document get() = message.document!!

    companion object : MessageContainerFactory {
        override fun condition(message: Message): Boolean = with(message) {
            return document != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            bot: TelegramBot
        ): MassageContainer = DocumentMessageContainer(chatId, message, content, chainSource, bot)

        override val type get() = DOCUMENT
        override val typeName get() = "Файл"
    }
}