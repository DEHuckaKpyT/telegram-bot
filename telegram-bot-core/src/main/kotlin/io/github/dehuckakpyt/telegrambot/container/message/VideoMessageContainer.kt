package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.Video


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
class VideoMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val caption: String? get() = message.caption
    val video: Video get() = message.video!!

    override val type: String = "VIDEO"
}