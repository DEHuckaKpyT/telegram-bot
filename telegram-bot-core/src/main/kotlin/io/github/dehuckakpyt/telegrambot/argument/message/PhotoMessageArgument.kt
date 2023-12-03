package io.github.dehuckakpyt.telegrambot.argument.message

import com.elbekd.bot.types.Message
import com.elbekd.bot.types.PhotoSize


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
}