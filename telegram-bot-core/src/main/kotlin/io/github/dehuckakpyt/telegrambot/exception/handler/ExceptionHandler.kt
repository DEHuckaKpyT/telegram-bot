package io.github.dehuckakpyt.telegrambot.exception.handler

import io.github.dehuckakpyt.telegrambot.model.telegram.Chat


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
     * @param chat in which chat may be thrown exception
     * @param block handler action for invoke
     */
    suspend fun execute(chat: Chat, block: suspend () -> Unit): Unit
}