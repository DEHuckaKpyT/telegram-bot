package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Audio
import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class AudioMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {

    val caption: String? get() = message.caption
    val audio: Audio get() = message.audio!!

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return audio != null
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = AudioMessageArgument(chatId, message, content)

        override val type = MessageType.AUDIO
        override val typeName = "Аудио сообщение"
    }
}