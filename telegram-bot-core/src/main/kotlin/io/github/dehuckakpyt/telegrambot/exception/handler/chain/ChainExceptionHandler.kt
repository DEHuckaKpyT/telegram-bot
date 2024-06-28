package io.github.dehuckakpyt.telegrambot.exception.handler.chain


/**
 * Created on 23.11.2023.
 *
 * Handler for internal exceptions with chain resolving.
 *
 * @author Denis Matytsin
 */
interface ChainExceptionHandler {
    fun whenCommandNotFound(command: String): Nothing
    fun whenStepNotFound(): Nothing
    fun whenUnexpectedMessageType(): Nothing
}