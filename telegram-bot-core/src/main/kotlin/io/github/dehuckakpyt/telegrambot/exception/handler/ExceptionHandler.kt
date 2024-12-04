package io.github.dehuckakpyt.telegrambot.exception.handler

import io.github.dehuckakpyt.telegrambot.model.telegram.CallbackQuery
import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


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

    /**
     * Execute callback handler action with exceptions handling.
     *
     * @param callback on which user's callback may be thrown exception
     * @param block handler action for invoke
     */
    suspend fun executeCallback(callback: CallbackQuery, block: suspend () -> Unit): Unit = execute(callback.message!!.chat, block)

    /**
     * Execute command handler action with exceptions handling.
     *
     * @param message on which user's message may be thrown exception
     * @param block handler action for invoke
     */
    suspend fun executeCommand(message: Message, block: suspend () -> Unit): Unit = execute(message.chat, block)

    /**
     * Execute step handler action with exceptions handling.
     *
     * @param message on which user's message may be thrown exception
     * @param block handler action for invoke
     */
    suspend fun executeStep(message: Message, block: suspend () -> Unit): Unit = execute(message.chat, block)
}