package io.github.dehuckakpyt.telegrambot.exception.handler.chain

import io.github.dehuckakpyt.telegrambot.argument.message.MessageArgument
import kotlin.reflect.KClass


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainExceptionHandler {
    fun whenCommandNotFound(command: String): Nothing
    fun whenStepNotFound(): Nothing
    fun whenUnexpectedMessageType(expectedMessageTypes: Set<KClass<out MessageArgument>>): Nothing
}