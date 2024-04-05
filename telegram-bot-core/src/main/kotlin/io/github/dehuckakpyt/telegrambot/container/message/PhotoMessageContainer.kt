package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.model.type.PhotoSize


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class PhotoMessageContainer(
    chatId: Long,
    message: Message,
    content: String?,
) : MessageContainer(chatId, message, content) {

    val caption: String? get() = message.caption
    val photos: List<PhotoSize> get() = message.photo
}