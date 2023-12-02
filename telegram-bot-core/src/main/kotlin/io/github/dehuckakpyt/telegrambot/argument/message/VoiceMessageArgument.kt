package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import com.elbekd.bot.types.Voice
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class VoiceMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {

    val voice: Voice get() = message.voice!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return voice != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = VoiceMessageArgument(chatId, message, content)

        override val type = MessageType.VOICE
        override val typeName = "Голосовое сообщение"
    }
}