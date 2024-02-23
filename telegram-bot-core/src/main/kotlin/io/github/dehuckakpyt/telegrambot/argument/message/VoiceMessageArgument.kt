package io.github.dehuckakpyt.telegrambot.argument.message

import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.github.dehuckakpyt.telegrambot.model.type.Voice


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

    val caption: String? get() = message.caption
    val voice: Voice get() = message.voice!!
}