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
class AudioMessageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    contentConverter: ContentConverter,
    bot: TelegramBot,
) : MessageContainer(chatId, message, content, chainSource, contentConverter, bot) {

    val caption get() = message.caption
    val audio get() = message.audio!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return audio != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
            chainSource: ChainSource,
            contentConverter: ContentConverter,
            bot: TelegramBot
        ): MessageContainer = AudioMessageContainer(chatId, message, content, chainSource, contentConverter, bot)

        override val type = AUDIO
        override val typeName = "Аудио сообщение"
    }
}