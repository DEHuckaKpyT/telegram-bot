package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import com.elbekd.bot.types.PhotoSize
import io.github.dehuckakpyt.telegrambot.argument.factory.MessageContainerFactory


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class PhotoMessageArgument(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageArgument(chatId, message, content) {

    val caption: String? get() = message.caption
    val photos: List<PhotoSize> get() = message.photo

    companion object : MessageContainerFactory {
        override fun matches(message: Message): Boolean = with(message) {
            return photo.isNotEmpty()
        }

        override fun create(
            chatId: Long,
            message: Message,
            content: String?,
        ): MessageArgument = PhotoMessageArgument(chatId, message, content)

        override val type = MessageType.PHOTO
        override val typeName = "Фотография"
    }
}