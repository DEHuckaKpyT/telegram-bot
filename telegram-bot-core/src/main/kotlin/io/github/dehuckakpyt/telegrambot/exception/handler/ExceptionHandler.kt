package io.github.dehuckakpyt.telegrambot.exception.handler


/**
 * Created on 23.11.2023.
 *
 * Handler for catching all exceptions in BotHandler actions.
 *
 * @author Denis Matytsin
 */
interface ExceptionHandler {

    /**
     * Execute handler action with exceptions handling.
     *
     * @param chatId in which chat may be thrown exception
     * @param block handler action for invoke
     */
    suspend fun execute(chatId: Long, block: suspend () -> Unit): Unit
}