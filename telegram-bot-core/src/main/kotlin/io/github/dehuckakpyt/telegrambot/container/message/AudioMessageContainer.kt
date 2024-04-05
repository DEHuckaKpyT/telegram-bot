package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Audio
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class AudioMessageContainer(chatId: Long, message: Message, content: String?) :
    MessageContainer(chatId, message, content) {

    val caption: String? get() = message.caption
    val audio: Audio get() = message.audio!!
}