package io.github.dehuckakpyt.telegrambot.exception.handler.chain


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface ChainExceptionHandler {
    fun whenCommandNotFound(command: String): Nothing
    fun whenStepNotFound(): Nothing
    fun whenUnexpectedMessageType(): Nothing
}