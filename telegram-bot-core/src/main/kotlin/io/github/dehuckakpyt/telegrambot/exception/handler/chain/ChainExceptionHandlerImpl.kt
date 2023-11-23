package io.github.dehuckakpyt.telegrambot.exception.handler.chain

import io.github.dehuckakpyt.telegrambot.container.MassageContainer
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.whenCommandNotFoundTemplate
import io.github.dehuckakpyt.telegrambot.template.whenStepNotFoundTemplate
import io.github.dehuckakpyt.telegrambot.template.whenUnexpectedMessageTypeTemplate
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance

open class ChainExceptionHandlerImpl : ChainExceptionHandler, Templating {
    override fun whenCommandNotFound(command: String): Nothing {
        throw ChatException(whenCommandNotFoundTemplate with ("command" to command))
    }

    override fun whenStepNotFound(): Nothing {
        throw PrivateChatException(whenStepNotFoundTemplate)
    }

    override fun whenUnexpectedMessageType(expectedMessageTypes: Set<KClass<out MassageContainer>>): Nothing {
        val expectedMessageNames = expectedMessageTypes.joinToString(", ") {
            (it.companionObjectInstance as MessageContainerFactory).typeName
        }
        throw ChatException(whenUnexpectedMessageTypeTemplate with ("expectedMessageNames" to expectedMessageNames))
    }
}