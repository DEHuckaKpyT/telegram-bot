package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Audio
import io.github.dehuckakpyt.telegrambot.model.type.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class AudioMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val caption: String? get() = message.caption
    val audio: Audio get() = message.audio!!

    override val type: String = "AUDIO"
}