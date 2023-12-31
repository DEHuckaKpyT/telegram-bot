package io.github.dehuckakpyt.telegrambot.exception.handler.chain

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate
import io.github.dehuckakpyt.telegrambot.template.Templater

open class ChainExceptionHandlerImpl(
    protected val template: MessageTemplate,
    templater: Templater,
) : ChainExceptionHandler, Templater by templater {

    override fun whenCommandNotFound(command: String): Nothing {
        throw ChatException(template.whenCommandNotFound with ("command" to command))
    }

    override fun whenStepNotFound(): Nothing {
        throw PrivateChatException(template.whenStepNotFound)
    }

    override fun whenUnexpectedMessageType(): Nothing {
        throw ChatException(template.whenUnexpectedMessageType)
    }
}