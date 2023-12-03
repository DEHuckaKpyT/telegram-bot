package io.github.dehuckakpyt.telegrambot.exception.handler.chain

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.whenCommandNotFoundTemplate
import io.github.dehuckakpyt.telegrambot.template.whenStepNotFoundTemplate
import io.github.dehuckakpyt.telegrambot.template.whenUnexpectedMessageTypeTemplate

open class ChainExceptionHandlerImpl : ChainExceptionHandler, Templating {
    override fun whenCommandNotFound(command: String): Nothing {
        throw ChatException(whenCommandNotFoundTemplate with ("command" to command))
    }

    override fun whenStepNotFound(): Nothing {
        throw PrivateChatException(whenStepNotFoundTemplate)
    }

    override fun whenUnexpectedMessageType(): Nothing {
        throw ChatException(whenUnexpectedMessageTypeTemplate)
    }
}