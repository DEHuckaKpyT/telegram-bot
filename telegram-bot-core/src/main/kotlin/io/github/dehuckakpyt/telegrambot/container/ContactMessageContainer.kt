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
class ContactMessageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    bot: TelegramBot,
) : MassageContainer(chatId, message, content, chainSource, bot) {

    val contact get() = message.contact!!

    companion object : MessageContainerFactory {
        override fun condition(message: Message): Boolean = with(message) {
            return contact != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            bot: TelegramBot
        ): MassageContainer = ContactMessageContainer(chatId, message, content, chainSource, bot)

        override val type get() = CONTACT
        override val typeName get() = "Контакт"
    }
}