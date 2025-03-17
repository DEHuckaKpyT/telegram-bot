package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message

class PrivateMessageContainer(message: Message, step: String?, content: String?) : MessageContainer(message, step, content) {
    override val type: String = "PRIVATE"
}