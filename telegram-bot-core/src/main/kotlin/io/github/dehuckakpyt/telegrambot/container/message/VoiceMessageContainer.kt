package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.Voice


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class VoiceMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val caption: String? get() = message.caption
    val voice: Voice get() = message.voice!!

    override val type: String = "VOICE"
}