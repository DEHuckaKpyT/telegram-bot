package io.github.dehuckakpyt.telegrambot.exception.handler


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class ExceptionHandler {
    abstract suspend fun execute(chatId: Long, block: suspend () -> Unit): Unit
    protected abstract suspend fun caught(chatId: Long, ex: Throwable): Unit
}