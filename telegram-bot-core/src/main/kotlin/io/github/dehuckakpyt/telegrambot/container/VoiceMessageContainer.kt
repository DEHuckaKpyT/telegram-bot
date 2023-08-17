package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class VoiceMessageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    bot: TelegramBot,
) : MassageContainer(chatId, message, content, chainSource, bot) {
    val voice get() = message.voice!!

    companion object : MessageContainerFactory {
        override fun condition(message: Message): Boolean = with(message) {
            return voice != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            bot: TelegramBot
        ): MassageContainer = VoiceMessageContainer(chatId, message, content, chainSource, bot)

        override val type get() = VOICE
        override val typeName get() = "Голосовое сообщение"
    }
}